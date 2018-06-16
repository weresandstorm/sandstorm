package io.sandstorm.common;

/**
 * Represents errors which aren't caused by external input and are only raised because of happening something bad
 * within this application.
 */
public class AppInternalException extends ApplicationException {

    public AppInternalException(String message) {
        super(new ResultCase(CaseCode.internal_error, message));
    }

    public AppInternalException(String message, Throwable cause) {
        super(new ResultCase(CaseCode.internal_error, message), cause);
    }

}
