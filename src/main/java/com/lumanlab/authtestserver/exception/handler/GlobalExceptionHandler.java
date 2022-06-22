package com.lumanlab.authtestserver.exception.handler;


import com.lumanlab.authtestserver.exception.layer.*;
import com.lumanlab.authtestserver.response.ApiResponse;
import com.lumanlab.authtestserver.response.ApiResponseCreator;
import com.lumanlab.authtestserver.response.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(
                        ApiResponseCreator.createFailResponse(
                                new ErrorInfo(
                                        e.getErrorCode(),
                                        e.getMessage(),
                                        e.getClass().getName()))
                );
    }

    @ExceptionHandler(DataPersistenceException.class)
    public ResponseEntity<ApiResponse> handleDataPersistenceException(DataPersistenceException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(
                        ApiResponseCreator.createFailResponse(
                                new ErrorInfo(
                                        e.getErrorCode(),
                                        e.getMessage(),
                                        e.getClass().getName()))
                );
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ApiResponse> handleInvalidFormatException(InvalidFormatException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(
                        ApiResponseCreator.createFailResponse(
                                new ErrorInfo(
                                        e.getErrorCode(),
                                        e.getMessage(),
                                        e.getClass().getName()))
                );
    }

    @ExceptionHandler(ExternalConnectionException.class)
    public ResponseEntity<ApiResponse> handleExternalConnectionException(ExternalConnectionException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiResponseCreator.createFailResponse(
                                new ErrorInfo(
                                        e.getErrorCode(),
                                        e.getMessage(),
                                        e.getClass().getName()))
                );
    }

    @ExceptionHandler(InvalidRequestValueException.class)
    public ResponseEntity<ApiResponse> handleInvalidRequestValueException(InvalidRequestValueException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(
                        ApiResponseCreator.createFailResponse(
                                new ErrorInfo(
                                        e.getErrorCode(),
                                        e.getMessage(),
                                        e.getClass().getName()))
                );
    }
}
