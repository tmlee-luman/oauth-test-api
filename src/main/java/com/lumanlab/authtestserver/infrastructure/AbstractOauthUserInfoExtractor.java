package com.lumanlab.authtestserver.infrastructure;

import java.util.Map;

public abstract class AbstractOauthUserInfoExtractor implements OauthUserInfoExtractor {

    private static final String NAME = "name";

    private static final String EMAIL = "email";

    @Override
    public OauthUserInfo extractUserInfo(Map<String, Object> userAttribution) {
        Map<String, Object> userDataMap = extractUserDataMap(userAttribution);

        return new OauthUserInfo(
                (String) userDataMap.get(NAME),
                (String) userDataMap.get(EMAIL)
        );
    }

    public abstract Map<String, Object> extractUserDataMap(Map<String, Object> userAttribution);
}
