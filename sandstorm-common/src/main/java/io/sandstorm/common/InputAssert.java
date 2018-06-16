package io.sandstorm.common;

import java.util.Collection;
import java.util.Map;

public final class InputAssert {

    private InputAssert() {
    }

    public static void notNull(Object value, String valueDesc) {
        if (!ValueChecks.notNull(value)) {
            throw new IllegalInputException(String.format("%s is null", valueDesc));
        }
    }

    public static void notBlank(String value, String valueDesc) {
        if (!ValueChecks.notBlank(value)) {
            throw new IllegalInputException(String.format("%s is blank", valueDesc));
        }
    }

    public static void notBlank(String value, String msgTemplate, Object... args) {
        if (!ValueChecks.notBlank(value)) {
            throw new IllegalInputException(String.format(msgTemplate, args));
        }
    }

    public static void notEqual(String value, String valueDesc, String notEqVal) {
        if (!ValueChecks.notEqual(value, notEqVal)) {
            throw new IllegalInputException(String.format("%s must not equal to %s", valueDesc, notEqVal));
        }
    }

    public static void lenLte(String value, String valueDesc, int maxLen) {
        if (!ValueChecks.lenLte(value, maxLen)) {
            throw new IllegalInputException(
                    String.format("Length of %s must be less than or equal to %s", valueDesc, maxLen));
        }
    }

    public static void gt(long value, String valueDesc, long lowerBound) {
        if (!ValueChecks.gt(value, lowerBound)) {
            throw new IllegalInputException(
                    String.format("%s must be greater than %s", valueDesc, lowerBound));
        }
    }

    public static void gte(long value, String valueDesc, long lowerBound) {
        if (!ValueChecks.gte(value, lowerBound)) {
            throw new IllegalInputException(
                    String.format("%s must be greater than or equal to %s", valueDesc, lowerBound));
        }
    }

    public static void range(long value, String valueDesc, long lowerBound, long upperBound) {
        if (!ValueChecks.range(value, lowerBound, upperBound)) {
            throw new IllegalInputException(
                    String.format("%s must be in range of %s to %s", valueDesc, lowerBound, upperBound));
        }
    }

    public static void isPositiveInt(String value, String valueDesc) {
        if (!ValueChecks.positiveInt(value)) {
            throw new IllegalInputException(String.format("%s is negative integer", valueDesc));
        }
    }

    public static void notEmpty(Collection<?> value, String valueDesc) {
        if (!ValueChecks.notEmpty(value)) {
            throw new IllegalInputException(String.format("%s is null or empty", valueDesc));
        }
    }

    public static void notEmpty(Map<?, ?> value, String valueDesc) {
        if (!ValueChecks.notEmpty(value)) {
            throw new IllegalInputException(String.format("%s is null or empty", valueDesc));
        }
    }

    public static void letterNumHyphen_(String value, String valueDesc) {
        if (!ValueChecks.letterNumHyphen_(value)) {
            throw new IllegalInputException(
                    String.format("%s must consist of letters, numbers, -, _", valueDesc));
        }
    }

    public static void className(String value, String valueDesc) {
        if (!ValueChecks.className(value)) {
            throw new IllegalInputException(
                    String.format("%s [%s] isn't a valid class name", valueDesc, value));
        }
    }

    public static void fileNameAndExt(String value, String valueDesc) {
        if (!ValueChecks.fileNameAndExt(value)) {
            throw new IllegalInputException(
                    String.format("%s must match pattern '<name>.<extension>'; "
                            + "name consists of a-z, A-Z, 1-9, _ and -; "
                            + "extension consists of a-z, A-Z, and 1-9", valueDesc));
        }
    }

    public static void ipv4(String value, String valueDesc) {
        if (!ValueChecks.ipv4(value)) {
            throw new IllegalInputException(
                    String.format("%s isn't a ipv4 address", valueDesc));
        }
    }

    public static void assertTrue(boolean expr, String msgTemplate, Object... args) {
        if (!expr) {
            throw new IllegalInputException(String.format(msgTemplate, args));
        }
    }

}
