package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.GoodMapper;
import com.cp26.soft3888_m11_04_group4.pojo.CheapestGood;
import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.StockLevelReport;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import com.cp26.soft3888_m11_04_group4.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    public GoodServiceImpl(GoodMapper goodMapper) {
        this.goodMapper = goodMapper;
    }

    /**
     * The function is to get the target by the good id.
     * @param id The id of the target good.
     * @return The target good.
     */
    @Override
    public Goods queryGoodsById(Integer id) {
        Goods goods = this.goodMapper.queryGoodsById(id);
        BigDecimal cost = this.goodMapper.findAverageCostPerUnit(id);
        goods.setStandard();
        goods.setGP(cost);
        return goods;
    }

    /**
     * The function is to edit the target good.
     * @param goods The good needs to be edited.
     * @return 1 for success, other for fail.
     */
    @Override
    public Integer updateGoodsById(Goods goods) {
        return this.goodMapper.updateGoodsById(goods);
    }

    /**
     * The function is to get all the goods from the database.
     * @return A JsonResult Object, which contains a list with all the goods in the database.
     */
    @Override
    public List<Goods> listGoods() {
        List<Goods> goodsList = goodMapper.queryAllGoods();
        for (Goods goods : goodsList) {
            goods.setStandard();
        }
        return goodsList;
    }

    /**
     * The function is to add a new good to the database.
     * @param goods The good needs to be added.
     * @return 1 for success, other for fail.
     */
    @Override
    public Integer addNewGood(Goods goods) {
        return goodMapper.addGood(goods);
    }

    /**
     * The function is to get the target goods by name.
     * @param name The name of the target goods.
     * @return A list with the target goods.
     */
    @Override
    public List<Goods> queryGoodsByName(String name) {
        List<Goods> goodsList = goodMapper.queryGoodsByName(name);
        for (Goods goods : goodsList) {
            goods.setStandard();
        }
        return goodsList;
    }

    /**
     * The function is to delete the target good from the database;
     * @param id The id of the target good.
     * @return 1 for success, other for fail.
     */
    @Override
    public Integer deleteGoods(Integer id) {
        return goodMapper.deleteGoods(id);
    }

    /**
     * The function is find the target good by APN.
     * @param APN The APN of the target good.
     * @return  The target good.
     */
    @Override
    public Goods queryGoodsByAPN(Long APN) {
        Goods goods = goodMapper.queryGoodsByAPN(APN);
        goods.setStandard();
        return goods;
    }

    /**
     * The function is find the target good by category.
     * @param categoryName The category of the target good.
     * @return A list, which contains the target good.
     */
    @Override
    public List<Goods> queryGoodsByCategory(String categoryName) {
        List<Goods> goodsList = goodMapper.queryGoodsByCategory(categoryName);
        for (Goods goods : goodsList) {
            goods.setStandard();
        }
        return goodsList;
    }

    /**
     * The function is to find the cheapest supplier of the target goods.
     * @param goods The good want to be ordered.
     * @return A list, which contains the cheapest supplier of the target goods.
     */
    @Override
    public List<CheapestGood> findTheCheapestSupplier(Goods goods) {
        return goodMapper.findTheCheapestSupplier(goods);
    }

    /**
     * The function is to get the stock level report.
     * @return A list, which contains a list with the stock level report.
     */
    @Override
    public List<StockLevelReport> listStockLevel() {
        List<StockLevelReport> stockLevelReportList = goodMapper.countSalesFrequency();
        for (StockLevelReport temp : stockLevelReportList) {
            temp.setStandard();
        }
        return stockLevelReportList;
    }
}
