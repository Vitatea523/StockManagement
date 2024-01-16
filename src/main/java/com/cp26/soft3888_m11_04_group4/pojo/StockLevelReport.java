package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

@Component
public class StockLevelReport {

    private Integer goodId;

    private String name;

    private String categoryName;

    private String standard;

    private Integer salesFrequency;

    private Integer fullStandard;

    private Integer highStandard;

    private Integer mediumStandard;

    private Integer lowStandard;

    private Integer amount;


    public StockLevelReport() {
    }

    public StockLevelReport(Integer goodId, String name, String categoryName, String standard, Integer salesFrequency, Integer fullStandard, Integer highStandard, Integer mediumStandard, Integer lowStandard, Integer amount) {
        this.goodId = goodId;
        this.name = name;
        this.categoryName = categoryName;
        this.standard = standard;
        this.salesFrequency = salesFrequency;
        this.fullStandard = fullStandard;
        this.highStandard = highStandard;
        this.mediumStandard = mediumStandard;
        this.lowStandard = lowStandard;
        this.amount = amount;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getStandard() {
        return standard;
    }

    public Integer getSalesFrequency() {
        return salesFrequency;
    }

    public Integer getFullStandard() {
        return fullStandard;
    }

    public Integer getHighStandard() {
        return highStandard;
    }

    public Integer getMediumStandard() {
        return mediumStandard;
    }

    public Integer getLowStandard() {
        return lowStandard;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setFullStandard(Integer fullStandard) {
        this.fullStandard = fullStandard;
    }

    public void setHighStandard(Integer highStandard) {
        this.highStandard = highStandard;
    }

    public void setMediumStandard(Integer mediumStandard) {
        this.mediumStandard = mediumStandard;
    }

    public void setLowStandard(Integer lowStandard) {
        this.lowStandard = lowStandard;
    }

    public void setSalesFrequency(Integer salesFrequency) {
        this.salesFrequency = salesFrequency;
    }

    public void setStandard() {
        if (this.amount == null || (this.fullStandard == null && this.highStandard == null &&
                this.mediumStandard == null && this.lowStandard == null)) {
            this.standard = "null";
            return;
        }
        if (this.fullStandard != null && this.amount >= fullStandard) {
            standard = "Full";
        }else if (this.highStandard != null && this.amount >= highStandard) {
            standard = "High";
        }else if (this.mediumStandard != null && this.amount >= mediumStandard) {
            standard = "Medium";
        }else if (this.lowStandard != null && this.amount >= lowStandard) {
            standard = "Low";
        }else {
            standard = "Very low";
        }
    }


}
