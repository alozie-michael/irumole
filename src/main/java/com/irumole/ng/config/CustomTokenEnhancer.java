package com.irumole.ng.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> additionalInformation = new HashMap<>();
        additionalInformation.put("organization", oAuth2Authentication.getPrincipal());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(
                additionalInformation);
        return oAuth2AccessToken;
    }
}
