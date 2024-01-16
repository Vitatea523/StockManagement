package com.cp26.soft3888_m11_04_group4.service;

import com.cp26.soft3888_m11_04_group4.pojo.Inventory;
import com.cp26.soft3888_m11_04_group4.pojo.InventoryOrder;

import java.util.List;

/**
 * @author Yuyun Liu
 */
public interface InventoryService {

    /**
     * The function to add an inventory to the system.
     * Only can be called in the system, not in the controller
     * @param inventory
     * @return
     */
    int addInventory(Inventory inventory);

    /**
     * The function that generate a list of all inventories.
     * @return
     */
    List<InventoryOrder> generateInventoryList();

    /**
     * The function that generate inventories associated with a goods.
     * @param goodsId
     * @return
     */
    List<InventoryOrder> generateInventoryListByGoodsId(int goodsId);

}
