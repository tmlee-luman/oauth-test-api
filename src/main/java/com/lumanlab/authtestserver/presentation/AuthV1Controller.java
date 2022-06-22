package com.lumanlab.authtestserver.presentation;

import com.lumanlab.authtestserver.infrastructure.OauthProvider;
import com.lumanlab.authtestserver.response.ApiResponse;
import com.lumanlab.authtestserver.response.ApiResponseCreator;
import com.lumanlab.authtestserver.service.AuthService;
import com.lumanlab.authtestserver.service.dto.response.OauthLoginResponse;
import com.lumanlab.authtestserver.service.dto.response.ReIssueTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/v1")
@Controller
@RequiredArgsConstructor
public class AuthV1Controller {

    private static final String BEARER_HEADER = "Bearer ";

    private static final String REFRESH_TOKEN_HEADER = "refreshToken";

    private final AuthService authService;

    @PostMapping ("/oauth/{oauthProvider}/login")
    public ResponseEntity<ApiResponse> loginOauth(
            @PathVariable OauthProvider oauthProvider,
            HttpServletRequest request) {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION)
                .substring(BEARER_HEADER.length());

        OauthLoginResponse oauthLoginResponse = authService.loginOauth(accessToken, oauthProvider);
        return ResponseEntity.ok(ApiResponseCreator.createSuccessResponse(oauthLoginResponse));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<ApiResponse> reissueToken(HttpServletRequest request) {
        ReIssueTokenResponse reIssueTokenResponse = authService.reissueToken(request.getHeader(REFRESH_TOKEN_HEADER));
        return ResponseEntity.ok(ApiResponseCreator.createSuccessResponse(reIssueTokenResponse));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest request) {
        authService.logout(request.getHeader(REFRESH_TOKEN_HEADER));

        return ResponseEntity.ok()
                .build();
    }

}
