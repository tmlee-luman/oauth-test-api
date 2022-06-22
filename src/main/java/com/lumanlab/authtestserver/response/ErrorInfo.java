package com.lumanlab.authtestserver.response;

import com.lumanlab.authtestserver.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorInfo {

    private ErrorCode errorCode;

    private String message;

    private String cause;

    public ErrorInfo(
            ErrorCode errorCode,
            String message,
            String cause) {
        this.errorCode = errorCode;
        this.message = message;
        this.cause = cause;
    }
}
