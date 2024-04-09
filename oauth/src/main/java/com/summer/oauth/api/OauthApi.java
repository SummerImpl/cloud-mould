package com.summer.oauth.api;

import com.summer.oauth.domain.AccessToken;
import com.summer.oauth.service.oauth.AuthorizationCodeService;
import com.summer.oauth.service.oauth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

/**
 * @Description Oauth2协议相关的api
 * @Author pipe
 * @Date 2024/4/9 12:03
 */
@RestController
@RequestMapping("/oauth")
public class OauthApi {


    @Autowired
    private AuthorizationCodeService authorizationCodeService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/authorize")
    public ResponseEntity<?> authorize(@RequestParam("response_type") String responseType,
                                       @RequestParam("client_id") String clientId,
                                       @RequestParam("redirect_uri") String redirectUri,
                                       @RequestParam("scope") String scope) {
        if ("code".equals(responseType)) {
            String code = authorizationCodeService.createAuthorizationCode(clientId, redirectUri, scope);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(redirectUri + "?code=" + code))
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported response type: " + responseType);
        }
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(
            @RequestParam("grant_type") String grantType,
            @RequestParam("code") String code,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("client_id") String clientId) {
        // 验证授权码、重定向URI、客户端ID等信息

        // 生成访问令牌和刷新令牌
        AccessToken accessToken = tokenService.createAccessToken(clientId, code, redirectUri);
        // 返回令牌信息
        return ResponseEntity.ok(accessToken);
    }

}
