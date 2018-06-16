package io.sandstorm.rest;

import io.sandstorm.common.query.domain.Page;
import io.sandstorm.controller.app.FindService;
import io.sandstorm.controller.app.JobExecQParams;
import io.sandstorm.controller.app.JobSliceExecQParams;
import io.sandstorm.controller.app.TestJobApp;
import io.sandstorm.controller.domain.job.JobExecution;
import io.sandstorm.controller.domain.job.JobSliceExecution;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static io.sandstorm.common.InputAssert.notNull;

@RestController
@RequestMapping("/job-executions")
public class JobExecutionRest {

    @Resource
    private FindService findService;
    @Resource
    private TestJobApp testJobApp;

    @GetMapping
    public Page<JobExecution> listJobExecutions(JobExecQParams params) {
        params.validate();
        return findService.findJobExecutions(params);
    }

    @GetMapping("/{id}")
    public JobExecution detailJobExecution(@PathVariable("id") ObjectId id) {
        notNull(id, "path variable id");
        return testJobApp.detailJobExecution(id);
    }

    @PostMapping("/{id}/actions/stop")
    public void stopJobExecution(@PathVariable("id") ObjectId id) {
        notNull(id, "path variable id");
        testJobApp.stopJobExecution(id);
    }

    @DeleteMapping("/{id}")
    public void deleteJobExecution(@PathVariable("id") ObjectId id) {
        notNull(id, "path variable id");
        testJobApp.deleteJobExecution(id);
    }

    @GetMapping("/{id}/job_slice_executions")
    public Page<JobSliceExecution> listJobSliceExecutions(@PathVariable("id") ObjectId jobExecId,
                                                          JobSliceExecQParams params) {
        params.setJobExecId(jobExecId);
        params.validate();
        return findService.findJobSliceExecs(params);
    }

}
