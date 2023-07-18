package com.paulosrlj.straypets.exception;

public class StorageException extends BusinessException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
