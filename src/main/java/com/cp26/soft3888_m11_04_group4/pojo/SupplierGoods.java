package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Yuyun Liu
 */
@Component
public class SupplierGoods {
    private Integer supplierId;
    private Integer goodsId;
    private BigDecimal costPerCarton;
    private BigDecimal costPerUnit;

    public SupplierGoods() {
    }

    public SupplierGoods(Integer supplierId, Integer goodsId, BigDecimal costPerCarton, BigDecimal costPerUnit) {
        this.supplierId = supplierId;
        this.goodsId = goodsId;
        this.costPerCarton = costPerCarton;
        this.costPerUnit = costPerUnit;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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
}
