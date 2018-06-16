package io.sandstorm.controller.domain.job;

import io.sandstorm.common.AppInternalException;

public class JobSchedulingException extends AppInternalException {

    public JobSchedulingException(String message) {
        super(message);
    }

    public JobSchedulingException(String message, Throwable cause) {
        super(message, cause);
    }

}
