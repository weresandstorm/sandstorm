package io.sandstorm.common;

public class ApplicationException extends RuntimeException {

    private final ResultCase resultCase;

    public ApplicationException(CaseCode caseCode, String message) {
        this(new ResultCase(caseCode, message));
    }

    public ApplicationException(CaseCode caseCode, String message, Throwable cause) {
        this(new ResultCase(caseCode, message), cause);
    }

    public ApplicationException(ResultCase resultCase) {
        super(resultCase.message());
        this.resultCase = resultCase;
    }

    public ApplicationException(ResultCase resultCase, Throwable cause) {
        super(resultCase.message(), cause);
        this.resultCase = resultCase;
    }

    public ResultCase resultCase() {
        return resultCase;
    }

}
