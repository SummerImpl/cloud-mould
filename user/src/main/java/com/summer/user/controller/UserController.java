package com.summer.user.controller;

import com.summer.user.config.openFeign.OrderServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：pipe
 * @Project：cloud-mould
 * @since：2024/3/10 10:39
 */
@RestController
//@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private OrderServiceClient orderServiceClient;

    @GetMapping("/add")
    public String add() {
//        String order = orderServiceClient.addOrder();
        return "user server " + port + "34";
    }
}
