package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.OrderMapper;
import com.cp26.soft3888_m11_04_group4.pojo.Order;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    private static Order order1;

    private static OrderMapper orderMapper;
    private static OrderServiceImpl orderService;

    @BeforeClass
    public static void init() {
        order1 = mock(Order.class);
        when(order1.getId()).thenReturn(1);
        when(order1.getAmount()).thenReturn(10);

        List<Order> list1 = new ArrayList<>();
        list1.add(order1);

        orderMapper = mock(OrderMapper.class);
        when(orderMapper.listAllOrder()).thenReturn(list1);
        when(orderMapper.listOrderBySupplier(1)).thenReturn(list1);
        when(orderMapper.listOrderByGoods(1)).thenReturn(list1);
        when(orderMapper.findOrderById(1)).thenReturn(order1);

        orderService = new OrderServiceImpl(orderMapper);
    }

    @Test
    public void listAllOrderTest() {
        assertEquals(1, orderService.listAllOrder().size());
    }

    @Test
    public void listOrderBySupplierTest() {
        assertEquals(1, orderService.listOrderBySupplier(1).size());
    }

    @Test
    public void listOrderByGoodsTest() {
        assertEquals(1, orderService.listOrderByGoods(1).size());
    }

    @Test
    public void findOrderByIdTest() {
        assertEquals(1, orderService.findOrderById(1).getId());
        assertEquals(10, orderService.findOrderById(1).getAmount());
    }

    @Test
    public void addOrderTest() {
        orderService.addOrder(order1);
        verify(orderMapper, times(1)).addOrder(order1);
    }

    @Test
    public void deleteOrderTest() {
        orderService.deleteOrder(order1);
        verify(orderMapper, times(1)).deleteOrder(order1);
    }

    @Test
    public void removeOrderTest() {
        orderService.removeOrder(1);
        verify(orderMapper, times(1)).removeOrder(1);
    }

    @Test
    public void updateOrderTest() {
        orderService.updateOrder(order1);
        verify(orderMapper, times(1)).updateOrder(order1);
    }
}
