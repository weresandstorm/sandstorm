package io.sandstorm.controller.domain.job;

import io.sandstorm.common.domain.model.ValueObject;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LoadProfile implements ValueObject {

    private Long totalInjectors;
    private Long scnRepeatTimes;
    private Long totalUsers;
    private Long maxRps;
    private List<UserInjectStep> userInjectSteps = Collections.emptyList();
    private List<RpsThrotStep> rpsThrotSteps = Collections.emptyList();
    private Slice loadProfilePerInjector;

    public Long totalInjectors() {
        return this.totalInjectors;
    }

    public Slice loadProfilePerInjector() {
        return this.loadProfilePerInjector;
    }

    public void slice() {
        Slice slice = new Slice();
        slice.scnRepeatTimes = this.scnRepeatTimes;
        slice.userInjectSteps = this.userInjectSteps.stream()
                .map(item -> item.slice(totalInjectors))
                .collect(Collectors.toList());
        slice.rpsThrotSteps = this.rpsThrotSteps.stream()
                .map(item -> item.slice(totalInjectors))
                .collect(Collectors.toList());
        this.loadProfilePerInjector = slice;
    }

    public void accept(Visitor visitor) {
        visitor.v_totalInjectors(totalInjectors);
        visitor.v_scnRepeatTimes(scnRepeatTimes);
        visitor.v_totalUsers(totalUsers);
        visitor.v_maxRps(maxRps);
        visitor.v_userInjectSteps(userInjectSteps);
        visitor.v_rpsThrotSteps(rpsThrotSteps);

        assert (this.userInjectSteps != null);
        for (UserInjectStep injectStep : userInjectSteps) {
            injectStep.accept(visitor.userInjectStepVisitor());
        }

        assert (this.rpsThrotSteps != null);
        for (RpsThrotStep rpsThrotStep : rpsThrotSteps) {
            rpsThrotStep.accept(visitor.rpsThrotStepVisitor());
        }
    }

    private static final long TWELVE_HOURS = 12 * 3600;
    private static final long EXEC_TIME_CORRECTION_VAL = 30;

    public long totalExecutionTime() {
        long total = 0;
        if (CollectionUtils.isEmpty(rpsThrotSteps)) {
            // If throttling steps are not given, we can't estimate the total execution time
            // of the test job, so here 12 hours is a casual decision, and may be unreasonable.
            total = TWELVE_HOURS;
        } else {
            for (RpsThrotStep step : rpsThrotSteps) {
                total += step.totalTime();
            }

            // Add {EXEC_TIME_CORRECTION_VAL} seconds as a buffer in case that the actual execution time is a little
            // longer than the sum of all rps throttling steps
            total = total + EXEC_TIME_CORRECTION_VAL;
        }
        return total;
    }

    public interface Visitor {
        void v_totalInjectors(Long totalInjectors);

        void v_scnRepeatTimes(Long scnRepeatTimes);

        void v_totalUsers(Long totalUsers);

        void v_maxRps(Long maxRps);

        void v_userInjectSteps(List<UserInjectStep> userInjectSteps);

        void v_rpsThrotSteps(List<RpsThrotStep> rpsThrotSteps);

        UserInjectStep.Visitor userInjectStepVisitor();

        RpsThrotStep.Visitor rpsThrotStepVisitor();
    }

    public static final class Slice implements ValueObject {
        private Long scnRepeatTimes;
        private List<UserInjectStep> userInjectSteps = Collections.emptyList();
        private List<RpsThrotStep> rpsThrotSteps = Collections.emptyList();
    }
}
