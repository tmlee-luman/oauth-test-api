package com.lumanlab.authtestserver.infrastructure;

import com.lumanlab.authtestserver.exception.NotSupportOauthClientException;
import com.lumanlab.authtestserver.service.dto.request.OauthLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OauthHandler {

    private final List<OauthClient> oauthClients;

    public OauthUserInfo getOauthUserInfo(String accessToken, OauthProvider oauthProvider) {
        return oauthClients.stream()
                .filter(oauthClient -> oauthClient.support(oauthProvider))
                .findFirst()
                .orElseThrow(NotSupportOauthClientException::new)
                .getUserInfo(accessToken);
    }
}
