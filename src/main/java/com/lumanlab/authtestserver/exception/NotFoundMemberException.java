package com.lumanlab.authtestserver.exception;

public class NotFoundMemberException extends RuntimeException {

    private static final String MESSAGE = "회원을 찾을 수 없습니다.";

    public NotFoundMemberException() {
        super(MESSAGE);
    }
}
