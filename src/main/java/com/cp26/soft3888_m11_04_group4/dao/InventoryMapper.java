package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.Inventory;
import com.cp26.soft3888_m11_04_group4.pojo.InventoryOrder;
import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yuyun Liu
 */
@Mapper
public interface InventoryMapper {

    /**
     * The function to give detail information of a single inventory.
     * @param goodsId
     * @param orderId
     * @return
     */
    Inventory findSingleInventory(int goodsId, int orderId);

    /**
     * The function to add an inventory record in the database.
     * @param inventory
     * @return
     */
    int addInventory(Inventory inventory);

    /**
     * The function to mark inventory as "deleted".
     * Only when the cashier sell the last such goods, this inventory will be deleted.
     * @param goodsId
     * @param orderId
     * @return
     */
    int deleteInventory(int goodsId, int orderId);

    /**
     * The function to update amount of the inventory.
     * @param goodsId
     * @param orderId
     * @param newAmount
     * @return
     */
    int updateInventory(int goodsId, int orderId, int newAmount);

    /**
     * The function to generate the list to show all details of inventory of all goods.
     * @return
     */
    List<InventoryOrder> generateInventoryList();

    /**
     * The function to get the detail information of inventory of a goods.
     * @param goodsId
     * @return
     */
    List<InventoryOrder> generateInventoryListByGoodsId(int goodsId);

    /**
     * The function to generate the list to show all details of inventory of all goods.
     * With different format with @generateInventoryList() function.
     * @return
     */
    List<InventoryOrder> generateAllInventory();

    /**
     * The function for cashier role to check status of an inventory.
     * @param goodsId
     * @return
     */
    int checkInventoryByCashier(int goodsId);

    /**
     * The function for cashier role to update inventory status.
     * The function will be only used when the inventory is enough for a selling.
     * @param sellingHistory
     * @return
     */
    int updateInventoryByCashier(SellingHistory sellingHistory);

    /**
     * The function for cashier role to update inventory status.
     * The function will be only used when the inventory is not enough for a selling.
     * @param sellingHistory
     * @return
     */
    int updateInventoryByCashierForLessStorage(SellingHistory sellingHistory);

    /**
     * The function for cashier to delete inventory when amount in inventory being zero.
     * @return
     */
    int deleteInventoryByCashier();

}
