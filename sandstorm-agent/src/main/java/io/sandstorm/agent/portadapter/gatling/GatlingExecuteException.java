package io.sandstorm.agent.portadapter.gatling;

public class GatlingExecuteException extends RuntimeException {

    public GatlingExecuteException() {
        super();
    }

    public GatlingExecuteException(String message) {
        super(message);
    }

    public GatlingExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatlingExecuteException(Throwable cause) {
        super(cause);
    }

}
