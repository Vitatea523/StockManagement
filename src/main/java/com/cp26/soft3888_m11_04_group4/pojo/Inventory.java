package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

/**
 * @author Yuyun Liu
 */
@Component
public class Inventory {

    private Integer goodsId;
    private Integer orderId;
    private Integer amount;
    private BigDecimal GPPercent;

    public Inventory() {
    }

    public Inventory(Integer goodsId, Integer orderId, Integer amount, BigDecimal GPPercent) {
        this.goodsId = goodsId;
        this.orderId = orderId;
        this.amount = amount;
        this.GPPercent = GPPercent;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getGPPercent() {
        return GPPercent;
    }

    public void setGPPercent(BigDecimal GPPercent) {
        this.GPPercent = GPPercent;
    }
}
