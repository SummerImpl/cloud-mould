package com.summer.oauth.service.oauth;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Component;

/**
 * @Description Oauth授权码的授权和验证
 * @Author pipe
 * @Date 2024/4/9 14:37
 */
@Component
public class AuthorizationCodeService extends RandomValueAuthorizationCodeServices {

    private RandomValueStringGenerator generator = new RandomValueStringGenerator(16);

    @Override
    protected void store(String code, OAuth2Authentication authentication) {

    }

    @Override
    protected OAuth2Authentication remove(String code) {
        return null;
    }

    @Override
    public String createAuthorizationCode(OAuth2Authentication oAuth2Authentication) {
//        String code = this.generator.generate();
//        authorizationCodeStore.put(code, oAuth2Authentication);
//        return code;
        return null;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String s) throws InvalidGrantException {
        return null;
    }
}
