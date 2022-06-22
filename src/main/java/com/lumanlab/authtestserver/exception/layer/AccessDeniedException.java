package com.lumanlab.authtestserver.exception.layer;

import com.lumanlab.authtestserver.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AccessDeniedException extends ApplicationException {

    private final HttpStatus httpStatus;

    private final ErrorCode errorCode;

    public AccessDeniedException(
            String message,
            HttpStatus httpStatus,
            ErrorCode errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public AccessDeniedException(
            String message,
            Throwable cause,
            HttpStatus httpStatus,
            ErrorCode errorCode) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
