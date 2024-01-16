package com.cp26.soft3888_m11_04_group4.config;

import com.cp26.soft3888_m11_04_group4.pojo.Notification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The configuration for notification.
 */
@Configuration
public class NotificationConfiguration {
    @Bean
    public HashMap<Integer, ArrayList<Notification>> userNotifications() {
        HashMap<Integer, ArrayList<Notification>> userNotifications = new HashMap<Integer, ArrayList<Notification>>();
        return userNotifications;
    }
}
