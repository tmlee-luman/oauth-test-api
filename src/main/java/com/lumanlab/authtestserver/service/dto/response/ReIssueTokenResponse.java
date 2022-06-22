package com.lumanlab.authtestserver.service.dto.response;

import com.lumanlab.authtestserver.domain.AccessToken;
import com.lumanlab.authtestserver.domain.RefreshToken;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReIssueTokenResponse {

    private String accessToken;

    private LocalDateTime accessTokenExpiredAt;

    private String refreshToken;

    private LocalDateTime refreshTokenExpiredAt;

    public ReIssueTokenResponse(AccessToken accessToken, RefreshToken refreshToken) {
        this.accessToken = accessToken.getToken();
        this.accessTokenExpiredAt = accessToken.getExpiredDateTime();
        this.refreshToken = refreshToken.getToken();
        this.refreshTokenExpiredAt = refreshToken.getExpiredDateTime();
    }
}
