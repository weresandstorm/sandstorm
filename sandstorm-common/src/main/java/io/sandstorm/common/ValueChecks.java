package io.sandstorm.common;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class ValueChecks {

    private ValueChecks() {}

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean notNull(Object value) {
        return value != null;
    }

    public static boolean isBlank(String value) {
        return isNull(value) || value.trim().isEmpty();
    }

    public static boolean notBlank(String value) {
        return (! isBlank(value));
    }

    public static boolean notEmpty(Collection<?> value) {
        return notNull(value) && !value.isEmpty();
    }

    public static boolean notEmpty(Map<?, ?> value) {
        return notNull(value) && !value.isEmpty();
    }

    public static boolean notEqual(String value, String notEqVal) {
        return (! notEqVal.equals(value));
    }

    public static boolean lenLte(String value, int maxLen) {
        return notNull(value) && value.length() <= maxLen;
    }

    public static boolean gt(long value, long lowerBound) {
        return value > lowerBound;
    }

    public static boolean gte(long value, long lowerBound) {
        return value >= lowerBound;
    }

    public static boolean range(long value, long lowerBound, long upperBound) {
        return (value >= lowerBound && value <= upperBound);
    }

    public static boolean positiveInt(String value) {
        return notBlank(value) && POSITIVE_INT.matcher(value).matches();
    }

    public static boolean letterNumHyphen_(String value) {
        return notBlank(value) && LETTER_NUM_HYPHEN_.matcher(value).matches();
    }

    public static boolean className(String value) {
        return notBlank(value) && CLASS_NAME.matcher(value).matches();
    }

    public static boolean fileNameAndExt(String value) {
        return notBlank(value) && FILE_NAME_EXT.matcher(value).matches();
    }

    public static boolean ipv4(String value) {
        return notBlank(value) && IPV4.matcher(value).matches();
    }

    private static final Pattern POSITIVE_INT = Pattern.compile("^[0-9]\\d*$");
    private static final Pattern LETTER_NUM_HYPHEN_ = Pattern.compile("^[a-zA-Z0-9_\\-]+$");
    private static final Pattern CLASS_NAME = Pattern.compile("^([a-zA-Z0-9_]+\\.)*[a-zA-Z0-9_]+$");
    private static final Pattern FILE_NAME_EXT = Pattern.compile("^[a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9]+)$");
    // Refer: https://www.safaribooksonline.com/library/view/regular-expressions-cookbook/9780596802837/ch07s16.html
    private static final Pattern IPV4 = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

    public static void main(String[] arg) {
        System.out.println(fileNameAndExt("data"));
        System.out.println(fileNameAndExt("data.zip"));
        System.out.println(fileNameAndExt("data.json.zip"));
    }
}
