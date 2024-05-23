package com.summer.order.entity;

import java.io.Serializable;

/**
 * @Description
 * @Author pipe
 * @Date 2024/4/23 15:18
 */
public class OrderEntity implements Serializable {
    private String name;

    private transient String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
