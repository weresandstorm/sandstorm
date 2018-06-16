package io.sandstorm.controller.domain.job;

import org.bson.types.ObjectId;

public interface PeriodicJobScheduler {

    /**
     * Schedules the given {@code testJob} to run periodically by {@link TestJob#execPlan()}.
     * <p>In the implementation, when it's time to run to the job, please call {@link JobService#runJob(ObjectId)}.
     *
     * @param testJob the job to schedule
     * @throws JobSchedulingException all exceptions occurred in the implementation should be translated to a {@link JobSchedulingException}
     */
    void schedule(TestJob testJob);

    /**
     * Deschdules the given {@code testJob}.
     *
     * @param testJob the job to deschedule
     * @throws JobSchedulingException all exceptions occurred in the implementation should be translated to a {@link JobSchedulingException}
     */
    void deschedule(TestJob testJob);

}
