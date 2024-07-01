package com.pipe.stock.entity;

/**
 * @Description
 * @Author pipe
 * @Date 2024/5/30 18:42
 */
public class StockEntity extends BaseEntity{
    private Long id;

    private String name;

    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
