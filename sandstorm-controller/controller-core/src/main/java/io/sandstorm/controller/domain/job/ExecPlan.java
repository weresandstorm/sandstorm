package io.sandstorm.controller.domain.job;

import io.sandstorm.common.domain.model.ValueObject;

import java.util.Optional;

public class ExecPlan implements ValueObject {

    private ExecMode execMode;
    private ExecRate execRate;

    public ExecMode execMode() {
        return execMode;
    }

    public Optional<ExecRate> execRate() {
        return Optional.ofNullable(execRate);
    }

    public void accept(Visitor visitor) {
        visitor.v_execMode(execMode);
        visitor.v_execRate(execRate, execMode);
        if (execRate != null) {
            execRate.accept(visitor.execRateVisitor());
        }
    }

    public interface Visitor {
        void v_execMode(ExecMode execMode);

        void v_execRate(ExecRate execRate, ExecMode execMode);

        ExecRate.Visitor execRateVisitor();
    }

    public enum ExecMode {
        on_demand, periodic
    }

    public static class ExecRate implements ValueObject {
        private Integer startMonth;
        private Integer monthInterval;
        private Integer lastDayOfWeek;
        private Integer hour;
        private Integer minute;

        public Integer startMonth() {
            return startMonth;
        }

        public Integer monthInterval() {
            return monthInterval;
        }

        public Integer lastDayOfWeek() {
            return lastDayOfWeek;
        }

        public Integer hour() {
            return hour;
        }

        public Integer minute() {
            return minute;
        }

        public void accept(Visitor visitor) {
            visitor.v_startMonth(startMonth);
            visitor.v_monthInterval(monthInterval);
            visitor.v_lastDayOfWeek(lastDayOfWeek);
            visitor.v_hour(hour);
            visitor.v_minute(minute);
        }

        public interface Visitor {
            void v_startMonth(Integer startMonth);

            void v_monthInterval(Integer monthInterval);

            void v_lastDayOfWeek(Integer lastDayOfWeek);

            void v_hour(Integer hour);

            void v_minute(Integer minute);
        }
    }
}
