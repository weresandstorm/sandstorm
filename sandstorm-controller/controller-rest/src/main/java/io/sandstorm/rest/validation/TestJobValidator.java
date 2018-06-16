package io.sandstorm.rest.validation;

import io.sandstorm.common.ValueChecks;
import io.sandstorm.controller.domain.job.ExecPlan;
import io.sandstorm.controller.domain.job.LoadProfile;
import io.sandstorm.controller.domain.job.TestJob;
import org.bson.types.ObjectId;

import java.util.Optional;

import static io.sandstorm.common.InputAssert.*;

public class TestJobValidator extends BasicValidator implements TestJob.Builder.Visitor {

    public static final TestJobValidator object = new TestJobValidator();

    private static final int NAME_MAX_LEN = 30;
    private static final int REMARK_MAX_LEN = 80;

    private TestJobValidator() {
    }

    @Override
    public void v_name(String name) {
        notBlank(name, "name");
        lenLte(name, "name", NAME_MAX_LEN);
    }

    @Override
    public void v_scriptId(ObjectId scriptId) {
        notNull(scriptId, "scriptId");
    }

    @Override
    public void v_simulationToRun(String simulationToRun) {
        String valueDesc = "simulationToRun";
        notBlank(simulationToRun, valueDesc);
        className(simulationToRun, valueDesc);
    }

    @Override
    public void v_dataSetId(Optional<ObjectId> dataSetId) {
        // Allow dataSetId to be absent
    }

    @Override
    public void v_remark(String remark) {
        if (ValueChecks.notBlank(remark)) {
            lenLte(remark, "remark", REMARK_MAX_LEN);
        }
    }

    @Override
    public void v_loadProfile(LoadProfile loadProfile) {
        notNull(loadProfile, "loadProfile");
    }

    @Override
    public LoadProfile.Visitor loadProfileVisitor() {
        return LoadProfileValidator.object;
    }

    @Override
    public void v_execPlan(ExecPlan execPlan) {
        notNull(execPlan, "execPlan");
    }

    @Override
    public ExecPlan.Visitor execPlanVisitor() {
        return ExecPlanVisitor.object;
    }

    private static class ExecPlanVisitor implements ExecPlan.Visitor {

        static final ExecPlanVisitor object = new ExecPlanVisitor();

        @Override
        public void v_execMode(ExecPlan.ExecMode execMode) {
            notNull(execMode, "execMode");
        }

        @Override
        public void v_execRate(ExecPlan.ExecRate execRate, ExecPlan.ExecMode execMode) {
            if (ExecPlan.ExecMode.periodic == execMode) {
                notNull(execRate, "execRate");
            }
        }

        @Override
        public ExecPlan.ExecRate.Visitor execRateVisitor() {
            return ExecRateVisitor.object;
        }
    }

    private static class ExecRateVisitor implements ExecPlan.ExecRate.Visitor {

        static final ExecRateVisitor object = new ExecRateVisitor();

        @Override
        public void v_startMonth(Integer startMonth) {
            notNull(startMonth, "startMonth");
            range(startMonth, "startMonth", 1, 12);
        }

        @Override
        public void v_monthInterval(Integer monthInterval) {
            notNull(monthInterval, "monthInterval");
            range(monthInterval, "monthInterval", 1, 12);
        }

        @Override
        public void v_lastDayOfWeek(Integer lastDayOfWeek) {
            notNull(lastDayOfWeek, "lastDayOfWeek");
            range(lastDayOfWeek, "lastDayOfWeek", 1, 7);
        }

        @Override
        public void v_hour(Integer hour) {
            notNull(hour, "hour");
            range(hour, "hour", 0, 23);
        }

        @Override
        public void v_minute(Integer minute) {
            notNull(minute, "minute");
            range(minute, "minute", 0, 59);
        }
    }
}
