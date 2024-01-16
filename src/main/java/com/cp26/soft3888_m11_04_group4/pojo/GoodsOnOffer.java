package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GoodsOnOffer {
    private int id;
    private String name;
    private BigDecimal price;

    public GoodsOnOffer() {
    }

    public GoodsOnOffer(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
