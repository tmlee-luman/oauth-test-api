package com.lumanlab.authtestserver.service;

import com.lumanlab.authtestserver.domain.Member;
import com.lumanlab.authtestserver.domain.MemberRepository;
import com.lumanlab.authtestserver.domain.RefreshToken;
import com.lumanlab.authtestserver.domain.RefreshTokenRepository;
import com.lumanlab.authtestserver.exception.InvalidAuthTokenException;
import com.lumanlab.authtestserver.infrastructure.OauthHandler;
import com.lumanlab.authtestserver.infrastructure.OauthProvider;
import com.lumanlab.authtestserver.infrastructure.OauthUserInfo;
import com.lumanlab.authtestserver.infrastructure.jwt.JwtTokenHelper;
import com.lumanlab.authtestserver.security.exception.InvalidRequestTokenException;
import com.lumanlab.authtestserver.service.dto.response.OauthLoginResponse;
import com.lumanlab.authtestserver.service.dto.response.ReIssueTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenHelper jwtTokenHelper;

    private final OauthHandler oauthHandler;

    @Transactional
    public OauthLoginResponse loginOauth(String accessToken, OauthProvider oauthProvider) {
        if (Objects.isNull(accessToken) || accessToken.isBlank()) {
            throw new InvalidAuthTokenException();
        }

        OauthUserInfo oauthUserInfo = oauthHandler.getOauthUserInfo(accessToken, oauthProvider);
        Member member = memberRepository.findByEmail(oauthUserInfo.getEmail())
                .orElseGet(() -> memberRepository.save(oauthUserInfo.toEntity()));

        String email = member.getEmail();
        return new OauthLoginResponse(
                jwtTokenHelper.createAccessToken(email, now()),
                refreshTokenRepository.save(jwtTokenHelper.createRefreshToken(email, now()))
        );
    }

    @Transactional
    public ReIssueTokenResponse reissueToken(String refreshToken) {
        RefreshToken existingRefreshToken = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(InvalidRequestTokenException::new);

        String email = jwtTokenHelper.parseToken(existingRefreshToken.getToken());

        return new ReIssueTokenResponse(
                jwtTokenHelper.createAccessToken(email, now()),
                refreshTokenRepository.save(jwtTokenHelper.createRefreshToken(email, now()))
        );
    }

    public void logout(String refreshToken) {
        RefreshToken existingRefreshToken = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(InvalidRequestTokenException::new);

        refreshTokenRepository.delete(existingRefreshToken);
    }
}
