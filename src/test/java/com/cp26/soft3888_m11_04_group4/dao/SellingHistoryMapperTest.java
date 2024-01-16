package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;
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
public class SellingHistoryMapperTest {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private SellingHistoryMapper sellingHistoryMapper;

    private static Goods goods;

    private static SellingHistory sellingHistory1;
    private static SellingHistory sellingHistory2;
    private static SellingHistory sellingHistory3;
    private static SellingHistory sellingHistory4;
    private static List<SellingHistory> sellingHistoryList = new ArrayList<>();

    @BeforeClass
    public static void prepare(){
        goods = new Goods(1, "Chocolate", new Long(273897328), "Unit", new BigDecimal(1.5), 100, 4,0, 10, 5, 1, "Food");

        //For the addSellingHistoryListByCashier(), the test scenario is that one customer brought 10+10=20 for good_id == 100; 20+30 == 50 for good_id == 101.
        sellingHistory1 = new SellingHistory(1, 1, 10, new Date(), new BigDecimal(1.99));
        sellingHistory2 = new SellingHistory(2, 1, 10, new Date(), new BigDecimal(1.99));
        sellingHistory3 = new SellingHistory(3, 1, 20, new Date(), new BigDecimal(2.99));
        sellingHistory4 = new SellingHistory(4, 1, 30, new Date(), new BigDecimal(2.99));
        sellingHistoryList.add(sellingHistory1);
        sellingHistoryList.add(sellingHistory2);
        sellingHistoryList.add(sellingHistory3);
        sellingHistoryList.add(sellingHistory4);
    }

    private void setUp() {
        goodMapper.addGood(goods);
    }

    private void destroy(){
        sellingHistoryMapper.deleteSellingHistory(1);
        sellingHistoryMapper.deleteSellingHistory(2);
        sellingHistoryMapper.deleteSellingHistory(3);
        sellingHistoryMapper.deleteSellingHistory(4);

        goodMapper.deleteGoods(1);

        goodMapper.restartIndex();
        sellingHistoryMapper.restartIndex();
    }

    @Test
    public void listAllSellingHistoryTest() {
        setUp();
        assertEquals(4, sellingHistoryMapper.addSellingHistoryListByCashier(sellingHistoryList));
        assertEquals(4, sellingHistoryMapper.listAllSellingHistory().size());
        destroy();
    }

    @Test
    public void deleteSellingHistoryTest() {
        setUp();
        assertEquals(4, sellingHistoryMapper.addSellingHistoryListByCashier(sellingHistoryList));
        destroy();
        assertEquals(0, sellingHistoryMapper.listAllSellingHistory().size());
    }

    @Test
    public void addSellingHistoryListByCashierSizeTest() {
        //This test is passed under the condition of an empty table of Selling_history.
        //Due to the design of primary key in the table and useGeneratedKeys="true".
        setUp();

        assertEquals(4, sellingHistoryMapper.addSellingHistoryListByCashier(sellingHistoryList));

        destroy();
    }
}