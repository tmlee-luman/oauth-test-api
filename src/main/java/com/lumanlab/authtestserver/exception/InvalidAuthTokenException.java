package com.lumanlab.authtestserver.exception;

public class InvalidAuthTokenException extends RuntimeException {
    private static final String MESSAGE = "소셜 토큰 유효성 검증에 실패했습니다.";

    public InvalidAuthTokenException() {
        super(MESSAGE);
    }
}
