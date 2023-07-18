package com.paulosrlj.straypets.exception;

public class JwtException extends BusinessException {
    public JwtException(String message) {
        super(message);
    }

    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
