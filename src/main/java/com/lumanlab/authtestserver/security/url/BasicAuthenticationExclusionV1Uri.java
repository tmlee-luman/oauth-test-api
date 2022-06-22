package com.lumanlab.authtestserver.security.url;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BasicAuthenticationExclusionV1Uri {

    REFRESH("/token/refresh"),;

    private static final String API_VERSION_V1 = "/v1";

    private final String uri;

    BasicAuthenticationExclusionV1Uri(String uri) {
        this.uri = uri;
    }

    public static boolean isExclusionUri(String requestUri) {
        return Arrays.stream(values())
                .map(value -> API_VERSION_V1 + value.uri)
                .anyMatch(uri -> uri.equals(requestUri));
    }

    public static String[] getUris() {
        return Arrays.stream(values())
                .map(value -> API_VERSION_V1 + value.uri)
                .toArray(String[]::new);
    }
}
