package com.cp26.soft3888_m11_04_group4.service;

import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import com.cp26.soft3888_m11_04_group4.pojo.SupplierGoods;

/**
 * @author Yuyun Liu
 */
public interface SendEmailService {
    /**
     * The function to send email via javax.mail service.
     * @param supplier
     * @param goods
     * @param supplierGoods
     * @param amount
     * @return
     */
    public Integer sendEmail(Supplier supplier, Goods goods, SupplierGoods supplierGoods, Integer amount);
}
