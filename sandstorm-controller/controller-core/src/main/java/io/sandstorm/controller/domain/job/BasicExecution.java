package io.sandstorm.controller.domain.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.sandstorm.common.BizAssert;
import io.sandstorm.common.domain.model.VersionedEntityOrBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BasicExecution extends VersionedEntityOrBuilder {

    protected ExecutionStatus status;
    protected String failureInfo;
    private List<ExecutionStatus> statusFlow = new ArrayList<>(5);
    private Date startedAt;
    private Date endedAt;

    @Override
    public final void beginDomainLifeCycle() {
        super.beginDomainLifeCycle();
        // The the initial status is preparing
        this.status = ExecutionStatus.preparing;
    }

    public final void prepare() {
        BizAssert.check(
                ExecutionStatus.preparing == status,
                buildStatusAssertMsg(ExecutionStatus.preparing)
        );
        // For prepare action, transit the status from preparing to preparing
        this.status = ExecutionStatus.preparing;
        this.statusFlow.add(this.status);
        this.startedAt = new Date();
        this.refreshLastUpdated();
    }

    public final void onPreparationSucceeded() {
        BizAssert.check(
                ExecutionStatus.preparing == status,
                buildStatusAssertMsg(ExecutionStatus.preparing)
        );
        this.status = ExecutionStatus.prepared;
        this.statusFlow.add(this.status);
        this.refreshLastUpdated();
    }

    public final void startTestEngine() {
        BizAssert.check(
                ExecutionStatus.prepared == status,
                buildStatusAssertMsg(ExecutionStatus.prepared)
        );
        this.status = ExecutionStatus.running;
        this.statusFlow.add(this.status);
        this.refreshLastUpdated();
    }

    public final void onExecutionCompleted() {
        BizAssert.check(
                ExecutionStatus.running == status,
                buildStatusAssertMsg(ExecutionStatus.running)
        );
        this.status = ExecutionStatus.completed;
        this.statusFlow.add(this.status);
        this.endedAt = new Date();
        this.refreshLastUpdated();
    }

    public final void stop() {
        BizAssert.check(
                !isEnded(),
                buildStatusAssertMsg("not-ended")
        );
        this.status = ExecutionStatus.stopping;
        this.statusFlow.add(this.status);
        this.refreshLastUpdated();
    }

    public final void onStopSucceeded() {
        BizAssert.check(
                ExecutionStatus.stopping == this.status,
                buildStatusAssertMsg(ExecutionStatus.stopping)
        );
        this.status = ExecutionStatus.stopped;
        this.statusFlow.add(this.status);
        this.endedAt = new Date();
        this.refreshLastUpdated();
    }

    public final void transitToFailedStatus() {
        this.status = ExecutionStatus.failed;
        this.statusFlow.add(this.status);
        this.endedAt = new Date();
        this.refreshLastUpdated();
    }

    @JsonIgnore
    public final boolean isEnded() {
        return ExecutionStatus.completed == this.status
                || ExecutionStatus.failed == this.status
                || ExecutionStatus.stopping == this.status
                || ExecutionStatus.stopped == this.status;
    }

    protected final String buildStatusAssertMsg(ExecutionStatus expectedStatus) {
        return buildStatusAssertMsg(expectedStatus.name());
    }

    protected final String buildStatusAssertMsg(ExecutionStatus expected1, ExecutionStatus... otherExpected) {
        StringBuilder expectedStatuses = new StringBuilder();
        expectedStatuses.append(expected1);
        if (otherExpected != null) {
            for (ExecutionStatus status : otherExpected) {
                expectedStatuses.append(",").append(status);
            }
        }
        return buildStatusAssertMsg(expectedStatuses.toString());
    }

    protected abstract String buildStatusAssertMsg(String expectedStatuses);

}
