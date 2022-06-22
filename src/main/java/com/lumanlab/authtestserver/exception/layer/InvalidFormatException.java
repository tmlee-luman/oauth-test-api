package com.lumanlab.authtestserver.exception.layer;

import com.lumanlab.authtestserver.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class InvalidFormatException extends ApplicationException {

    private final HttpStatus httpStatus;

    private final ErrorCode errorCode;

    public InvalidFormatException(
            String message,
            HttpStatus httpStatus,
            ErrorCode errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public InvalidFormatException(
            String message,
            Throwable throwable,
            HttpStatus httpStatus,
            ErrorCode errorCode) {
        super(message, throwable);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
