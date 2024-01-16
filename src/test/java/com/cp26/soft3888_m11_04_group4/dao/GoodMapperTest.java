package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoodMapperTest {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private SupplierGoodsMapper supplierGoodsMapper;

    @Autowired
    private SellingHistoryMapper sellingHistoryMapper;

    private static Goods goods1;
    private static Goods goods2;
    private static Supplier supplier1;
    private static Supplier supplier2;
    private static SupplierGoods supplierGoods1;
    private static SupplierGoods supplierGoods2;
    private static SellingHistory sellingHistory1;
    private static SellingHistory sellingHistory2;
    private static SellingHistory sellingHistory3;
    private static SellingHistory sellingHistory4;
    private static List<SellingHistory> sellingHistoryList = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        goods1 = new Goods(1, "Pepsi", 10L, "gm", new BigDecimal("3.5"),
                10, 4,150, 100, 50, 10, "Drink");
        goods2 = new Goods(2, "Coke", 20L, "gm", new BigDecimal("5.5"),
                1000, 50,200, 150, 100, 50, "Drink");
        supplier1 = new Supplier(1, "DefaultCompany1", "Alice", "alice@gmail.com", "972091820");
        supplier2 = new Supplier(2, "DefaultCompany2", "Bob", "bob@gmail.com", "3762782");
        supplierGoods1 = new SupplierGoods(1, 1, new BigDecimal(28), new BigDecimal(2));
        supplierGoods2 = new SupplierGoods(2, 1, new BigDecimal(30), new BigDecimal(4));

        //For the countSalesFrequencyTest(), the SalesFrequency is set 10+10=20 for good_id == 100; 20+30 == 50 for good_id == 101.
        //For the updateGoodsByListByCashierTest(), the test scenario is that one customer brought 10+10=20 for good_id == 100; 20+30 == 50 for good_id == 101.
        sellingHistory1 = new SellingHistory(1, 1, 10, new Date(), new BigDecimal(1.99));
        sellingHistory2 = new SellingHistory(2, 1, 10, new Date(), new BigDecimal(1.99));
        sellingHistory3 = new SellingHistory(3, 2, 20, new Date(), new BigDecimal(2.99));
        sellingHistory4 = new SellingHistory(4, 2, 30, new Date(), new BigDecimal(2.99));
        sellingHistoryList.add(sellingHistory1);
        sellingHistoryList.add(sellingHistory2);
        sellingHistoryList.add(sellingHistory3);
        sellingHistoryList.add(sellingHistory4);

    }

    private void setup() {
        goodMapper.restartIndex();
        goodMapper.addGood(goods1);
        goodMapper.addGood(goods2);
    }

    private void destroy() {
        goodMapper.deleteGoods(goods1.getGoodId());
        goodMapper.deleteGoods(goods2.getGoodId());
        goodMapper.restartIndex();
    }

    private void setupSupplier() {
        supplierMapper.restartIndex();
        supplierMapper.addSupplier(supplier1);
        supplierMapper.addSupplier(supplier2);
    }

    private void destroySupplier() {
        supplierMapper.deleteSupplier(supplier1.getId());
        supplierMapper.deleteSupplier(supplier2.getId());
        supplierMapper.restartIndex();
    }

    private void setupSupplierGood() {
        supplierGoodsMapper.addSupplierGoods(supplierGoods1);
        supplierGoodsMapper.addSupplierGoods(supplierGoods2);
    }

    private void destroySupplierGood() {
        supplierGoodsMapper.deleteSupplierGoods(1, 1);
        supplierGoodsMapper.deleteSupplierGoods(2, 1);
    }

    private void setupSellingHistory(){
        sellingHistoryMapper.addSellingHistoryListByCashier(sellingHistoryList);
    }

    private void destroySellingHistory(){
        sellingHistoryMapper.deleteSellingHistory(1);
        sellingHistoryMapper.deleteSellingHistory(2);
        sellingHistoryMapper.deleteSellingHistory(3);
        sellingHistoryMapper.deleteSellingHistory(4);
        sellingHistoryMapper.restartIndex();
    }

    @Test
    public void queryAllGoods() {
        destroy();
        setup();
        List<Goods> allGoods = goodMapper.queryAllGoods();

        assertNotNull(allGoods);
        assertTrue(allGoods.size() == 2);

        assertTrue(allGoods.get(0) instanceof Goods);
        assertTrue(allGoods.get(0).getName().equals("Pepsi"));
        assertTrue(allGoods.get(0).getAPN().equals(10L));
        assertTrue(allGoods.get(0).getUOM().equals("gm"));
        assertTrue(allGoods.get(0).getPrice().equals(new BigDecimal("3.50")));
        assertTrue(allGoods.get(0).getAmount().equals(10));
        assertTrue(allGoods.get(0).getThreshold().equals(4));
        assertTrue(allGoods.get(0).getFullStandard().equals(150));
        assertTrue(allGoods.get(0).getHighStandard().equals(100));
        assertTrue(allGoods.get(0).getMediumStandard().equals(50));
        assertTrue(allGoods.get(0).getLowStandard().equals(10));
        assertTrue(allGoods.get(0).getCategoryName().equals("Drink"));

        destroy();
    }

    @Test
    public void queryGoodsByName() {
        destroy();
        setup();
        List<Goods> allGoods = goodMapper.queryGoodsByName("Pepsi");

        assertNotNull(allGoods);

        assertTrue(allGoods.get(0) instanceof Goods);
        assertTrue(allGoods.get(0).getName().equals("Pepsi"));
        assertTrue(allGoods.get(0).getAPN().equals(10L));
        assertTrue(allGoods.get(0).getUOM().equals("gm"));
        assertTrue(allGoods.get(0).getPrice().equals(new BigDecimal("3.50")));
        assertTrue(allGoods.get(0).getAmount().equals(10));
        assertTrue(allGoods.get(0).getThreshold().equals(4));
        assertTrue(allGoods.get(0).getFullStandard().equals(150));
        assertTrue(allGoods.get(0).getHighStandard().equals(100));
        assertTrue(allGoods.get(0).getMediumStandard().equals(50));
        assertTrue(allGoods.get(0).getLowStandard().equals(10));
        assertTrue(allGoods.get(0).getCategoryName().equals("Drink"));
        destroy();
    }

    @Test
    public void queryGoodsById() {
        destroy();
        setup();

        Goods goods = goodMapper.queryGoodsById(1);

        assertNotNull(goods);

        assertTrue(goods instanceof Goods);
        assertTrue(goods.getName().equals("Pepsi"));
        assertTrue(goods.getAPN().equals(10L));
        assertTrue(goods.getUOM().equals("gm"));
        assertTrue(goods.getPrice().equals(new BigDecimal("3.50")));
        assertTrue(goods.getAmount().equals(10));
        assertTrue(goods.getThreshold().equals(4));
        assertTrue(goods.getFullStandard().equals(150));
        assertTrue(goods.getHighStandard().equals(100));
        assertTrue(goods.getMediumStandard().equals(50));
        assertTrue(goods.getLowStandard().equals(10));
        assertTrue(goods.getCategoryName().equals("Drink"));

        destroy();
    }

    @Test
    public void updateGoodsById() {
        destroy();
        setup();

        goods1.setName("Water");
        goods1.setAmount(80);
        goods1.setAPN(1234567L);
        goods1.setFullStandard(500);
        this.goodMapper.updateGoodsById(goods1);

        Goods goods = goodMapper.queryGoodsById(1);

        assertTrue(goods instanceof Goods);
        assertTrue(goods.getName().equals("Water"));
        assertTrue(goods.getAPN().equals(1234567L));
        assertTrue(goods.getUOM().equals("gm"));
        assertTrue(goods.getPrice().equals(new BigDecimal("3.50")));
        assertTrue(goods.getAmount().equals(80));
        assertTrue(goods.getThreshold().equals(4));
        assertTrue(goods.getFullStandard().equals(500));
        assertTrue(goods.getHighStandard().equals(100));
        assertTrue(goods.getMediumStandard().equals(50));
        assertTrue(goods.getLowStandard().equals(10));
        assertTrue(goods.getCategoryName().equals("Drink"));

        destroy();
    }

    @Test
    public void findAverageCostTest() {
        destroy();
        destroySupplier();
        destroySupplierGood();

        setup();
        setupSupplier();
        setupSupplierGood();

        BigDecimal result = goodMapper.findAverageCost(1);
        assertTrue(result.equals(new BigDecimal("3.0000000000000000")));

        destroy();
        destroySupplier();
        destroySupplierGood();
    }

    @Test
    public void queryGoodsByAPNTest() {
        destroy();
        setup();
        Goods goods = goodMapper.queryGoodsByAPN(10L);
        assertTrue(goods instanceof Goods);
        assertTrue(goods.getName().equals("Pepsi"));
        assertTrue(goods.getAPN().equals(10L));
        assertTrue(goods.getUOM().equals("gm"));
        assertTrue(goods.getPrice().equals(new BigDecimal("3.50")));
        assertTrue(goods.getAmount().equals(10));
        assertTrue(goods.getThreshold().equals(4));
        assertTrue(goods.getFullStandard().equals(150));
        assertTrue(goods.getHighStandard().equals(100));
        assertTrue(goods.getMediumStandard().equals(50));
        assertTrue(goods.getLowStandard().equals(10));
        assertTrue(goods.getCategoryName().equals("Drink"));
        destroy();
    }

    @Test
    public void queryGoodsByCategoryTest() {
        destroy();
        setup();
        List<Goods> goods = goodMapper.queryGoodsByCategory("Drink");
        assertTrue(goods.get(0) instanceof Goods);
        assertTrue(goods.get(0).getName().equals("Pepsi"));
        assertTrue(goods.get(0).getAPN().equals(10L));
        assertTrue(goods.get(0).getUOM().equals("gm"));
        assertTrue(goods.get(0).getPrice().equals(new BigDecimal("3.50")));
        assertTrue(goods.get(0).getAmount().equals(10));
        assertTrue(goods.get(0).getThreshold().equals(4));
        assertTrue(goods.get(0).getFullStandard().equals(150));
        assertTrue(goods.get(0).getHighStandard().equals(100));
        assertTrue(goods.get(0).getMediumStandard().equals(50));
        assertTrue(goods.get(0).getLowStandard().equals(10));
        assertTrue(goods.get(0).getCategoryName().equals("Drink"));
        destroy();
    }

    @Test
    public void findTheCheapestSupplierTest() {
        destroy();
        destroySupplier();
        destroySupplierGood();

        setup();
        setupSupplier();
        setupSupplierGood();
        List<CheapestGood> cheapestGoods = goodMapper.findTheCheapestSupplier(goods1);
        assertTrue(cheapestGoods.get(0).getId().equals(1));
        assertTrue(cheapestGoods.get(0).getBusinessName().equals("DefaultCompany1"));
        assertTrue(cheapestGoods.get(0).getContactName().equals("Alice"));
        assertTrue(cheapestGoods.get(0).getEmail().equals("alice@gmail.com"));
        assertTrue(cheapestGoods.get(0).getPhoneNumber().equals("972091820"));
        assertTrue(cheapestGoods.get(0).getCostPerCarton().equals(new BigDecimal("28.00")));
        assertTrue(cheapestGoods.get(0).getCostPerUnit().equals(new BigDecimal("2.00")));
        destroy();
        destroySupplier();
        destroySupplierGood();
    }

    @Test
    public void countSalesFrequencyTest(){
        //This test is passed under the condition of an empty table of Selling_history.
        //Due to the design of primary key in the table and useGeneratedKeys="true".
        destroy();
        destroySellingHistory();
        setup();
        setupSellingHistory();

        List<StockLevelReport> stockLevelReportList = goodMapper.countSalesFrequency();
        assertTrue(1 == stockLevelReportList.get(0).getGoodId());
        assertTrue(20 == stockLevelReportList.get(0).getSalesFrequency());
        assertTrue(2 == stockLevelReportList.get(1).getGoodId());
        assertTrue(50 == stockLevelReportList.get(1).getSalesFrequency());

        destroySellingHistory();
        destroy();
    }

    @Test
    public void updateGoodsByListByCashierTest(){
        destroy();
        setup();
        Goods beforeUpdate1 = goodMapper.queryGoodsById(1);
        int beforeUpdateAmount1 = beforeUpdate1.getAmount();

        Goods beforeUpdate2 = goodMapper.queryGoodsById(2);
        int beforeUpdateAmount2 = beforeUpdate2.getAmount();

        assertEquals(1,goodMapper.updateGoodsByListByCashier(sellingHistoryList));

        Goods afterUpdate1 = goodMapper.queryGoodsById(1);
        int afterUpdateAmount1 = afterUpdate1.getAmount();

        Goods afterUpdate2 = goodMapper.queryGoodsById(2);
        int afterUpdateAmount2 = afterUpdate2.getAmount();

        assertEquals(beforeUpdateAmount1-afterUpdateAmount1, sellingHistoryList.get(0).getAmount()+sellingHistoryList.get(1).getAmount());
        assertEquals(beforeUpdateAmount2-afterUpdateAmount2, sellingHistoryList.get(2).getAmount()+sellingHistoryList.get(3).getAmount());

        //Reset the amount in Goods table
        beforeUpdate1.setAmount(beforeUpdateAmount1);
        goodMapper.updateGoodsById(beforeUpdate1);

        beforeUpdate2.setAmount(beforeUpdateAmount2);
        goodMapper.updateGoodsById(beforeUpdate2);
        destroy();
    }
}