package com.lumanlab.authtestserver.exception;

import com.lumanlab.authtestserver.exception.layer.AccessDeniedException;
import org.springframework.http.HttpStatus;

public class InternalServerException extends AccessDeniedException {

    private static final String MESSAGE = "내부 서버 오류입니다.";

    public InternalServerException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST, ErrorCode.A211);
    }
}
