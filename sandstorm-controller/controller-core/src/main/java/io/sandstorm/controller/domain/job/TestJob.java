package io.sandstorm.controller.domain.job;

import io.sandstorm.common.domain.model.EntityOrBuilder;
import io.sandstorm.controller.app.TestJobCmd;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.TestScript;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.Optional;

@Entity(value = "test_jobs", noClassnameStored = true)
public class TestJob extends EntityOrBuilder {

    private String name;
    @Reference
    private TestScript script;
    private String simulationToRun;
    @Reference
    private DataSet dataSet;
    private LoadProfile loadProfile;
    private ExecPlan execPlan;
    private String remark;

    private TestJob() {
    }

    public void mergeFrom(TestJobCmd.Update update) {
        this.name = update.name();
        this.remark = update.remark();
        refreshLastUpdated();
    }

    public String name() {
        return this.name;
    }

    public Optional<DataSet> dataSet() {
        return Optional.ofNullable(dataSet);
    }

    public LoadProfile loadProfile() {
        return this.loadProfile;
    }

    public ExecPlan execPlan() {
        return this.execPlan;
    }

    public boolean isPeriodic() {
        return (execPlan().execMode() == ExecPlan.ExecMode.periodic);
    }

    public JobExecution deriveJobExec(RunJobCmd command) {
        JobExecution jobExec = new JobExecution(
                this.id(),
                this.script,
                this.simulationToRun,
                this.dataSet,
                command.loadProfile(),
                command.execMode(),
                command.operator());
        return jobExec;
    }
    public void setScript(TestScript testScript){
        this.script = testScript;
    }
    public void setDataSet(DataSet dataSet){
        this.dataSet = dataSet;
    }

}
