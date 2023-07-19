package com.paulosrlj.straypets.exception;

public class EntityAlreadyExists extends BusinessException {
    public EntityAlreadyExists(String message) {
        super(message);
    }

    public EntityAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyExists(Long entityId) {
        super(String.format("A entidade de id %d já existe.", entityId));
    }

}
