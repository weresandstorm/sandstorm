package io.sandstorm.common;

/**
 * Util for asserting business constraint conditions
 */
public final class BizAssert {

    private BizAssert() {
    }

    /**
     * Asserts that the given condition is met, otherwise throw a ViolateBizConstraintException with the given message.
     *  @param condition the constraint condition to check
     * @param message        the exception message to use if the check fails
     */
    public static final void check(boolean condition, String message) {
        if (!condition) {
            throw new ViolateBizConstraintException(message);
        }
    }

    /**
     * Asserts that the given condition is met, otherwise throw a ViolateBizConstraintException with the given message.
     *  @param condition the constraint condition to check
     * @param msgTemplate    a template for the exception message if the check fail
     * @param msgArgs        the arguments to be substituted into the message template.
     */
    public static final void check(boolean condition, String msgTemplate, Object... msgArgs) {
        check(condition, String.format(msgTemplate, msgArgs));
    }

    public static final void check(boolean condition, CaseCode caseCode, String message) {
        if (!condition) {
            throw new ViolateBizConstraintException(caseCode, message);
        }
    }

    public static final void check(boolean condition, CaseCode caseCode, String msgTemplate, Object... msgArgs) {
        check(condition, caseCode, String.format(msgTemplate, msgArgs));
    }

}
