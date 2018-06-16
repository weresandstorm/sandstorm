package io.sandstorm.agent.domain.model;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LocalLoadProfile {

    private long scnRepeatTimes;
    private List<UserInjectStep> userInjectSteps;
    private List<RpsThrotStep> rpsThrotSteps;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("scnRepeatTimes").append(scnRepeatTimes)
            .append("userInjectSteps").append(userInjectSteps)
            .append("rpsThrotSteps").append(rpsThrotSteps)
            .build();
    }

    public static class UserInjectStep {
        private long totalUsers;
        private long rateUps;
        private long duration;

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .append("totalUsers").append(totalUsers)
                .append("rateUps").append(rateUps)
                .append("duration").append(duration)
                .build();
        }
    }

    public static class RpsThrotStep {
        private long rampTime;
        private long toRps;
        private long holdTime;

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .append("rampTime").append(rampTime)
                .append("toRps").append(toRps)
                .append("holdTime").append(holdTime)
                .build();
        }
    }
}
