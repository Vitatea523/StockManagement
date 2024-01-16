package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GoodsInfo {
    private Integer goodsId;
    private BigDecimal price;
    private BigDecimal averageCost;

    public GoodsInfo() {
    }

    public GoodsInfo(Integer goodsId, BigDecimal price, BigDecimal averageCost) {
        this.goodsId = goodsId;
        this.price = price;
        this.averageCost = averageCost;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(BigDecimal averageCost) {
        this.averageCost = averageCost;
    }
}
