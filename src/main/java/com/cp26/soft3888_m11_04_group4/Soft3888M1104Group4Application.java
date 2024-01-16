package com.cp26.soft3888_m11_04_group4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class Soft3888M1104Group4Application {

    public static void main(String[] args) {
        SpringApplication.run(Soft3888M1104Group4Application.class, args);
    }

}
