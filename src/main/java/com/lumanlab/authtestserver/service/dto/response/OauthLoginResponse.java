package com.lumanlab.authtestserver.service.dto.response;

import com.lumanlab.authtestserver.domain.AccessToken;
import com.lumanlab.authtestserver.domain.RefreshToken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OauthLoginResponse {

    private String accessToken;

    private LocalDateTime accessTokenExpiredAt;

    private String refreshToken;

    private LocalDateTime refreshTokenExpiredAt;

    public OauthLoginResponse(AccessToken accessToken, RefreshToken refreshToken) {
        this.accessToken = accessToken.getToken();
        this.accessTokenExpiredAt = accessToken.getExpiredDateTime();
        this.refreshToken = refreshToken.getToken();
        this.refreshTokenExpiredAt = refreshToken.getExpiredDateTime();
    }
}
