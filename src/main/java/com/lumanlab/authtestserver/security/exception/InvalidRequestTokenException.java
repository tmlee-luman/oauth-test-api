package com.lumanlab.authtestserver.security.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidRequestTokenException extends AuthenticationException {

    private static final String MESSAGE = "잘못된 토큰입니다.";

    public InvalidRequestTokenException() {
        super(MESSAGE);
    }
}
