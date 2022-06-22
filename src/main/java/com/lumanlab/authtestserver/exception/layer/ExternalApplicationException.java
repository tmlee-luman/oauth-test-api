package com.lumanlab.authtestserver.exception.layer;

public abstract class ExternalApplicationException extends RuntimeException {

    public ExternalApplicationException(String message) {
        super(message);
    }

    public ExternalApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
