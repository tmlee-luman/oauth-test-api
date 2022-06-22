package com.lumanlab.authtestserver.security.url;

import lombok.Getter;

import java.util.Arrays;
import java.util.regex.Pattern;

@Getter
public enum BasicAuthenticationExclusionV1Uri {

    OAUTH_LOGIN("/oauth/**"),
    REFRESH("/token/refresh"),;

    private static final String API_VERSION_V1 = "/v1";

    private static final Pattern OAUTH_LOGIN_PATTERN = Pattern.compile(
            "^/v1/oauth/(kakao|KAKAO|naver|NAVER|google|GOOGLE|apple|APPLE).*/login$");

    private final String uri;

    BasicAuthenticationExclusionV1Uri(String uri) {
        this.uri = uri;
    }

    public static boolean isExclusionUri(String requestUri) {
        boolean uriEquals = Arrays.stream(values())
                .map(value -> API_VERSION_V1 + value.uri)
                .anyMatch(uri -> uri.equals(requestUri));

        return uriEquals || OAUTH_LOGIN_PATTERN.matcher(requestUri).matches();
    }

    public static String[] getUris() {
        return Arrays.stream(values())
                .map(value -> API_VERSION_V1 + value.uri)
                .toArray(String[]::new);
    }
}
