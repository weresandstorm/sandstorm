package io.sandstorm.controller.domain.job;

import io.sandstorm.common.domain.model.ValueObject;
import io.sandstorm.controller.domain.job.util.Maths;

public class UserInjectStep implements ValueObject {

    private Long totalUsers;
    private Long rateUps;
    private Long duration;

    private UserInjectStep() {
    }

    public UserInjectStep slice(long numInjectors) {
        UserInjectStep aSlice = new UserInjectStep();
        aSlice.totalUsers = Maths.divideAndCeil(this.totalUsers, numInjectors);
        aSlice.rateUps = Maths.divideAndCeil(this.rateUps, numInjectors);
        aSlice.duration = this.duration;
        return aSlice;
    }

    public void accept(Visitor visitor) {
        visitor.v_totalUsers(totalUsers);
        visitor.v_rateUps(rateUps);
        visitor.v_duration(duration);
    }

    public interface Visitor {
        void v_totalUsers(Long totalUsers);

        void v_rateUps(Long rateUps);

        void v_duration(Long duration);
    }

}
