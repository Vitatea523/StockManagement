package com.cp26.soft3888_m11_04_group4.service;

import com.cp26.soft3888_m11_04_group4.pojo.CheapestGood;
import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.StockLevelReport;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiaonan
 */
public interface GoodService {

    Goods queryGoodsById(Integer id);

    Integer updateGoodsById(Goods goods);

    List<Goods> listGoods();

    Integer addNewGood(Goods goods);

    List<Goods> queryGoodsByName(String name);

    Integer deleteGoods(Integer id);

    Goods queryGoodsByAPN(Long APN);

    List<Goods> queryGoodsByCategory(String categoryName);

    List<CheapestGood> findTheCheapestSupplier(Goods goods);

    List<StockLevelReport> listStockLevel();
}
