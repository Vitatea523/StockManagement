package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

/**
 * @author Yuyun Liu
 */
@Component
public class Supplier {

    private Integer id;
    private String businessName;
    private String contactName;
    private String email;
    private String phoneNumber;

    public Supplier() {

    }

    public Supplier(int id, String businessName, String contractName, String email, String phoneNumber) {
        this.id = id;
        this.businessName = businessName;
        this.contactName = contractName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
