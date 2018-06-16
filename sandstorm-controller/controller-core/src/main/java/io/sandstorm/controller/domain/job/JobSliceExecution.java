package io.sandstorm.controller.domain.job;

import io.sandstorm.common.BizAssert;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.LoadInjector;
import io.sandstorm.controller.domain.resource.TestScript;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "job_slice_executions", noClassnameStored = true)
public class JobSliceExecution extends BasicExecution {

    private ObjectId jobExecId;
    @Reference
    private LoadInjector injector;
    @Reference
    private TestScript script;
    private String simulationToRun;
    private DataSet.Slice dataSet;
    private LoadProfile.Slice loadProfile;

    private JobSliceExecution() {
    }

    public JobSliceExecution(ObjectId jobExecId,
                             LoadInjector injector,
                             TestScript script,
                             String simulationToRun,
                             DataSet.Slice dataSet,
                             LoadProfile.Slice loadProfile,
                             String creator) {
        this.jobExecId = jobExecId;
        this.injector = injector;
        this.script = script;
        this.simulationToRun = simulationToRun;
        this.dataSet = dataSet;
        this.loadProfile = loadProfile;
        this.creator = creator;
    }

    public ObjectId jobExecId() {
        return this.jobExecId;
    }

    public LoadInjector injector() {
        return this.injector;
    }

    public void onPreparationFailed(String failureMessage) {
        BizAssert.check(
                ExecutionStatus.preparing == status,
                buildStatusAssertMsg(ExecutionStatus.preparing)
        );
        this.failureInfo = failureMessage;
        transitToFailedStatus();
    }

    public void onExecutionFailed(String failureMessage) {
        BizAssert.check(
                ExecutionStatus.running == status,
                buildStatusAssertMsg(ExecutionStatus.running)
        );
        this.failureInfo = failureMessage;
        transitToFailedStatus();
    }

    public void onStopFailed(String failureMessage) {
        BizAssert.check(
                ExecutionStatus.stopping == this.status,
                buildStatusAssertMsg(ExecutionStatus.stopping)
        );
        // Here keep its status unchanged
        this.failureInfo = failureMessage;
        this.refreshLastUpdated();
    }

    @Override
    protected String buildStatusAssertMsg(String expectedStatuses) {
        return String.format(
                "Status-assert of JobSliceExecution [id:%s, jobExecId:%s] failed: expected %s, actual %s",
                id, jobExecId, expectedStatuses, status
        );
    }

}