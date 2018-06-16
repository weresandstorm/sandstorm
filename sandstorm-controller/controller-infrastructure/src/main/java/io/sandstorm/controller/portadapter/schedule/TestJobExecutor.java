package io.sandstorm.controller.portadapter.schedule;

import io.sandstorm.controller.domain.job.JobService;
import javax.annotation.Resource;

import io.sandstorm.controller.domain.job.JobService;
import org.bson.types.ObjectId;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class TestJobExecutor extends QuartzJobBean {

    private String testJobId;

    @Resource
    private JobService jobService;

    public TestJobExecutor() {}

    public void setTestJobId(String testJobId) {
        this.testJobId = testJobId;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        jobService.runJob(new ObjectId(testJobId));
    }
}
