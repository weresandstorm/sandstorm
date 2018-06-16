package io.sandstorm.common;

public final class ResultCase {

    private static final ResultCase ok = new ResultCase(CaseCode.ok, "ok");

    private final CaseCode code;
    private final String message;

    public ResultCase(CaseCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public static final ResultCase ok() {
        return ok;
    }

    public final CaseCode code() {
        return code;
    }

    public final String message() {
        return message;
    }
}
