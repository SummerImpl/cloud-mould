package com.summer.oauth.config;

import com.summer.oauth.service.AuthClientDetailsService;
import com.summer.oauth.service.oauth.AuthorizationCodeService;
import com.summer.oauth.service.oauth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @Author：pipe
 * @Project：cloud-mould
 * @since：2024/3/12 09:52
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig implements AuthorizationServerConfigurer {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationCodeService authorizationCodeService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthClientDetailsService authClientDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                //让/oauth/token支持client_id以及client_secret作登录认证
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(authClientDetailsService).build();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.pathMapping("/oauth/authorize", "/iam/authorize")
                .pathMapping("/oauth/token", "/iam/token")
                .pathMapping("/oauth/confirm_access", "/iam/confirm_access")
                .pathMapping("/oauth/error", "/iam/error")
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeService)
                .tokenServices(tokenService)
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
