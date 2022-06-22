package com.lumanlab.authtestserver.infrastructure;

import com.lumanlab.authtestserver.exception.InternalServerException;
import com.lumanlab.authtestserver.exception.InvalidAuthTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoOauthClient implements OauthClient {

    private static final String KAKAO_AUTH_URL = "https://kapi.kakao.com";

    private static final String KAKAO_USER_ME_URI = "/v2/user/me";

    private final WebClient webClient;

    private final OauthUserInfoExtractor kakaoOauthUserInfoExtractor;

    @Override
    public boolean support(OauthProvider oauthProvider) {
        return oauthProvider == OauthProvider.KAKAO;
    }

    @Override
    public OauthUserInfo getUserInfo(String accessToken) {
        Map<String, Object> userAttribution = webClient.mutate()
                .baseUrl(KAKAO_AUTH_URL)
                .build()
                .post()
                .uri(KAKAO_USER_ME_URI)
                .headers(h -> h.setBearerAuth(accessToken) )
                .headers(h -> h.setContentType(MediaType.APPLICATION_FORM_URLENCODED))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new InvalidAuthTokenException()))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new InternalServerException()))
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .blockOptional()
                .orElseThrow(InternalServerException::new);

        return kakaoOauthUserInfoExtractor.extractUserInfo(userAttribution);
    }


}
