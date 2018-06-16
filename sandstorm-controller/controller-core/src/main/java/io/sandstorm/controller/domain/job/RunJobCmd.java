package io.sandstorm.controller.domain.job;

import org.bson.types.ObjectId;

import static io.sandstorm.common.InputAssert.notNull;

/**
 * A command to run a {@link TestJob}
 */
public class RunJobCmd {

    private ObjectId jobId;
    private ExecPlan.ExecMode execMode = ExecPlan.ExecMode.on_demand;
    private LoadProfile loadProfile;
    private String operator;

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
    }

    public void setExecMode(ExecPlan.ExecMode execMode) {
        this.execMode = execMode;
    }

    public void setLoadProfile(LoadProfile loadProfile) {
        this.loadProfile = loadProfile;
    }

    public ObjectId jobId() {
        return jobId;
    }

    public ExecPlan.ExecMode execMode() {
        return execMode;
    }

    public LoadProfile loadProfile() {
        return loadProfile;
    }

    public String operator() {
        return this.operator;
    }

    public void validate(LoadProfile.Visitor validator) {
        notNull(jobId,"jobId");
        notNull(execMode,"execMode");
        notNull(loadProfile, "loadProfile");
        loadProfile.accept(validator);
        notNull(operator, "operator");
    }

}
