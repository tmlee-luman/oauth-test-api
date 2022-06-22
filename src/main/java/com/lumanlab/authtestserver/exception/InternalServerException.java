package com.lumanlab.authtestserver.exception;

public class InternalServerException extends RuntimeException {

    private static final String MESSAGE = "내부 서버 오류입니다.";

    public InternalServerException() {
        super(MESSAGE);
    }
}
