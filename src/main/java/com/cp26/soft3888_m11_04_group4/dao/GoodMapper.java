package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiaonan
 */
@Mapper
public interface GoodMapper {

    /**
     * The function is to add good to the database;
     * @param goods The good needs to be added.
     * @return 1 for success, other for fail.
     */
    int addGood(Goods goods);

    /**
     * The function is to get all the products in the database.
     * @return A list which contains all the goods in the database.
     */
    List<Goods> queryAllGoods();

    /**
     * The function is to get the target goods by the name
     * @param name The name of the target goods.
     * @return A list which contains all the target goods.
     */
    List<Goods> queryGoodsByName(String name);

    /**
     * The function is to get the target goods by id.
     * @param id The id of the target good.
     * @return The target good.
     */
    Goods queryGoodsById(int id);

    /**
     * The function is to update the target good in the database.
     * @param goods The good needs to be edited.
     * @return 1 for success, other for fail.
     */
    int updateGoodsById(Goods goods);

    /**
     * The function is to update the target good in the database.
     * @param goods The good needs to be edited.
     * @return 1 for success, other for fail.
     */
    int updateGoodsByName(Goods goods);

    /**
     * The function is to delete the target good.
     * @param goodId The id of the target good.
     * @return 1 for success, other for fail.
     */
    int deleteGoods(int goodId);

    /**
     * restart the index of the good table.
     * @return
     */
    int restartIndex();

    /**
     * The function is to find the average cost of target good.
     * @param goodsId The id of the target good.
     * @return The average cost.
     */
    BigDecimal findAverageCost(int goodsId);

    /**
     * The function is to find the target good by APN.
     * @param APN The APN of the target goods.
     * @return A list which contains all the target goods.
     */
    Goods queryGoodsByAPN(Long APN);

    /**
     * The function is to find the target goods by category name.
     * @param categoryName The category name of the target goods.
     * @return A list which contains all the target goods.
     */
    List<Goods> queryGoodsByCategory(String categoryName);

    /**
     * The function is to find the cheapest suppliers of the target good.
     * @param goods The target good.
     * @return A list which contains the cheapest suppliers.
     */
    List<CheapestGood> findTheCheapestSupplier(Goods goods);

    /**
     * The function is to count the average cost per unit by the target good.
     * @param goodId The target good.
     * @return The average cost per unit of the target good.
     */
    BigDecimal findAverageCostPerUnit(int goodId);

    /**
     * The function is to count the sale frequency.
     * @return A list which contains the stock level report.
     */
    List<StockLevelReport> countSalesFrequency();

    /**
     * The function is to find all goods information.
     * @return A list which contains the information of all the goods.
     */
    List<GoodsInfo> findAllGoodsInfo();

    /**
     * The function is to update the good list, it only can be done by the cashier.
     * @param sellingHistoryList The list of the selling history.
     * @return The changed rows.
     */
    int updateGoodsByListByCashier(List<SellingHistory> sellingHistoryList);
}
