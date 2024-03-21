package com.summer.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author：pipe
 * @Project：cloud-mould
 * @since：2024/3/10 10:32
 */
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        Thread d= new Thread(()->{});
        d.start();
        SpringApplication.run(OrderApplication.class, args);
    }

}
