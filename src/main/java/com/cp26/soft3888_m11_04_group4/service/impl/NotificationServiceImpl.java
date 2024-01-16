package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.GoodMapper;
import com.cp26.soft3888_m11_04_group4.dao.InventoryMapper;
import com.cp26.soft3888_m11_04_group4.dao.UserMapper;
import com.cp26.soft3888_m11_04_group4.pojo.*;
import com.cp26.soft3888_m11_04_group4.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private HashMap<Integer, ArrayList<Notification>> userNotifications;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * The function that all notifications in the system.
     * @param notification
     */
    private void addNotification(Notification notification) {
        List<User> users = userMapper.queryAllUsers();

        for(int i = 0; i < users.size(); i++) {
            int userId = users.get(i).getId();
            ArrayList<Notification> notifications = userNotifications.get(userId);
            if(notifications == null) {
                notifications = new ArrayList<>();
            }

            boolean whetherIn = false;
            for(int j = 0; j < notifications.size(); j++) {
                if(notifications.get(j).getGoodsId().equals(notification.getGoodsId()) && notifications.get(j).getType().equals(notification.getType())) {
                    whetherIn = true;
                    break;
                }
            }

            if(whetherIn == false) {
                ArrayList<Notification> newNotifications = userNotifications.get(userId);
                if(newNotifications == null) {
                    newNotifications = new ArrayList<>();
                }
                newNotifications.add(notification);
                userNotifications.put(userId, newNotifications);
            }
        }
    }

    /**
     * The function that used to delete notification.
     * @param goodsId
     * @param type
     */
    @Override
    public void deleteNotification(int goodsId, String type) {
        for(Map.Entry<Integer, ArrayList<Notification>> entry : userNotifications.entrySet()) {
            int index = -1;
            ArrayList<Notification> notifications = entry.getValue();
            for(int i = 0; i < notifications.size(); i++) {
                if(notifications.get(i).getGoodsId() == goodsId && notifications.get(i).getType().equals(type)) {
                    index = i;
                    break;
                }
            }

            if(index != -1) {
                entry.getValue().remove(index);
            }
        }
    }

    /**
     * The function convert time string to int.
     * @param time
     * @return
     */
    private static int toMinutes(String time) {
        // time = "00:00"
        int h1 = time.charAt(0) - '0';
        int h2 = time.charAt(1) - '0';
        int m1 = time.charAt(3) - '0';
        int m2 = time.charAt(4) - '0';

        int minutes = (h1 * 10 + h2) * 60 + (m1 * 10 + m2);
        return minutes;
    }

    /**
     * The function to update notification timeframe.
     * @param userId
     * @param n
     */
    @Override
    public void updateNotification(int userId, Notification n) {
        ArrayList<Notification> notifications = userNotifications.get(userId);
        if(notifications == null) {
            notifications = new ArrayList<>();
        }
        Notification notification = null;
        for(int i = 0; i < notifications.size(); i++) {
            if(notifications.get(i).getGoodsId().equals(n.getGoodsId()) && notifications.get(i).getType().equals(n.getType())) {
                notification = notifications.get(i);
                break;
            }
        }
        if(notification != null) {
            notification.setReminderOn(n.isReminderOn());
            notification.setReminderFrequency(n.getReminderFrequency());
            notification.setSnoozeOn(n.isSnoozeOn());
            notification.setSnoozeTime(n.getSnoozeTime());
            notification.setDate(new Date());
        }
    }

    /**
     * The function that check stock level of the goods.
     * Should be called when check out.
     * @param goodsId
     * @return
     */
    @Override
    public int checkStockLevel(int goodsId) {
        Goods goods = goodMapper.queryGoodsById(goodsId);
        int amount = goods.getAmount();
        int low = goods.getThreshold();
        if(amount < low) {
            String msg = goods.getName() + " (ID: " + goodsId + ") stock is lower than " + low + ".";
            Notification notification = new Notification(goodsId, "STOCK", msg, new Date(), true, "01:00", false, "01:00");
            this.addNotification(notification);
            return 1;
        }

        return 0;
    }

    /**
     * The function to check expire date of all goods in the system.
     * @return
     */
    @Override
    public int checkExpireDate() {
        List<Goods> goods = goodMapper.queryAllGoods();
        List<InventoryOrder> allInventories = inventoryMapper.generateAllInventory();
        Map<Integer, List<InventoryOrder>> goodsInventories = new HashMap<>();
        for(int i = 0; i < goods.size(); i++) {
            int id = goods.get(i).getGoodId();
            goodsInventories.put(id, new ArrayList<>());

            for(int j = 0 ; j < allInventories.size(); j++) {
                if(allInventories.get(j).getId().equals(id)) {
                    List<InventoryOrder> inventories = goodsInventories.get(id);
                    inventories.add(allInventories.get(j));
                    goodsInventories.put(id, inventories);
                }
            }
        }
        for(int i = 0; i < goods.size(); i++) {
            int goodsId = goods.get(i).getGoodId();

            List<InventoryOrder> inventory = goodsInventories.get(goodsId);
            System.out.println(inventory.size());
            boolean whetherExpire = false;
            for(int j = 0; j < inventory.size(); j++) {
                System.out.println(inventory.get(j).getExpireDate().getTime());
                System.out.println(new Date());
                if(new Date(inventory.get(j).getExpireDate().getTime() - ((1000 * 60 * 60 * 24) * 15)).compareTo(new Date()) <= 0) {
                    whetherExpire = true;
                    break;
                }
            }

            if(whetherExpire == true) {
                String message = "Inventory of " + goods.get(i).getName() + " (ID: " + goodsId + ") will expire within 15 days.";
                Notification notification = new Notification(goodsId, "EXPIRE", message, new Date(), true, "01:00", false, "01:00");
                this.addNotification(notification);
            }
        }
        return 0;
    }

    /**
     * The function to get all notifications that should pop up at the current moment.
     * @param userId
     * @return
     */
    @Override
    public ArrayList<String> getCurrentNotification(int userId) {
        ArrayList<String> result = new ArrayList<>();

        ArrayList<Notification> notifications = userNotifications.get(userId);
        if(notifications == null) {
            notifications = new ArrayList<>();
        }
        for(Notification notification: notifications) {
            if(notification.isReminderOn()) {
                if(notification.isSnoozeOn()) {
                    Date time = notification.getDate();
                    String snoozeTime = notification.getSnoozeTime();
                    int delay = toMinutes(snoozeTime);
                    Date launch = new Date(time.getTime() + (delay * 60 * 1000));

                    Date current = new Date();
                    Date start = new Date(current.getTime() - 1000 * 10);
                    Date end = new Date(current.getTime() + 1000 * 10);

                    if(launch.compareTo(start) >= 0 && launch.compareTo(end) <= 0) {
                        result.add(notification.getMessage());
                        notification.setSnoozeOn(false);
                        notification.setDate(new Date());
                    }
                } else {
                    // check frequency
                    Date time = notification.getDate();
                    String reminderFrequency = notification.getReminderFrequency();
                    int delay = toMinutes(reminderFrequency);
                    Date launch = new Date(time.getTime() + (delay * 60 * 1000));

                    Date current = new Date();
                    Date start = new Date(current.getTime() - 1000 * 10);
                    Date end = new Date(current.getTime() + 1000 * 10);

                    if(launch.compareTo(start) >= 0 && launch.compareTo(end) <= 0) {
                        result.add(notification.getMessage());
                        notification.setDate(new Date());
                    }
                }
            }
        }

        return result;
    }


}
