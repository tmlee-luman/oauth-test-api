package com.lumanlab.authtestserver.security.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lumanlab.authtestserver.exception.ErrorCode;
import com.lumanlab.authtestserver.response.ApiResponseCreator;
import com.lumanlab.authtestserver.response.ErrorInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(
                response.getOutputStream(),
                ApiResponseCreator.createFailResponse(
                        new ErrorInfo(
                                ErrorCode.A211,
                                ErrorCode.A211.getCause(),
                                authException.getMessage()
                        )
                )
        );
    }
}
