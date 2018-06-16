package io.sandstorm.rest.validation;

import io.sandstorm.controller.domain.job.LoadProfile;
import io.sandstorm.controller.domain.job.RpsThrotStep;
import io.sandstorm.controller.domain.job.UserInjectStep;

import java.util.List;

import static io.sandstorm.common.InputAssert.*;

public final class LoadProfileValidator implements LoadProfile.Visitor {

    public static final LoadProfileValidator object = new LoadProfileValidator();

    private LoadProfileValidator() {}

    @Override
    public void v_totalInjectors(Long totalInjectors) {
        notNull(totalInjectors, "totalInjectors");
        gt(totalInjectors, "totalInjectors", 0);
    }

    @Override
    public void v_scnRepeatTimes(Long scnRepeatTimes) {
        notNull(scnRepeatTimes, "scnRepeatTimes");
        gte(scnRepeatTimes, "scnRepeatTimes", 0);
    }

    @Override
    public void v_totalUsers(Long totalUsers) {
        notNull(totalUsers, "totalUsers");
        gt(totalUsers, "totalUsers", 0);
    }

    @Override
    public void v_maxRps(Long maxRps) {
        notNull(maxRps, "maxRps");
        gte(maxRps, "maxRps", 0);
    }

    @Override
    public void v_userInjectSteps(List<UserInjectStep> userInjectSteps) {
        notEmpty(userInjectSteps, "userInjectSteps");
    }

    @Override
    public void v_rpsThrotSteps(List<RpsThrotStep> rpsThrotSteps) {
        // Allow rpsThrotSteps to be absent
    }

    @Override
    public UserInjectStep.Visitor userInjectStepVisitor() {
        return UserInjectStepValidator.object;
    }

    @Override
    public RpsThrotStep.Visitor rpsThrotStepVisitor() {
        return RpsThrotStepValidator.object;
    }

    private static class UserInjectStepValidator implements UserInjectStep.Visitor {

        static final UserInjectStepValidator object = new UserInjectStepValidator();

        @Override
        public void v_totalUsers(Long totalUsers) {
            notNull(totalUsers, "totalUsers");
            gt(totalUsers, "totalUsers", 0);
        }

        @Override
        public void v_rateUps(Long rateUps) {
            notNull(rateUps, "rateUps");
            gte(rateUps, "rateUps", 0);
        }

        @Override
        public void v_duration(Long duration) {
            notNull(duration, "duration");
            gte(duration, "duration", 0);
        }
    }

    private static class RpsThrotStepValidator implements RpsThrotStep.Visitor {

        static final RpsThrotStepValidator object = new RpsThrotStepValidator();

        @Override
        public void v_rampTime(Long rampTime) {
            notNull(rampTime, "rampTime");
            gte(rampTime, "rampTime", 0);
        }

        @Override
        public void v_toRps(Long toRps) {
            notNull(toRps, "toRps");
            gt(toRps, "toRps", 0);
        }

        @Override
        public void v_holdTime(Long holdTime) {
            notNull(holdTime, "holdTime");
            gt(holdTime, "holdTime", 0);
        }
    }
}
