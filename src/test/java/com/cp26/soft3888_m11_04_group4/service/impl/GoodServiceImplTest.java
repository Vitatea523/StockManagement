package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.GoodMapper;
import com.cp26.soft3888_m11_04_group4.pojo.CheapestGood;
import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.service.GoodService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodServiceImplTest {

    private static GoodService goodService;
    private static Goods goods1;
    private static Goods goods2;
    private static GoodMapper goodMapper;
    private static CheapestGood cheapestGood;
    private static List<Goods> goodsList = new ArrayList<>();
    private static List<CheapestGood> cheapestGoods = new ArrayList<>();

    @BeforeClass
    public static void prepare() {
        goods1 = mock(Goods.class);
        when(goods1.getGoodId()).thenReturn(1);
        when(goods1.getName()).thenReturn("Pepsi");
        when(goods1.getAPN()).thenReturn(Long.valueOf(123456));
        when(goods1.getUOM()).thenReturn("bottle");
        when(goods1.getPrice()).thenReturn(new BigDecimal(3));
        when(goods1.getAmount()).thenReturn(50);
        when(goods1.getThreshold()).thenReturn(30);
        when(goods1.getFullStandard()).thenReturn(200);
        when(goods1.getHighStandard()).thenReturn(150);
        when(goods1.getMediumStandard()).thenReturn(100);
        when(goods1.getLowStandard()).thenReturn(50);
        when(goods1.getCategoryName()).thenReturn("Drink");
        when(goods1.getStandard()).thenReturn("low");
        when(goods1.getGP()).thenReturn("0.33333%");

        goods2 = mock(Goods.class);
        when(goods2.getGoodId()).thenReturn(2);
        when(goods2.getName()).thenReturn("Coke");
        when(goods2.getAPN()).thenReturn(Long.valueOf(654321));
        when(goods2.getUOM()).thenReturn("bottle");
        when(goods2.getPrice()).thenReturn(new BigDecimal(4));
        when(goods2.getAmount()).thenReturn(80);
        when(goods2.getThreshold()).thenReturn(40);
        when(goods2.getFullStandard()).thenReturn(150);
        when(goods2.getHighStandard()).thenReturn(100);
        when(goods2.getMediumStandard()).thenReturn(50);
        when(goods2.getLowStandard()).thenReturn(20);
        when(goods2.getCategoryName()).thenReturn("Drink");
        when(goods2.getStandard()).thenReturn("very low");
        when(goods2.getGP()).thenReturn("0.67777777%");

        cheapestGood = mock(CheapestGood.class);
        when(cheapestGood.getId()).thenReturn(1);
        when(cheapestGood.getBusinessName()).thenReturn("company");
        when(cheapestGood.getContactName()).thenReturn("Tony");
        when(cheapestGood.getEmail()).thenReturn("123@gmail.com");
        when(cheapestGood.getPhoneNumber()).thenReturn("123456");
        when(cheapestGood.getCostPerCarton()).thenReturn(new BigDecimal(123));
        when(cheapestGood.getCostPerUnit()).thenReturn(new BigDecimal(321));
        cheapestGoods.add(cheapestGood);

        goodsList.add(goods1);
        goodsList.add(goods2);
        goodMapper = mock(GoodMapper.class);

        when(goodMapper.queryGoodsById(1)).thenReturn(goods1);
        when(goodMapper.updateGoodsById(goods1)).thenReturn(1);
        when(goodMapper.queryAllGoods()).thenReturn(goodsList);
        when(goodMapper.addGood(goods1)).thenReturn(1);
        when(goodMapper.queryGoodsByName("Coke")).thenReturn(goodsList);
        when(goodMapper.deleteGoods(1)).thenReturn(1);
        when(goodMapper.queryGoodsByAPN(123456L)).thenReturn(goods1);
        when(goodMapper.queryGoodsByCategory("Drink")).thenReturn(goodsList);

        when(goodMapper.queryGoodsById(2)).thenReturn(goods2);
        when(goodMapper.updateGoodsById(goods2)).thenReturn(1);
        when(goodMapper.queryAllGoods()).thenReturn(goodsList);
        when(goodMapper.addGood(goods2)).thenReturn(1);
        when(goodMapper.queryGoodsByName("Coke")).thenReturn(goodsList);
        when(goodMapper.deleteGoods(2)).thenReturn(1);
        when(goodMapper.queryGoodsByAPN(654321L)).thenReturn(goods2);
        when(goodMapper.queryGoodsByCategory("Drink")).thenReturn(goodsList);
        when(goodMapper.findTheCheapestSupplier(goods1)).thenReturn(cheapestGoods);

        goodService = new GoodServiceImpl(goodMapper);
    }

    @Test
    public void queryGoodsById() {
        assertEquals(1, goodService.queryGoodsById(1).getGoodId());
        assertEquals(2, goodService.queryGoodsById(2).getGoodId());
    }

    @Test
    public void updateGoodsById() {
        assertEquals(1, goodService.updateGoodsById(goods1));
        assertEquals(1, goodService.updateGoodsById(goods2));
    }

    @Test
    public void listGoods() {
        assertEquals(2, goodService.listGoods().size());
    }

    @Test
    public void addNewGood() {
        assertEquals(1, goodService.addNewGood(goods1));
        assertEquals(1, goodService.addNewGood(goods2));
    }

    @Test
    public void queryGoodsByName() {
        assertEquals(goodsList, goodService.queryGoodsByName("Coke"));
    }

    @Test
    public void deleteGoods() {
        assertEquals(1, goodService.deleteGoods(1));
        assertEquals(1, goodService.deleteGoods(2));
    }

    @Test
    public void queryGoodsByAPN() {
        assertEquals(goods1, goodService.queryGoodsByAPN(123456L));
        assertEquals(goods2, goodService.queryGoodsByAPN(654321L));
    }

    @Test
    public void queryGoodsByCategory() {
        assertEquals(goodsList, goodService.queryGoodsByCategory("Drink"));
    }

    @Test
    public void findTheCheapestSupplier() {
        assertEquals(cheapestGoods, goodService.findTheCheapestSupplier(goods1));
    }
}