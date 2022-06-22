package com.lumanlab.authtestserver.exception.layer;

import com.lumanlab.authtestserver.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class DataPersistenceException extends ApplicationException {

    private final HttpStatus httpStatus;

    private final ErrorCode errorCode;

    public DataPersistenceException(
            String message,
            HttpStatus httpStatus,
            ErrorCode errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public DataPersistenceException(
            String message,
            Throwable cause,
            HttpStatus httpStatus,
            ErrorCode errorCode) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
