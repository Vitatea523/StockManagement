package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yuyun Liu
 */
@Component
public class Order {

    private Integer id;
    private Integer supplierId;
    private Integer goodsId;
    private Date date;
    private Date expireDate;
    private Integer amount;
    private BigDecimal currentCostPerCarton;
    private BigDecimal currentCostPerUnit;
    private String status;

    public Order() {

    }

    public Order(int id, int supplierId, int goodsId, Date date, Date expireDate, int amount, BigDecimal currentCostPerCarton, BigDecimal currentCostPerUnit, String status) {
        this.id = id;
        this.supplierId = supplierId;
        this.goodsId = goodsId;
        this.date = date;
        this.expireDate = expireDate;
        this.amount = amount;
        this.currentCostPerCarton = currentCostPerCarton;
        this.currentCostPerUnit = currentCostPerUnit;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentCostPerCarton() {
        return currentCostPerCarton;
    }

    public void setCurrentCostPerCarton(BigDecimal currentCostPerCarton) {
        this.currentCostPerCarton = currentCostPerCarton;
    }

    public BigDecimal getCurrentCostPerUnit() {
        return currentCostPerUnit;
    }

    public void setCurrentCostPerUnit(BigDecimal currentCostPerUnit) {
        this.currentCostPerUnit = currentCostPerUnit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
