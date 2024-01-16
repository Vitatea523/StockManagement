package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author xiaonan
 */
@Component
public class Goods {

    private Integer goodId;

    private String name;

    private Long APN;

    private String UOM;

    private BigDecimal price;

    private Integer amount;

    private Integer threshold;

    private Integer fullStandard;

    private Integer highStandard;

    private Integer mediumStandard;

    private Integer lowStandard;

    private String categoryName;

    private String standard;

    private String GP;

    public Goods() {
    }

    public Goods(Integer goodId, String name, Long APN, String UOM, BigDecimal price, Integer amount, Integer threshold, Integer fullStandard, Integer highStandard, Integer mediumStandard, Integer lowStandard, String categoryName) {
        this.goodId = goodId;
        this.name = name;
        this.APN = APN;
        this.UOM = UOM;
        this.price = price;
        this.amount = amount;
        this.threshold = threshold;
        this.fullStandard = fullStandard;
        this.highStandard = highStandard;
        this.mediumStandard = mediumStandard;
        this.lowStandard = lowStandard;
        this.categoryName = categoryName;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAPN() {
        return APN;
    }

    public void setAPN(Long APN) {
        this.APN = APN;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getFullStandard() {
        return fullStandard;
    }

    public void setFullStandard(Integer fullStandard) {
        this.fullStandard = fullStandard;
    }

    public Integer getHighStandard() {
        return highStandard;
    }

    public void setHighStandard(Integer highStandard) {
        this.highStandard = highStandard;
    }

    public Integer getMediumStandard() {
        return mediumStandard;
    }

    public void setMediumStandard(Integer mediumStandard) {
        this.mediumStandard = mediumStandard;
    }

    public Integer getLowStandard() {
        return lowStandard;
    }

    public void setLowStandard(Integer lowStandard) {
        this.lowStandard = lowStandard;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStandard() {
        return standard;
    }

    public String getGP() {
        return GP;
    }

    public void setGP(BigDecimal cost) {
        if (this.price == null || cost == null) {
            this.GP = null;
            return;
        }
        StringBuilder res = new StringBuilder();
        BigDecimal value = (this.price.subtract(cost)).divide(this.price, RoundingMode.HALF_UP);
        res.append(value).append("%");
        this.GP = res.toString();
    }

    public void setGP(String GP) {
        this.GP = GP;
    }

    public void setStandard() {
        if (this.amount == null || (this.fullStandard == null && this.highStandard == null &&
                this.mediumStandard == null && this.lowStandard == null)) {
            this.standard = "null";
            return;
        }
        if (this.fullStandard != null && this.amount >= fullStandard) {
            standard = "full";
        }else if (this.highStandard != null && this.amount >= highStandard) {
            standard = "high";
        }else if (this.mediumStandard != null && this.amount >= mediumStandard) {
            standard = "medium";
        }else if (this.lowStandard != null && this.amount >= lowStandard) {
            standard = "low";
        }else {
            standard = "very low";
        }
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodId=" + goodId +
                ", name='" + name + '\'' +
                ", APN=" + APN +
                ", UOM='" + UOM + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", threshold=" + threshold +
                ", fullStandard=" + fullStandard +
                ", highStandard=" + highStandard +
                ", mediumStandard=" + mediumStandard +
                ", lowStandard=" + lowStandard +
                ", categoryName='" + categoryName + '\'' +
                ", standard='" + standard + '\'' +
                ", GP=" + GP +
                '}';
    }
}
