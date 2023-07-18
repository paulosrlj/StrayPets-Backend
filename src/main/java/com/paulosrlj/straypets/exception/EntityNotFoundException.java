package com.paulosrlj.straypets.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Long entityId) {
        super(String.format("A entidade de id %d não existe.", entityId));
    }

}
