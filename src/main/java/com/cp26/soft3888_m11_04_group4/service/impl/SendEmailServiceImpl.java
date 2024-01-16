package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import com.cp26.soft3888_m11_04_group4.pojo.SupplierGoods;
import com.cp26.soft3888_m11_04_group4.service.SendEmailService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import org.json.simple.parser.ParseException;

/**
 * @author Yuyun Liu
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * The function to send email via javax.mail service.
     * @param supplier
     * @param goods
     * @param supplierGoods
     * @param amount
     * @return
     */
    @Override
    public Integer sendEmail(Supplier supplier, Goods goods, SupplierGoods supplierGoods, Integer amount) {
        JSONParser parser = new JSONParser();

        try {
            FileReader reader = new FileReader("src/main/resources/config/SendEmailConfig.json");
            Object obj = parser.parse(reader);
            JSONObject format = (JSONObject) obj;
            String topicFormat = (String) format.get("topic");
            String bodyFormat = (String) format.get("body");

            String email = supplier.getEmail();
            String contactName = supplier.getContactName();
            String goodsName = goods.getName();
            Long APN = goods.getAPN();
            BigDecimal cost = supplierGoods.getCostPerCarton();

            String topic = String.format(topicFormat, goodsName);
            String body = String.format(bodyFormat, contactName, amount, goodsName, APN, cost, "Carton");

            this.mailTo(email, body, topic);
            return 1;
        } catch (MailException e) {
            return 0;
        } catch (FileNotFoundException e) {
            return 0;
        } catch (IOException e) {
            return 0;
        } catch (ParseException e) {
            return 0;
        }
    }

    private void mailTo(String to, String body, String topic) {
        System.out.println("Send Email...");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("Metro");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);

        System.out.println("Send Email Finished.");
    }
}
