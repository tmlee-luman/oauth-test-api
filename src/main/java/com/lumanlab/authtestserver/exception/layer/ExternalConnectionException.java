package com.lumanlab.authtestserver.exception.layer;

import com.lumanlab.authtestserver.exception.ErrorCode;
import lombok.Getter;

@Getter
public abstract class ExternalConnectionException extends ExternalApplicationException {

    private final ErrorCode errorCode;

    public ExternalConnectionException(
            String message,
            ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ExternalConnectionException(
            String message,
            Throwable cause,
            ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
