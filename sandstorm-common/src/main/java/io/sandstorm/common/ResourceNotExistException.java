package io.sandstorm.common;

/**
 * @author Loops .
 */
public class ResourceNotExistException extends ApplicationException {

    public ResourceNotExistException(String message) {
        super(new ResultCase(CaseCode.resource_not_exist, message));
    }

    public ResourceNotExistException(String message, Throwable cause) {
        super(new ResultCase(CaseCode.resource_not_exist, message), cause);
    }
}
