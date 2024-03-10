package com.summer.user.config.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author：xhquan
 * @Project：cloud-mould
 * @since：2024/3/10 10:21
 */
@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/order/add")
    String addOrder();
}
