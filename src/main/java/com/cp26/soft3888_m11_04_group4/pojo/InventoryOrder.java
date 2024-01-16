package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yuyun Liu
 */
@Component
public class InventoryOrder {
    private Integer id;
    private BigDecimal costPerCarton;
    private BigDecimal costPerUnit;
    private Integer amount;
    private Date expireDate;

    public InventoryOrder() {
    }

    public InventoryOrder(Integer id, BigDecimal costPerCarton, BigDecimal costPerUnit, Integer amount, Date expireDate) {
        this.id = id;
        this.costPerCarton = costPerCarton;
        this.costPerUnit = costPerUnit;
        this.amount = amount;
        this.expireDate = expireDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCostPerCarton() {
        return costPerCarton;
    }

    public void setCostPerCarton(BigDecimal costPerCarton) {
        this.costPerCarton = costPerCarton;
    }

    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(BigDecimal costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
