package com.cp26.soft3888_m11_04_group4.controller;

import com.cp26.soft3888_m11_04_group4.pojo.Notification;
import com.cp26.soft3888_m11_04_group4.service.NotificationService;
import com.cp26.soft3888_m11_04_group4.vo.JsonResult;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Yuyun Liu
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private HashMap<Integer, ArrayList<Notification>> userNotifications;

    /**
     * The function is to get all notifications in the system regardless of the timeframe.
     * @param token
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/all")
    public JsonResult<ArrayList<Notification>> getAllNotification(@RequestParam String token) {
        int userId = Integer.parseInt(token);
        ArrayList<Notification> notifications = userNotifications.get(userId);
        if(notifications == null) {
            notifications = new ArrayList<>();
        }
        return JsonResult.build(20000, "Success", notifications);
    }

    /**
     * The function is to get all notifications that should pop up at the called time.
     * @param token
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/popUp")
    public JsonResult<ArrayList<String>> getLaunchedNotification(@RequestParam String token) {
        int userId = Integer.parseInt(token);
        ArrayList<String> result = notificationService.getCurrentNotification(userId);
        if(result == null) {
            result = new ArrayList<>();
        }
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is to delete a notification from system..
     * @param goodsId
     * @param type
     * @return JsonResult(a json object for the front-end)
     */
    @DeleteMapping("/delete")
    public JsonResult<Integer> deleteNotification(@RequestParam int goodsId, @RequestParam String type) {
        notificationService.deleteNotification(goodsId, type);
        return JsonResult.build(20000, "Success", null);
    }

    /**
     * The function is to update a notification.
     * @param data
     * @return JsonResult(a json object for the front-end)
     */
    @PutMapping("/setting")
    public JsonResult<Integer> updateNotification(@RequestBody JSONObject data) {
        String token = (String) data.get("token");
        Integer goodsId = (int) data.get("goodsId");
        String type = (String) data.get("type");
        boolean reminderOn = (boolean) data.get("reminderOn");
        String reminderFrequency = (String) data.get("reminderFrequency");
        boolean snoozeOn = (boolean) data.get("snoozeOn");;
        String snoozeTime = (String) data.get("snoozeTime");

        Notification notification = new Notification(goodsId, type, null, null, reminderOn, reminderFrequency, snoozeOn, snoozeTime);

        int userId = Integer.parseInt(token);
        notificationService.updateNotification(userId, notification);
        return JsonResult.build(20000, "Success", null);
    }

    /**
     * The function is to check through the database to find goods which is going to expire.
     * This function will be called every 24 hours.
     * @return JsonResult(a json object for the front-end)
     */
    @Scheduled(fixedRate = 24*60*60*1000)
    public void checkExpireDate() {
        notificationService.checkExpireDate();
    }
}
