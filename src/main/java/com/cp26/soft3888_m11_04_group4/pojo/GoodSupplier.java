package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GoodSupplier {

    private Integer id;

    private String businessName;

    private String contactName;

    private String email;

    private String phoneNumber;

    private Integer goodsId;

    private BigDecimal costPerCarton;

    private BigDecimal costPerUnit;

    public GoodSupplier() {
    }

    public GoodSupplier(Integer id, String businessName, String contactName, String email, String phoneNumber, Integer goodsId, BigDecimal costPerCarton, BigDecimal costPerUnit) {
        this.id = id;
        this.businessName = businessName;
        this.contactName = contactName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.goodsId = goodsId;
        this.costPerCarton = costPerCarton;
        this.costPerUnit = costPerUnit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "GoodSupplier{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", goodsId=" + goodsId +
                ", costPerCarton=" + costPerCarton +
                ", costPerUnit=" + costPerUnit +
                '}';
    }
}
