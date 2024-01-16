package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.InventoryMapper;
import com.cp26.soft3888_m11_04_group4.pojo.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceImplTest {
    private static Inventory inventory;
    private static InventoryOrder inventoryOrder;

    private static InventoryMapper inventoryMapper;
    private static InventoryServiceImpl inventoryService;

    @BeforeClass
    public static void init() {
        inventory = mock(Inventory.class);
        when(inventory.getGoodsId()).thenReturn(1);

        inventoryOrder = mock(InventoryOrder.class);
        when(inventory.getGoodsId()).thenReturn(1);

        List<InventoryOrder> list = new ArrayList<>();
        list.add(inventoryOrder);

        inventoryMapper = mock(InventoryMapper.class);
        when(inventoryMapper.generateInventoryList()).thenReturn(list);
        when(inventoryMapper.generateInventoryListByGoodsId(1)).thenReturn(list);

        inventoryService = new InventoryServiceImpl(inventoryMapper);
    }

    @Test
    public void generateInventoryListTest() {
        assertEquals(1, inventoryService.generateInventoryList().size());
    }

    @Test
    public void generateInventoryListByGoodsIdTest() {
        assertEquals(1, inventoryService.generateInventoryListByGoodsId(1).size());
    }

    @Test
    public void addInventoryTest() {
        inventoryService.addInventory(inventory);
        verify(inventoryMapper, times(1)).addInventory(inventory);
    }
}
