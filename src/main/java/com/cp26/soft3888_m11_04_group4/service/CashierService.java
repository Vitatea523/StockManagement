package com.cp26.soft3888_m11_04_group4.service;

import com.cp26.soft3888_m11_04_group4.dao.GoodMapper;
import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CashierService {

    int checkOutItems(List<SellingHistory> sellingHistoryList);

    List<Goods> manualItem();
}
