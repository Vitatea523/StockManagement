package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.InventoryMapper;
import com.cp26.soft3888_m11_04_group4.pojo.Inventory;
import com.cp26.soft3888_m11_04_group4.pojo.InventoryOrder;
import com.cp26.soft3888_m11_04_group4.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyun Liu
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    public InventoryServiceImpl(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    /**
     * The function to add an inventory to the system.
     * Only can be called in the system, not in the controller
     * @param inventory
     * @return
     */
    @Override
    public int addInventory(Inventory inventory) {
        return this.inventoryMapper.addInventory(inventory);
    }

    /**
     * The function that generate a list of all inventories.
     * @return
     */
    @Override
    public List<InventoryOrder> generateInventoryList() {
        return this.inventoryMapper.generateInventoryList();
    }

    /**
     * The function that generate inventories associated with a goods.
     * @param goodsId
     * @return
     */
    @Override
    public List<InventoryOrder> generateInventoryListByGoodsId(int goodsId) {
        return this.inventoryMapper.generateInventoryListByGoodsId(goodsId);
    }
}
