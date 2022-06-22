package com.lumanlab.authtestserver.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiFailResponse<T extends ErrorInfo> implements ApiResponse {

    private T error;

    public ApiFailResponse(T error) {
        this.error = error;
    }
}
