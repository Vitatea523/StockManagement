package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yuyun Liu
 */
@Component
public class SellingHistory {
    private Integer id;
    private Integer goodsId;
    private Integer amount;
    private Date soldDate;
    private BigDecimal money;

    public SellingHistory() {
    }

    public SellingHistory(Integer id, Integer goodsId, Integer amount, Date soldDate, BigDecimal money) {
        this.id = id;
        this.goodsId = goodsId;
        this.amount = amount;
        this.soldDate = soldDate;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
