package com.fb.locations.util;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Proxy class for {@link Facebook} implementation
 */
@Component
public class FacebookAdapter {

    @Value("${token}")
    private String token;

    @Value("${app_id}")
    private String appId;

    @Value("${app_secret}")
    private String appSecret;

    @Getter
    private Facebook facebook;

    @PostConstruct
    private void setUp() {
        facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(appId, appSecret);
        facebook.setOAuthPermissions("");
        facebook.setOAuthAccessToken(new AccessToken(token, null));
    }
}
