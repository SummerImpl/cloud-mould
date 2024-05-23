package com.summer.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 登录相关的接口类
 * @Author pipe
 * @Date 2024/4/10 10:17
 */
@RestController
@RequestMapping("/iam")
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
