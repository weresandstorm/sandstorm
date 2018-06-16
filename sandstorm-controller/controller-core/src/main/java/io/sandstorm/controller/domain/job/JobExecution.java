package io.sandstorm.controller.domain.job;

import io.sandstorm.common.BizAssert;
import io.sandstorm.common.ParamAssert;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.LoadInjector;
import io.sandstorm.controller.domain.resource.TestScript;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Entity(value = "job_executions", noClassnameStored = true)
public class JobExecution extends BasicExecution {

    private ObjectId jobId;
    @Reference
    private TestScript script;
    private String simulationToRun;
    @Reference
    private DataSet dataSet;
    private LoadProfile loadProfile;
    private ExecPlan.ExecMode execMode;
    private String reportUrl;

    private JobExecution() {
    }

    public JobExecution(ObjectId jobId,
                        TestScript script,
                        String simulationToRun,
                        DataSet dataSet,
                        LoadProfile loadProfile,
                        ExecPlan.ExecMode execMode,
                        String creator) {
        this.jobId = jobId;
        this.script = script;
        this.simulationToRun = simulationToRun;
        this.dataSet = dataSet;
        this.loadProfile = loadProfile;
        this.loadProfile.slice();
        this.execMode = execMode;
        this.creator = creator;
    }

    public List<JobSliceExecution> deriveJobSliceExec(
            List<LoadInjector> injectors,
            List<DataSet.Slice> dataSlices) {
        List<JobSliceExecution> jobSliceExecs = new ArrayList<>(injectors.size());
        for (int i = 0; i < injectors.size(); i++) {
            DataSet.Slice dataSlice = null;
            if (!CollectionUtils.isEmpty(dataSlices)) {
                dataSlice = dataSlices.get(i);
            }

            jobSliceExecs.add(new JobSliceExecution(
                    this.id(),
                    injectors.get(i),
                    this.script,
                    this.simulationToRun,
                    dataSlice,
                    this.loadProfile.loadProfilePerInjector(),
                    this.creator));
        }
        return jobSliceExecs;
    }

    public LoadProfile loadProfile() {
        return this.loadProfile;
    }

    public long slicesCount() {
        return this.loadProfile.totalInjectors();
    }

    public void recordFailureInfo(String failureInfo) {
        this.failureInfo = failureInfo;
        this.refreshLastUpdated();
    }

    public void onPreparationOrExecFailed() {
        BizAssert.check(
                (ExecutionStatus.preparing == status
                        || ExecutionStatus.prepared == status
                        || ExecutionStatus.running == status),
                buildStatusAssertMsg(
                        ExecutionStatus.preparing,
                        ExecutionStatus.prepared,
                        ExecutionStatus.running)
        );
        transitToFailedStatus();
    }

    public void onStopFailed() {
        BizAssert.check(
                ExecutionStatus.stopping == this.status,
                buildStatusAssertMsg(ExecutionStatus.stopping)
        );
        // Here keep the status unchanged
        this.refreshLastUpdated();
    }

    public void onReportGenerated(String reportUrl) {
        ParamAssert.notBlank(reportUrl, "reportUrl");
        this.reportUrl = reportUrl;
        this.refreshLastUpdated();
    }

    @Override
    protected String buildStatusAssertMsg(String expectedStatuses) {
        return String.format(
                "Status-assert of JobExecution [id:%s] failed: expected %s, actual %s",
                id, expectedStatuses, status
        );
    }
}
