package com.cp26.soft3888_m11_04_group4.service;

import com.cp26.soft3888_m11_04_group4.pojo.Notification;

import java.util.ArrayList;

/**
 * @author Yuyun Liu
 */
public interface NotificationService {
    /**
     * The function that used to delete notification.
     * @param goodsId
     * @param type
     */
    void deleteNotification(int goodsId, String type);

    /**
     * The function to update notification timeframe.
     * @param userId
     * @param n
     */
    void updateNotification(int userId, Notification n);

    /**
     * The function that check stock level of the goods.
     * Should be called when check out.
     * @param goodsId
     * @return
     */
    int checkStockLevel(int goodsId);

    /**
     * The function to check expire date of all goods in the system.
     * @return
     */
    int checkExpireDate();

    /**
     * The function to get all notifications that should pop up at the current moment.
     * @param userId
     * @return
     */
    ArrayList<String> getCurrentNotification(int userId);
}
