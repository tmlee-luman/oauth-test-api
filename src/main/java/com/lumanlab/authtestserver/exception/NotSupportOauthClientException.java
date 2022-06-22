package com.lumanlab.authtestserver.exception;

public class NotSupportOauthClientException extends RuntimeException {

    private static final String MESSAGE = "지원하지않는 소셜로그인 요청입니다.";

    public NotSupportOauthClientException() {
        super(MESSAGE);
    }
}
