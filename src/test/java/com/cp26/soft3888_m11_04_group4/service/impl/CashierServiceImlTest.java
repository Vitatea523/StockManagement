package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.GoodMapper;
import com.cp26.soft3888_m11_04_group4.dao.InventoryMapper;
import com.cp26.soft3888_m11_04_group4.dao.SellingHistoryMapper;
import com.cp26.soft3888_m11_04_group4.dao.UserMapper;
import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;
import com.cp26.soft3888_m11_04_group4.pojo.User;
import com.cp26.soft3888_m11_04_group4.service.CashierService;
import com.cp26.soft3888_m11_04_group4.service.UserService;
import com.cp26.soft3888_m11_04_group4.utils.MD5Util;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Shuhan Yang
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CashierServiceImlTest {

    private static CashierService cashierService;
    private static UserService userService;
    private static User cashier1;
    private static User cashier2;
    private static SellingHistory sellingHistory1;
    private static SellingHistory sellingHistory2;
    private static SellingHistory sellingHistory3;
    private static SellingHistory sellingHistory4;
    private static Goods goods1;
    private static Goods goods2;
    private static Goods goods3;
    private static Goods goods4;
    private static List<SellingHistory> sellingHistoryList;
    private static SellingHistoryMapper sellingHistoryMapper;
    private static GoodMapper goodMapper;
    private static InventoryMapper inventoryMapper;
    private static UserMapper userMapper;

    @BeforeClass
    public static void prepare(){
        cashier1 = mock(User.class);
        when(cashier1.getId()).thenReturn(3);
        when(cashier1.getName()).thenReturn("Justin");
        when(cashier1.getPassword()).thenReturn("123456");

        cashier2 = mock(User.class);
        when(cashier2.getId()).thenReturn(4);
        when(cashier2.getName()).thenReturn("Celine");
        when(cashier2.getPassword()).thenReturn("123456");

        userMapper = mock(UserMapper.class);

        //For Login First
        when(userMapper.queryByNameAndPassword("Justin", MD5Util.md5("123456"))).thenReturn(cashier1);
        when(userMapper.queryByNameAndPassword("Celine", MD5Util.md5("123456"))).thenReturn(cashier2);

        userService = new UserServiceImpl(userMapper);

        goods1 = mock(Goods.class);
        when(goods1.getGoodId()).thenReturn(1);
        when(goods1.getPrice()).thenReturn(new BigDecimal(7.99));

        goods2 = mock(Goods.class);
        when(goods2.getGoodId()).thenReturn(2);
        when(goods2.getPrice()).thenReturn(new BigDecimal(5.99));

        goods3 = mock(Goods.class);
        when(goods3.getGoodId()).thenReturn(3);
        when(goods3.getPrice()).thenReturn(new BigDecimal(9.99));

        goods4 = mock(Goods.class);
        when(goods4.getGoodId()).thenReturn(4);
        when(goods4.getPrice()).thenReturn(new BigDecimal(59.99));

        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        goodsList.add(goods4);

        goodMapper = mock(GoodMapper.class);

        when(goodMapper.queryAllGoods()).thenReturn(goodsList);

        sellingHistory1 = mock(SellingHistory.class);
        when(sellingHistory1.getId()).thenReturn(1);
        when(sellingHistory1.getGoodsId()).thenReturn(1);
        when(sellingHistory1.getMoney()).thenReturn(new BigDecimal(7.99));
        when(sellingHistory1.getSoldDate()).thenReturn(new Date());

        sellingHistory2 = mock(SellingHistory.class);
        when(sellingHistory2.getId()).thenReturn(2);
        when(sellingHistory2.getGoodsId()).thenReturn(2);
        when(sellingHistory2.getMoney()).thenReturn(new BigDecimal(5.99));

        sellingHistory3 = mock(SellingHistory.class);
        when(sellingHistory3.getId()).thenReturn(3);
        when(sellingHistory3.getGoodsId()).thenReturn(3);
        when(sellingHistory3.getMoney()).thenReturn(new BigDecimal(9.99));

        sellingHistory4 = mock(SellingHistory.class);
        when(sellingHistory4.getId()).thenReturn(4);
        when(sellingHistory4.getGoodsId()).thenReturn(4);
        when(sellingHistory4.getMoney()).thenReturn(new BigDecimal(59.99));

        sellingHistoryList = new ArrayList<>();
        sellingHistoryList.add(sellingHistory1);
        sellingHistoryList.add(sellingHistory2);
        sellingHistoryList.add(sellingHistory3);
        sellingHistoryList.add(sellingHistory4);

        sellingHistoryMapper = mock(SellingHistoryMapper.class);

        inventoryMapper = mock(InventoryMapper.class);

        when(sellingHistoryMapper.addSellingHistoryListByCashier(sellingHistoryList)).thenReturn(4);
        when(goodMapper.updateGoodsByListByCashier(sellingHistoryList)).thenReturn(4);

        //Simulate the Inventory/Stock for goodId == 1 is 50 units.
        when(inventoryMapper.checkInventoryByCashier(1)).thenReturn(50);
        //Simulate the Inventory/Stock for goodId == 2 is 100 units.
        when(inventoryMapper.checkInventoryByCashier(2)).thenReturn(100);
        //Simulate the Inventory/Stock for goodId == 3 is 30 units.
        when(inventoryMapper.checkInventoryByCashier(3)).thenReturn(30);
        //Simulate the Inventory/Stock for goodId == 4 is 10 units.
        when(inventoryMapper.checkInventoryByCashier(4)).thenReturn(10);

        when(inventoryMapper.updateInventoryByCashier(sellingHistory1)).thenReturn(1);
        when(inventoryMapper.updateInventoryByCashier(sellingHistory2)).thenReturn(2);
        when(inventoryMapper.updateInventoryByCashier(sellingHistory3)).thenReturn(3);
        when(inventoryMapper.updateInventoryByCashier(sellingHistory4)).thenReturn(4);

        cashierService = new CashierServiceImpl(sellingHistoryMapper, goodMapper, inventoryMapper);
    }

    @Test
    public void doLoginTest() {
        assertEquals(cashier1, userService.doLogin(cashier1));
        assertEquals(cashier2, userService.doLogin(cashier2));
    }

    @Test
    public void checkOutItemsTest() {
        sellingHistoryList.get(0).setAmount(8);
        sellingHistoryList.get(1).setAmount(10);
        sellingHistoryList.get(2).setAmount(2);
        sellingHistoryList.get(3).setAmount(1);
        assertEquals(4, cashierService.checkOutItems(sellingHistoryList));

        assertEquals(0, sellingHistory1.getAmount());
        assertEquals(0, sellingHistory2.getAmount());
        assertEquals(0, sellingHistory3.getAmount());
        assertEquals(0, sellingHistory4.getAmount());
    }


    @Test
    public void manualItemTest() {
        assertEquals(4, cashierService.manualItem().size());
    }
}