package io.sandstorm.common;

import java.util.Collection;
import java.util.Map;

/**
 * Util for asserting validation rules of input parameters.
 */
public final class ParamAssert {

    private ParamAssert() {
    }

    public static void notBlank(String param, String paramDesc) {
        if (param == null || param.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s is null", paramDesc));
        }
    }

    public static void notNull(Object param, String paramDesc) {
        if (null == param) {
            throw new IllegalArgumentException(String.format("%s is null", paramDesc));
        }
    }

    public static void notEmpty(Collection<?> coll, String paramDesc) {
        if (null == coll || coll.isEmpty()) {
            throw new IllegalArgumentException(String.format("%s is null or empty", paramDesc));
        }
    }

    public static void notEmpty(Map<?, ?> map, String paramDesc) {
        if (null == map || map.isEmpty()) {
            throw new IllegalArgumentException(String.format("%s is null or empty", paramDesc));
        }
    }

}
