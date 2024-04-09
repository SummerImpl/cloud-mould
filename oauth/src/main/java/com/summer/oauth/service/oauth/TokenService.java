package com.summer.oauth.service.oauth;

import com.summer.oauth.domain.AccessToken;
import org.springframework.stereotype.Service;

/**
 * @Description Oauth2协议的token相关
 * @Author pipe
 * @Date 2024/4/9 14:36
 */
@Service
public class TokenService {

    public AccessToken createAccessToken(String clientId, String code, String redirectUri) {
        return null;
    }

    public boolean validateToken(String token) {
        return false;
    }
}
