package com.lumanlab.authtestserver.exception;

import com.lumanlab.authtestserver.exception.layer.InvalidRequestValueException;
import org.springframework.http.HttpStatus;

public class InvalidAuthTokenException extends InvalidRequestValueException {
    private static final String MESSAGE = "소셜 토큰 유효성 검증에 실패했습니다.";

    public InvalidAuthTokenException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST, ErrorCode.A120);
    }
}
