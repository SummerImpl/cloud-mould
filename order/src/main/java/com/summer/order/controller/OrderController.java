package com.summer.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：pipe
 * @Project：cloud-mould
 * @since：2024/3/10 10:33
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/add")
    public String addOrder() {
        return "调用 order 成功 服务端口是：" + port;
    }
}
