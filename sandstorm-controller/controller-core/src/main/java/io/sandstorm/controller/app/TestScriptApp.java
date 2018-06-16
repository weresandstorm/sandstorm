package io.sandstorm.controller.app;

import io.sandstorm.common.BizAssert;
import io.sandstorm.common.CaseCode;
import io.sandstorm.common.query.domain.Existence;
import io.sandstorm.controller.domain.job.JobExecRepo;
import io.sandstorm.controller.domain.job.TestJobRepo;
import io.sandstorm.controller.domain.resource.TestScript;
import io.sandstorm.controller.domain.resource.TestScriptDiscard;
import io.sandstorm.controller.domain.resource.TestScriptDiscardRepo;
import io.sandstorm.controller.domain.resource.TestScriptRepo;
import io.sandstorm.spi.SpiServiceFinder;
import io.sandstorm.spi.storage.ScriptStorage;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class TestScriptApp {

    @Resource
    private TestScriptRepo scriptRepo;
    @Resource
    private TestScriptDiscardRepo testScriptDiscardRepo;
    @Resource
    private TestJobRepo testJobRepo;
    @Resource
    private JobExecRepo jobExecRepo;

    private ScriptStorage scriptStorage = SpiServiceFinder.findService(ScriptStorage.class);

    public Existence checkExistence(String jarAlias) {
        boolean exist = scriptRepo.exist(jarAlias);
        return new Existence(exist);
    }

    public void create(TestScript testScript, byte[] jarContent) {
        BizAssert.check(
                !checkExistence(testScript.jarAlias()).exist(),
                CaseCode.script_alias_exist,
                "%s with alias %s already exists",
                TestScript.class.getSimpleName(), testScript.jarAlias()
        );

        testScript.beginDomainLifeCycle();
        String jarUrl = scriptStorage.put(testScript.jarAlias(), jarContent, testScript.revision());
        testScript.setJarUrl(jarUrl);
        scriptRepo.save(testScript);
    }

    public TestScript detail(ObjectId scriptId) {
        return scriptRepo.get(scriptId);
    }

    public CheckJobExecsEnded checkReferringJobExecsEnded(ObjectId scriptId) {
        TestScript script = scriptRepo.get(scriptId);
        boolean notEndedExist = jobExecRepo.notEndedExistByScript(script);
        return new CheckJobExecsEnded(!notEndedExist);
    }

    public void update(TestScript change, Optional<byte[]> jarContent) {
        TestScript theScript = scriptRepo.get(change.id());
        BizAssert.check(
                theScript.jarAlias().equals(change.jarAlias()),
                "Field jarAlias of %s doesn't allowed to be modified",
                TestScript.class.getSimpleName()
        );
        BizAssert.check(
                checkReferringJobExecsEnded(change.id()).allEnded(),
                CaseCode.script_unchangeable_currently,
                "Not all job-execs referring test-script [id:%s] ended, so the referred test-script is currently unchangeable",
                change.id()
        );

        boolean contentChanged = jarContent.isPresent();
        if (contentChanged) {
            int newRevision = theScript.revision() + 1;
            String jarUrl = scriptStorage.put(theScript.jarAlias(), jarContent.get(), newRevision);
            change.setJarUrl(jarUrl);

            // Put the old test-script into trash
            testScriptDiscardRepo.save(new TestScriptDiscard(theScript, change.creator()));
        }

        theScript.mergeFrom(change, contentChanged);
        scriptRepo.save(theScript);
    }

    public void delete(ObjectId scriptId) {
        BizAssert.check(
                !testJobRepo.existWithScript(scriptId),
                CaseCode.script_undeletable,
                "TestScript [id:%s] is still referenced by a TestJob, so can't be deleted",
                scriptId);
        scriptRepo.delete(scriptId);
    }

}
