package com.summer.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @Author：pipe
 * @Project：cloud-mould
 * @since：2024/3/12 09:47
 */
@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class OAuth2AuthorizationServerConfig extends WebSecurityConfigurerAdapter {
}
