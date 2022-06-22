package com.lumanlab.authtestserver.response;


import com.lumanlab.authtestserver.exception.NotInstanceException;

public final class ApiResponseCreator {

    private ApiResponseCreator() {
        throw new NotInstanceException();
    }

    public static ApiResponse createSuccessResponse() {
        return new ApiSuccessResponse<>(null);
    }

    public static ApiResponse createSuccessResponse(String message) {
        return new ApiSuccessResponse<>(message);
    }

    public static <T> ApiResponse createSuccessResponse(T body) {
        return new ApiSuccessResponse<>(body);
    }

    public static <T> ApiResponse createSuccessResponse(T body, String message) {
        return new ApiSuccessResponse<>(body, message);
    }

    public static <T extends ErrorInfo> ApiResponse createFailResponse(T errorInfo) {
        return new ApiFailResponse<>(errorInfo);
    }
}
