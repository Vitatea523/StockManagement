package com.cp26.soft3888_m11_04_group4.pojo;

import java.util.Date;
import java.util.Objects;

public class Notification {
    private Integer goodsId;
    private String type;
    private String message;
    private Date date;
    private boolean reminderOn;
    private String reminderFrequency;
    private boolean snoozeOn;
    private String snoozeTime;

    public Notification() {
    }

    public Notification(Integer goodsId, String type, String message, Date date, boolean reminderOn, String reminderFrequency, boolean snoozeOn, String snoozeTime) {
        this.goodsId = goodsId;
        this.type = type;
        this.message = message;
        this.date = date;
        this.reminderOn = reminderOn;
        this.reminderFrequency = reminderFrequency;
        this.snoozeOn = snoozeOn;
        this.snoozeTime = snoozeTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isReminderOn() {
        return reminderOn;
    }

    public void setReminderOn(boolean reminderOn) {
        this.reminderOn = reminderOn;
    }

    public String getReminderFrequency() {
        return reminderFrequency;
    }

    public void setReminderFrequency(String reminderFrequency) {
        this.reminderFrequency = reminderFrequency;
    }

    public boolean isSnoozeOn() {
        return snoozeOn;
    }

    public void setSnoozeOn(boolean snoozeOn) {
        this.snoozeOn = snoozeOn;
    }

    public String getSnoozeTime() {
        return snoozeTime;
    }

    public void setSnoozeTime(String snoozeTime) {
        this.snoozeTime = snoozeTime;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Notification that = (Notification) o;
        return goodsId.equals(that.goodsId) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, type);
    }
}
