package com.summer.oauth.config;

import com.summer.oauth.service.oauth.provider.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author：pipe
 * @Project：cloud-mould
 * @since：2024/3/12 09:47
 */
@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //创建一个登录用户
        auth.authenticationProvider(userAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭CSRF
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**", "/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/iam/login")
                .permitAll();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // 采用密码授权模式需要显式配置AuthenticationManager
        return super.authenticationManagerBean();
    }
}
