package io.sandstorm.agent.domain;

public class JobExecuteException extends RuntimeException {

    public JobExecuteException() {
        super();
    }

    public JobExecuteException(String message) {
        super(message);
    }

    public JobExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobExecuteException(Throwable cause) {
        super(cause);
    }
}
