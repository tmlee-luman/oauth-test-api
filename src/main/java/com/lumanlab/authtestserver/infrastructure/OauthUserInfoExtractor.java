package com.lumanlab.authtestserver.infrastructure;

import java.util.Map;

public interface OauthUserInfoExtractor {

    OauthUserInfo extractUserInfo(Map<String, Object> userAttribution);
}
