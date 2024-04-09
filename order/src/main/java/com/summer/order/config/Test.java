package com.summer.order.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author pipe
 * @Date 2024/4/2 17:24
 */
@Component
public class Test implements InitializingBean, ApplicationRunner, CommandLineRunner {

    @PostConstruct
    private void init() {
        System.out.println("初始化执行");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet 方法执行");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner 方法执行");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner 方法执行");

    }
}
