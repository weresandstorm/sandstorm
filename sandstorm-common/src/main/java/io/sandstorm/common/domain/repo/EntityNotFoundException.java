package io.sandstorm.common.domain.repo;

import io.sandstorm.common.ResourceNotExistException;

public class EntityNotFoundException extends ResourceNotExistException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
