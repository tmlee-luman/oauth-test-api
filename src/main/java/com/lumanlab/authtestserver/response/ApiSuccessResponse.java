package com.lumanlab.authtestserver.response;

import lombok.Getter;

@Getter
public class ApiSuccessResponse<T> implements ApiResponse {

    private final T body;

    private final String message;

    public ApiSuccessResponse(T body) {
        this(body, null);
    }

    public ApiSuccessResponse(String message) {
        this(null, message);
    }

    public ApiSuccessResponse(T body, String message) {
        this.body = body;
        this.message = message;
    }
}
