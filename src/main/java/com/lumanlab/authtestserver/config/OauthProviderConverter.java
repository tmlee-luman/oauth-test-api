package com.lumanlab.authtestserver.config;

import com.lumanlab.authtestserver.infrastructure.OauthProvider;
import org.springframework.core.convert.converter.Converter;

public class OauthProviderConverter implements Converter<String, OauthProvider> {

    @Override
    public OauthProvider convert(String source) {
        return OauthProvider.valueOf(source.toUpperCase());
    }
}
