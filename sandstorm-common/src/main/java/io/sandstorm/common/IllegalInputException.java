package io.sandstorm.common;

public class IllegalInputException extends ApplicationException {

    public IllegalInputException(String message) {
        super(new ResultCase(CaseCode.illegal_api_input, message));
    }

    public IllegalInputException(String message, Throwable cause) {
        super(new ResultCase(CaseCode.illegal_api_input, message), cause);
    }

    public IllegalInputException(CaseCode caseCode, String message) {
        super(caseCode, message);
    }

    public IllegalInputException(CaseCode caseCode, String message, Throwable cause) {
        super(caseCode, message, cause);
    }

}
