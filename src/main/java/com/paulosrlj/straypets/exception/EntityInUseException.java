package com.paulosrlj.straypets.exception;

public class EntityInUseException extends RuntimeException{
    public EntityInUseException(String message) {
        super(message);
    }

    public EntityInUseException(String message, Throwable cause) {
        super(message, cause);
    }
}
