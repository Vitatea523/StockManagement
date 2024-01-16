package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.SupplierGoodsMapper;
import com.cp26.soft3888_m11_04_group4.pojo.SupplierGoods;
import com.cp26.soft3888_m11_04_group4.service.SupplierGoodsService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierGoodsServiceImplTest {

    private static SupplierGoodsService supplierGoodsService;
    private static SupplierGoods supplierGoods1;
    private static SupplierGoods supplierGoods2;
    private static SupplierGoodsMapper supplierGoodsMapper;

    @BeforeClass
    public static void prepare(){
        supplierGoods1 = mock(SupplierGoods.class);
        when(supplierGoods1.getSupplierId()).thenReturn(1);
        when(supplierGoods1.getGoodsId()).thenReturn(1);
        when(supplierGoods1.getCostPerCarton()).thenReturn(new BigDecimal(29.99));
        when(supplierGoods1.getCostPerUnit()).thenReturn(new BigDecimal(2.99));

        supplierGoods2 = mock(SupplierGoods.class);
        when(supplierGoods2.getSupplierId()).thenReturn(1);
        when(supplierGoods2.getGoodsId()).thenReturn(2);
        when(supplierGoods2.getCostPerCarton()).thenReturn(new BigDecimal(79.99));
        when(supplierGoods2.getCostPerUnit()).thenReturn(new BigDecimal(7.99));

        supplierGoodsMapper = mock(SupplierGoodsMapper.class);

        when(supplierGoodsMapper.findCostPerCarton(1, 1)).thenReturn(new BigDecimal(29.99));
        when(supplierGoodsMapper.findCostPerCarton(1, 2)).thenReturn(new BigDecimal(79.99));

        when(supplierGoodsMapper.findCostPerUnit(1, 1)).thenReturn(new BigDecimal(2.99));
        when(supplierGoodsMapper.findCostPerUnit(1, 2)).thenReturn(new BigDecimal(7.99));

        when(supplierGoodsMapper.querySupplierGoodsById(1, 1)).thenReturn(supplierGoods1);
        when(supplierGoodsMapper.querySupplierGoodsById(1, 2)).thenReturn(supplierGoods2);

        when(supplierGoodsMapper.addSupplierGoods(supplierGoods1)).thenReturn(1);
        when(supplierGoodsMapper.addSupplierGoods(supplierGoods2)).thenReturn(2);

        when(supplierGoodsMapper.deleteSupplierGoods(1, 1)).thenReturn(1);
        when(supplierGoodsMapper.deleteSupplierGoods(1, 2)).thenReturn(2);

        supplierGoodsService = new SupplierGoodsServiceImpl(supplierGoodsMapper);

    }

    @Test
    public void findCostPerCartonTest() {
        assertEquals(supplierGoods1.getCostPerCarton(), supplierGoodsService.findCostPerCarton(1, 1));
        assertEquals(supplierGoods2.getCostPerCarton(), supplierGoodsService.findCostPerCarton(1, 2));
    }

    @Test
    public void findCostPerUnitTest() {
        assertEquals(supplierGoods1.getCostPerUnit(), supplierGoodsService.findCostPerUnit(1, 1));
        assertEquals(supplierGoods2.getCostPerUnit(), supplierGoodsService.findCostPerUnit(1, 2));
    }

    @Test
    public void querySupplierGoodsByIdTest() {
        assertEquals(supplierGoods1, supplierGoodsService.querySupplierGoodsById(1, 1));
        assertEquals(supplierGoods2, supplierGoodsService.querySupplierGoodsById(1, 2));
    }

    @Test
    public void addSupplyGoodsTest() {
        assertEquals(1, supplierGoodsService.addSupplyGoods(supplierGoods1));
        assertEquals(2, supplierGoodsService.addSupplyGoods(supplierGoods2));
    }

    @Test
    public void deleteSupplyGoodsTest() {
        assertEquals(1, supplierGoodsService.deleteSupplyGoods(1, 1));
        assertEquals(2, supplierGoodsService.deleteSupplyGoods(1, 2));
    }
}
