package com.lumanlab.authtestserver.infrastructure;

public interface OauthClient {

    boolean support(OauthProvider oauthProvider);

    OauthUserInfo getUserInfo(String accessToken);

}
