package com.summer.oauth.filter;

import com.summer.oauth.service.oauth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description Auth验证的Code
 * @Author pipe
 * @Date 2024/4/9 14:45
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求中获取访问令牌
//        String token = request.getHeader("Authorization");
//        // 验证令牌
//        boolean valid = tokenService.validateToken(token);
//        if (valid) {
//            // 如果验证通过，允许请求继续执行
//            filterChain.doFilter(request, response);
//        } else {
//            // 如果验证失败，返回错误信息
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.getWriter().write("Invalid token");
//        }
    }
}
