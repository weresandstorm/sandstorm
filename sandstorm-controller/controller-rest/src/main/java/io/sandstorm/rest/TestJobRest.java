package io.sandstorm.rest;

import io.sandstorm.common.query.domain.Existence;
import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.SimplePagingQParams;
import io.sandstorm.controller.app.TestJobCmd;
import io.sandstorm.rest.common.JsonSchemaFactory;
import io.sandstorm.rest.validation.LoadProfileValidator;
import io.sandstorm.controller.app.FindService;
import io.sandstorm.controller.app.TestJobApp;
import io.sandstorm.controller.domain.job.RunJobCmd;
import io.sandstorm.controller.domain.job.TestJob;
import org.bson.types.ObjectId;
import org.everit.json.schema.Schema;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static io.sandstorm.common.InputAssert.notBlank;
import static io.sandstorm.common.InputAssert.notNull;

@RestController
@RequestMapping("/test-jobs")
public class TestJobRest {

    @Resource
    private TestJobApp testJobApp;
    @Resource
    private FindService findService;

    @GetMapping("/{name}/actions/check-existence")
    public Existence checkExistence(@PathVariable("name") String name) {
        notBlank(name, "path variable name");
        return testJobApp.checkJobExistence(name);
    }

    @PostMapping
    public void create(@RequestBody TestJobCmd.Create createCmd) {
        Schema schema = JsonSchemaFactory.getSchema("TestJobCreateCmd.json");
        schema.validate(createCmd);
        testJobApp.createJob(createCmd);
    }

    @GetMapping
    public Page<TestJob> list(SimplePagingQParams params) {
        params.validate();
        return findService.findTestJobs(params);
    }

    @GetMapping("/{id}")
    public TestJob detail(@PathVariable("id") ObjectId id) {
        notNull(id, "id");
        return testJobApp.detailJob(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") ObjectId id,
                       @RequestBody TestJobCmd.Update updateCmd) {
        Schema schema = JsonSchemaFactory.getSchema("TestJobCreateCmd.json");
        schema.validate(updateCmd);
        testJobApp.updateJob(id, updateCmd);
    }

    @PostMapping("/{id}/actions/run")
    public void run(@PathVariable("id") ObjectId id, @RequestBody RunJobCmd command) {
        notNull(id, "path variable id");
        notNull(command, "json object in request body");
        command.setJobId(id);
        command.validate(LoadProfileValidator.object);
        testJobApp.runJob(command);
    }

    @PostMapping("/{id}/actions/deschedule")
    public void deschedule(@PathVariable("id") ObjectId id) {
        notNull(id, "path variable id");
        testJobApp.descheduleJob(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") ObjectId id) {
        notNull(id, "id");
        testJobApp.deleteJob(id);
    }

}
