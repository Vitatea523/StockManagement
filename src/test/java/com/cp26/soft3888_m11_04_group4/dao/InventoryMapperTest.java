package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryMapperTest {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private SupplierGoodsMapper supplierGoodsMapper;

    private static Goods goods;
    private static Supplier supplier;
    private static SupplierGoods supplierGoods;
    private static Order order;
    private static Inventory inventory;

    @BeforeClass
    public static void prepare(){
        goods = new Goods(1, "Chocolate", new Long(273897328), "Unit", new BigDecimal(1.5), 50, 4,0, 10, 5, 1, "Food");
        supplier = new Supplier(1, "Company", "Contact", "fakeEmail", "1234");
        supplierGoods = new SupplierGoods(1, 1, new BigDecimal(1.5), new BigDecimal(0.5));
        order = new Order(1, 1, 1, new Date(), new Date(2021, 12, 1), 50, new BigDecimal(1.5), new BigDecimal(0.5), "InProgress");
        inventory = new Inventory(1,1, 50, new BigDecimal(0.77));
    }

    private void setUp(){
        goodMapper.addGood(goods);
        supplierMapper.addSupplier(supplier);
        supplierGoodsMapper.addSupplierGoods(supplierGoods);
        orderMapper.addOrder(order);
        inventoryMapper.addInventory(inventory);
    }

    private void destroy(){
        orderMapper.removeOrder(1);
        inventoryMapper.deleteInventory(1, 1);
        supplierGoodsMapper.deleteSupplierGoods(1, 1);
        supplierMapper.deleteSupplier(1);
        goodMapper.deleteGoods(1);

        goodMapper.restartIndex();
        supplierMapper.restartIndex();
        orderMapper.restartIndex();
    }

    @Test
    public void findSingleInventory() {
        setUp();
        Inventory inventory = inventoryMapper.findSingleInventory(1, 1);
        assertNotNull(inventory);
        assertEquals(new Integer(50), inventory.getAmount());
        destroy();
    }

    @Test
    public void addInventory() {
        setUp();
        Inventory inventory = inventoryMapper.findSingleInventory(1, 1);
        assertNotNull(inventory);
        assertEquals(new Integer(50), inventory.getAmount());
        destroy();
    }

    @Test
    public void deleteInventory() {
        setUp();
        Inventory inventory = inventoryMapper.findSingleInventory(1, 1);
        assertNotNull(inventory);
        assertEquals(new Integer(50), inventory.getAmount());
        destroy();

        inventory = inventoryMapper.findSingleInventory(1, 1);
        assertNull(inventory);
    }

    @Test
    public void updateInventory() {
        setUp();
        Inventory inventory = inventoryMapper.findSingleInventory(1, 1);
        assertNotNull(inventory);
        assertEquals(new Integer(50), inventory.getAmount());

        inventoryMapper.updateInventory(1, 1, 20);
        inventory = inventoryMapper.findSingleInventory(1, 1);
        assertNotNull(inventory);
        assertEquals(new Integer(20), inventory.getAmount());

        destroy();
    }

    @Test
    public void generateInventoryList() {
        setUp();
        List<InventoryOrder> inventoryOrder = inventoryMapper.generateInventoryList();
        assertEquals(1, inventoryOrder.size());
        destroy();
    }

    @Test
    public void generateInventoryListByGoodsId() {
        setUp();
        List<InventoryOrder> inventoryOrder = inventoryMapper.generateInventoryListByGoodsId(1);
        assertEquals(1, inventoryOrder.size());
        destroy();
    }

    @Test
    public void generateAllInventory() {
        setUp();
        List<InventoryOrder> inventoryOrder = inventoryMapper.generateAllInventory();
        assertEquals(1, inventoryOrder.size());
        destroy();
    }

    @Test
    public void checkInventoryByCashierTest() {
        setUp();
        assertEquals(50, inventoryMapper.checkInventoryByCashier(1));
        destroy();
    }

    @Test
    public void updateInventoryByCashierTest() {
        setUp();
        SellingHistory sellingHistory1 = new SellingHistory(1, 1, 20, new Date(), new BigDecimal(1.99));
        inventoryMapper.updateInventoryByCashier(sellingHistory1);
        int leftAmountOfInventory = inventoryMapper.findSingleInventory(1, 1).getAmount();
        assertEquals(30, leftAmountOfInventory);
        destroy();
    }

    @Test
    public void updateInventoryByCashierForLessStorageTest() {
        setUp();
        SellingHistory sellingHistory2 = new SellingHistory(2, 1, 50, new Date(), new BigDecimal(1.99));
        inventoryMapper.updateInventoryByCashierForLessStorage(sellingHistory2);
        int leftAmountOfInventory = inventoryMapper.findSingleInventory(1, 1).getAmount();
        assertEquals(0, leftAmountOfInventory);
        destroy();
    }

    @Test
    public void deleteInventoryByCashierTest() {
        setUp();
        SellingHistory sellingHistory2 = new SellingHistory(2, 1, 50, new Date(), new BigDecimal(1.99));
        inventoryMapper.updateInventoryByCashierForLessStorage(sellingHistory2);
        inventoryMapper.deleteInventoryByCashier();
        assertEquals(null, inventoryMapper.findSingleInventory(1, 1));
        destroy();
    }
}