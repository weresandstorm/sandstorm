package io.sandstorm.agent.domain;

public class JobResourceException extends RuntimeException {

    public JobResourceException() {
        super();
    }

    public JobResourceException(String message) {
        super(message);
    }

    public JobResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
