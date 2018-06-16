package io.sandstorm.controller.domain.job.util;

public final class Maths {

    private Maths() {
    }

    public static long divideAndCeil(long dividend, long divisor) {
        return (long) Math.ceil(dividend * 1.0d / divisor);
    }

}
