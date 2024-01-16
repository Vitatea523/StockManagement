package com.cp26.soft3888_m11_04_group4.pojo;

import java.math.BigDecimal;

public class CheapestGood {

    private Integer id;

    private String businessName;

    private String contactName;

    private String email;

    private String phoneNumber;

    private BigDecimal costPerCarton;

    private BigDecimal costPerUnit;

    public CheapestGood() {
    }

    public CheapestGood(Integer id, String businessName, String contactName, String email, String phoneNumber, BigDecimal costPerCarton, BigDecimal costPerUnit) {
        this.id = id;
        this.businessName = businessName;
        this.contactName = contactName;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
