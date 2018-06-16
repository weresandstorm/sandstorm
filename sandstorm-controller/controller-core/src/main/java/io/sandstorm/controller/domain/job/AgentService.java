package io.sandstorm.controller.domain.job;

public interface AgentService {

    void prepareFor(JobSliceExecution sliceExec);

    void startTestEngine(JobSliceExecution sliceExec);

    void stop(JobSliceExecution sliceExec);

}
