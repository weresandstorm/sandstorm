package io.sandstorm.controller.portadapter.schedule;

import io.sandstorm.common.ParamAssert;
import io.sandstorm.controller.domain.job.ExecPlan;
import io.sandstorm.controller.domain.job.JobSchedulingException;
import io.sandstorm.controller.domain.job.PeriodicJobScheduler;
import io.sandstorm.controller.domain.job.TestJob;
import java.text.ParseException;
import javax.annotation.Resource;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

@Service
public class TestJobScheduler implements PeriodicJobScheduler {

    private static final String GROUP_JOB = "jobdetail";

    private static final String GROUP_TRIGGER = "jobtrigger";

    private static final String triggerExpressionPattern = "0 %s %s ? %s/%s %sL";

    private static final String testTriggerExpressionPattern = "0 1/2 * * * ?";


    @Resource
    private Scheduler scheduler;

    public void schedule(TestJob testJob) {
        ExecPlan execPlan = testJob.execPlan();
        ParamAssert.notNull(execPlan, "execPlan");
        ExecPlan.ExecRate execRate = execPlan.execRate().get();
        ParamAssert.notNull(execRate, "execRate");

        try {
            scheduler.scheduleJob(jobDetail(testJob), jobTrigger(testJob, execRate));
        } catch (Exception e) {
            String errorMsg = String.format("Schedule this testjob failed! testJobId = [%s], testJobName = [%s]" +
                ", and error = [%s]", testJob.idAsString(), testJob.name(), e.getMessage());
            throw new JobSchedulingException(errorMsg, e);
        }

    }

    @Override
    public void deschedule(TestJob testJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(testJob.idAsString(), GROUP_TRIGGER);
            scheduler.unscheduleJob(triggerKey);
        } catch (Exception e) {
            String errorMsg = String.format("Deschedule this testjob failed! testJobId = [%s], testJobName = [%s]" +
                ", and error = [%s]", testJob.idAsString(), testJob.name(), e.getMessage());
            throw new JobSchedulingException(errorMsg, e);
        }
    }

    private Trigger jobTrigger(TestJob testJob, ExecPlan.ExecRate execRate) throws ParseException {

        String cronExpressionFormat = String.format(triggerExpressionPattern,
            execRate.minute(),
            execRate.hour(),
            execRate.startMonth(),
            execRate.monthInterval(),
            execRate.lastDayOfWeek());

        CronExpression cronExpression = new CronExpression(cronExpressionFormat);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
            .withMisfireHandlingInstructionDoNothing();  //Misfire handle

        Trigger jobTrigger = TriggerBuilder.newTrigger()
            .withIdentity(TriggerKey.triggerKey(testJob.idAsString(), GROUP_TRIGGER))
            .withDescription(testJob.name())
            .withSchedule(cronScheduleBuilder)
            .startNow()
            .build();
        return jobTrigger;
    }

    private JobDetail jobDetail(TestJob testJob) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("testJobId", testJob.idAsString());

        JobDetail jobDetail = JobBuilder.newJob(TestJobExecutor.class)
            .withIdentity(JobKey.jobKey(testJob.idAsString(), GROUP_JOB))
            .withDescription(testJob.name())
            .setJobData(jobDataMap)
            .build();
        return jobDetail;
    }
}
