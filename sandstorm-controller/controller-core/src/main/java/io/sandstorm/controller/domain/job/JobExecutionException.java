package io.sandstorm.controller.domain.job;

import io.sandstorm.common.AppInternalException;

public class JobExecutionException extends AppInternalException {

    public JobExecutionException(String message) {
        super(message);
    }

    public JobExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

}
