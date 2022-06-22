package com.lumanlab.authtestserver.infrastructure;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KakaoOauthUserInfoExtractor extends AbstractOauthUserInfoExtractor {

    private static final String KAKAO_ACCOUNT = "kakao_account";

    @Override
    public Map<String, Object> extractUserDataMap(Map<String, Object> userAttribution) {
        return (Map<String, Object>) userAttribution.get(KAKAO_ACCOUNT);
    }
}
