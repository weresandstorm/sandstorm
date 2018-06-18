package io.sandstorm.controller.app;

import io.sandstorm.common.BizAssert;
import io.sandstorm.common.CaseCode;
import io.sandstorm.common.ViolateBizConstraintException;
import io.sandstorm.common.query.domain.Existence;
import io.sandstorm.controller.domain.job.JobExecRepo;
import io.sandstorm.controller.domain.job.JobExecution;
import io.sandstorm.controller.domain.job.JobOperator;
import io.sandstorm.controller.domain.job.JobService;
import io.sandstorm.controller.domain.job.JobSliceExecRepo;
import io.sandstorm.controller.domain.job.JobSliceExecution;
import io.sandstorm.controller.domain.job.PeriodicJobScheduler;
import io.sandstorm.controller.domain.job.RunJobCmd;
import io.sandstorm.controller.domain.job.TestJob;
import io.sandstorm.controller.domain.job.TestJobRepo;
import io.sandstorm.controller.domain.resource.DataSetRepo;
import io.sandstorm.controller.domain.resource.TestScript;
import io.sandstorm.controller.domain.resource.TestScriptRepo;
import java.util.function.Function;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestJobApp {

    @Resource
    private TestScriptRepo testScriptRepo;
    @Resource
    private DataSetRepo dataSetRepo;
    @Resource
    private TestJobRepo testJobRepo;
    @Resource
    private JobExecRepo jobExecRepo;
    @Resource
    private JobSliceExecRepo jobSliceExecRepo;
    @Resource
    private PeriodicJobScheduler jobScheduler;
    @Resource
    private JobService jobService;
    @Resource
    private JobOperator jobOperator;

    public Existence checkJobExistence(String name) {
        boolean exist = testJobRepo.exist(name);
        return new Existence(exist);
    }

    public void createJob(TestJobCmd.Create createCmd) {
        TestJob testJob = createCmd.toTestJob(scriptId -> testScriptRepo.get(scriptId),
            dataSetId -> dataSetRepo.get(dataSetId));
        testJob.beginDomainLifeCycle();
        testJobRepo.save(testJob);

        if (testJob.isPeriodic()) {
            jobScheduler.schedule(testJob);
        }
    }

    public TestJob detailJob(ObjectId id) {
        return testJobRepo.get(id);
    }

    public void updateJob(ObjectId id, TestJobCmd.Update update) {
        TestJob testJob = testJobRepo.get(id);
        testJob.mergeFrom(update);
        testJobRepo.save(testJob);
    }

    public void runJob(RunJobCmd command) {
        jobService.runJob(command);
    }

    public void descheduleJob(ObjectId id) {
        TestJob theJob = testJobRepo.get(id);
        if (theJob.isPeriodic()) {
            jobScheduler.deschedule(theJob);
        }
    }

    public void deleteJob(ObjectId id) {
        testJobRepo.delete(id);
    }

    public JobExecution detailJobExecution(ObjectId id) {
        return jobExecRepo.get(id);
    }

    public void stopJobExecution(ObjectId id) {
        JobExecution jobExec = jobExecRepo.get(id);
        BizAssert.check(!jobExec.isEnded(),
                CaseCode.job_exec_unstoppable,
                "JobExecution [id:%s] is ended, so can't be stopped",
                id);
        List<JobSliceExecution> sliceExecs = jobSliceExecRepo.findBy(jobExec.id());
        jobOperator.stop(id, sliceExecs);
    }

    public void deleteJobExecution(ObjectId id) {
        JobExecution jobExec = jobExecRepo.get(id);
        BizAssert.check(jobExec.isEnded(),
                CaseCode.job_exec_undeletable,
                "JobExecution [id:%s] isn't ended, so can't be deleted",
                id);
        jobSliceExecRepo.delete(jobExec.id());
        jobExecRepo.delete(id);
    }

    private void assertNameNotExist(String name) {
        if (checkJobExistence(name).exist()) {
            throw new ViolateBizConstraintException(String.format(
                    "%s with name '%s' already exists",
                    TestJob.class.getSimpleName(), name));
        }
    }

}
