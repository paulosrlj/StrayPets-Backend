package com.paulosrlj.straypets.exception;

public class PersistenseErrorException extends BusinessException {
    public PersistenseErrorException(String message) {
        super(message);
    }

    public PersistenseErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}

