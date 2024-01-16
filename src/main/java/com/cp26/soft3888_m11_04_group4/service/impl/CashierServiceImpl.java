package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.GoodMapper;
import com.cp26.soft3888_m11_04_group4.dao.InventoryMapper;
import com.cp26.soft3888_m11_04_group4.dao.SellingHistoryMapper;
import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;
import com.cp26.soft3888_m11_04_group4.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Shuhan Yang
 */
@Service
public class CashierServiceImpl implements CashierService {

    @Autowired
    private SellingHistoryMapper sellingHistoryMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    public CashierServiceImpl(SellingHistoryMapper sellingHistoryMapper, GoodMapper goodMapper, InventoryMapper inventoryMapper){
        this.sellingHistoryMapper = sellingHistoryMapper;
        this.goodMapper = goodMapper;
        this.inventoryMapper = inventoryMapper;
    }

    /**
     * The function is for cashiers to check out purchases of the customers.
     * @param sellingHistoryList
     * @return int(the result of running the relevant SQLs in the database)
     */
    @Override
    public int checkOutItems(List<SellingHistory> sellingHistoryList) {
        for (SellingHistory temp : sellingHistoryList) {
            temp.setSoldDate(new Date());
        }
        //Add the selling history(the customers' purchases data) in the database.
        int result00 = sellingHistoryMapper.addSellingHistoryListByCashier(sellingHistoryList);
        //Update(mines) the amount in the Goods table
        int result01 = goodMapper.updateGoodsByListByCashier(sellingHistoryList);

        //The loop checks for the Inventory.
        //If enough storage, update(mines) the amount in the Inventory table.
        //If less storage, delete this inventory data, and find the next inventory data accounting to the goodsId.
        for (SellingHistory sellingHistory: sellingHistoryList){
            while(sellingHistory.getAmount() != 0) {
                int inventoryAmount = inventoryMapper.checkInventoryByCashier(sellingHistory.getGoodsId());
                if (inventoryAmount >= sellingHistory.getAmount()) {
                    //Enough storage
                    int result02 = inventoryMapper.updateInventoryByCashier(sellingHistory);
                    sellingHistory.setAmount(0);
                } else if (inventoryAmount < sellingHistory.getAmount()) {
                    //Less storage
                    int result03 = inventoryMapper.updateInventoryByCashierForLessStorage(sellingHistory);
                    int result04 = inventoryMapper.deleteInventoryByCashier();
                    sellingHistory.setAmount(sellingHistory.getAmount() - inventoryAmount);
                    //Find next Inventory
                }
            }
        }
        return result00;
    }

    /**
     * The function for cashiers to manually add items by providing the list of items.
     * @return List<Goods>(the list of items)
     */
    @Override
    public List<Goods> manualItem() {
        return goodMapper.queryAllGoods();
    }
}
