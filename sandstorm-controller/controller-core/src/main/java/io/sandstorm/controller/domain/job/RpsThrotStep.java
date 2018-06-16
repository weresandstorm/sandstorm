package io.sandstorm.controller.domain.job;

import io.sandstorm.common.domain.model.ValueObject;
import io.sandstorm.controller.domain.job.util.Maths;

public class RpsThrotStep implements ValueObject {

    private Long rampTime;
    private Long toRps;
    private Long holdTime;

    private RpsThrotStep() {
    }

    public RpsThrotStep slice(long numInjectors) {
        RpsThrotStep aSlice = new RpsThrotStep();
        aSlice.rampTime = this.rampTime;
        aSlice.toRps = Maths.divideAndCeil(this.toRps, numInjectors);
        aSlice.holdTime = this.holdTime;
        return aSlice;
    }

    public void accept(Visitor visitor) {
        visitor.v_rampTime(rampTime);
        visitor.v_toRps(toRps);
        visitor.v_holdTime(holdTime);
    }

    public Long totalTime() {
        return (rampTime + holdTime);
    }

    public interface Visitor {
        void v_rampTime(Long rampTime);

        void v_toRps(Long toRps);

        void v_holdTime(Long holdTime);
    }
}
