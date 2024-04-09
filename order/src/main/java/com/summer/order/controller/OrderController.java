package com.summer.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> addOrder() {
        String ser = "调用 order 成功 服务端口是：" + port;

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("res", true);
        return map;
    }

    @GetMapping(value = "/edit")
    public Map<String, Object> addOrder1() {
        String ser = "调用 order 成功 服务端口是：" + port;
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        HashMap<String, Object> map = new HashMap<>();
        map.put("res", list);
        return map;
    }
}
