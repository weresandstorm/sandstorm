package io.sandstorm.controller.domain.job;

import io.sandstorm.common.domain.model.EntityOrBuilder;
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

    public void mergeFrom(TestJob.Builder builder) {
        this.name = builder.name;
        this.remark = builder.remark;
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

    public static final class Builder extends EntityOrBuilder {
        private String name;
        private ObjectId scriptId;
        private String simulationToRun;
        private Optional<ObjectId> dataSetId = Optional.empty();
        private LoadProfile loadProfile;
        private ExecPlan execPlan;
        private String remark;

        private TestScript script;
        private DataSet dataSet;

        public String getName() {
            return name;
        }

        public ObjectId getScriptId() {
            return scriptId;
        }

        // This method is only for json deserializer
        public void setDataSetId(ObjectId dataSetId) {
            this.dataSetId = Optional.ofNullable(dataSetId);
        }

        public Optional<ObjectId> getDataSetId() {
            return dataSetId;
        }

        public void setScript(TestScript script) {
            this.script = script;
        }

        public void setDataSet(DataSet dataSet) {
            this.dataSet = dataSet;
        }

        public TestJob build() {
            TestJob testJob = new TestJob();
            super.copyProps(this, testJob);
            testJob.name = this.name;
            testJob.script = this.script;
            testJob.simulationToRun = this.simulationToRun;
            testJob.dataSet = this.dataSet;
            testJob.loadProfile = this.loadProfile;
            testJob.execPlan = this.execPlan;
            testJob.remark = this.remark;
            return testJob;
        }

        public void accept(Visitor visitor) {
            super.accept(visitor);
            visitor.v_name(name);
            visitor.v_scriptId(scriptId);
            visitor.v_simulationToRun(simulationToRun);
            visitor.v_dataSetId(dataSetId);
            visitor.v_remark(remark);

            visitor.v_loadProfile(loadProfile);
            if (loadProfile != null) {
                loadProfile.accept(visitor.loadProfileVisitor());
            }

            visitor.v_execPlan(execPlan);
            if (execPlan != null) {
                execPlan.accept(visitor.execPlanVisitor());
            }
        }

        public interface Visitor extends EntityOrBuilder.Visitor {
            void v_name(String name);

            void v_scriptId(ObjectId scriptId);

            void v_simulationToRun(String simulationToRun);

            void v_dataSetId(Optional<ObjectId> dataSetId);

            void v_loadProfile(LoadProfile loadProfile);

            void v_execPlan(ExecPlan execPlan);

            void v_remark(String remark);

            LoadProfile.Visitor loadProfileVisitor();

            ExecPlan.Visitor execPlanVisitor();
        }
    }

}
