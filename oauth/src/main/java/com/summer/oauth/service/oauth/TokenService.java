package com.summer.oauth.service.oauth;

import com.summer.oauth.domain.AccessToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description Oauth2协议的token相关
 * @Author pipe
 * @Date 2024/4/9 14:36
 */
@Component
public class TokenService implements AuthorizationServerTokenServices {

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication oAuth2Authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public OAuth2AccessToken refreshAccessToken(String s, TokenRequest tokenRequest) throws AuthenticationException {
        return null;
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication oAuth2Authentication) {
        return null;
    }
}