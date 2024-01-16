package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.Order;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private GoodMapper goodMapper;

    private static Order order1;
    private static Order order2;
    private static Supplier supplier;
    private static Goods goods;

    @BeforeClass
    public static void prepare() {
        supplier = new Supplier(1, "Company", "Contact", "fakeEmail", "1234");
        goods = new Goods(1, "Chocolate", new Long(273897328), "Unit", new BigDecimal(1.5), 0, 4,0, 10, 5, 1, "Food");
        order1 = new Order(1, 1, 1, new Date(), new Date(2021, 12, 3), 10, new BigDecimal(1.5), new BigDecimal(0.5), "InProgress");
        order2 = new Order(2, 1, 1, new Date(), new Date(2021, 12, 4), 12, new BigDecimal(3), new BigDecimal(0.6), "InProgress");
    }

    private void setup() {
        supplierMapper.addSupplier(supplier);
        goodMapper.addGood(goods);
        orderMapper.addOrder(order1);
        orderMapper.addOrder(order2);
    }

    private void destroy() {
        orderMapper.removeOrder(1);
        orderMapper.removeOrder(2);
        supplierMapper.deleteSupplier(1);
        goodMapper.deleteGoods(1);

        supplierMapper.restartIndex();
        orderMapper.restartIndex();
        goodMapper.restartIndex();
    }

    @Test
    public void listAllOrderTest() {
        setup();
        List<Order> orders = orderMapper.listAllOrder();
        assertNotNull(orders);
        assertTrue(orders.size() == 2);
        destroy();
    }

    @Test
    public void listOrderBySupplierTest() {
        setup();
        List<Order> orders = orderMapper.listOrderBySupplier(1);
        assertEquals(2, orders.size());
        destroy();
    }

    @Test
    public void listOrderByGoodsTest() {
        setup();
        List<Order> orders = orderMapper.listOrderByGoods(1);
        assertEquals(2, orders.size());
        destroy();
    }

    @Test
    public void findOrderByIdTest() {
        setup();
        Order order1 = orderMapper.findOrderById(1);
        assertEquals((Integer)1, order1.getSupplierId());
        assertEquals((Integer)1, order1.getGoodsId());
        assertEquals((Integer)10, order1.getAmount());

        Order order2 = orderMapper.findOrderById(2);
        assertEquals((Integer)1, order2.getSupplierId());
        assertEquals((Integer)1, order2.getGoodsId());
        assertEquals((Integer)12, order2.getAmount());
        destroy();
    }

    @Test
    public void addOrderTest() {
        goodMapper.addGood(goods);
        supplierMapper.addSupplier(supplier);
        orderMapper.addOrder(order1);

        List<Order> orders = orderMapper.listAllOrder();
        assertEquals(1, orders.size());
        assertEquals("InProgress", orders.get(0).getStatus());

        orderMapper.removeOrder(1);
        goodMapper.deleteGoods(1);
        supplierMapper.deleteSupplier(1);

        orderMapper.restartIndex();
        goodMapper.restartIndex();
        supplierMapper.restartIndex();
    }

    @Test
    public void updateOrderTest() {
        goodMapper.addGood(goods);
        supplierMapper.addSupplier(supplier);
        orderMapper.addOrder(order1);

        List<Order> orders = orderMapper.listAllOrder();
        assertEquals(1, orders.size());
        assertEquals("InProgress", orders.get(0).getStatus());

        orderMapper.updateOrder(new Order(1, 1, 1, new Date(), new Date(2021, 12, 3), 10, new BigDecimal(1.5), new BigDecimal(0.5), "Done"));
        orders = orderMapper.listAllOrder();
        assertEquals(1, orders.size());
        assertEquals("Done", orders.get(0).getStatus());

        orderMapper.removeOrder(1);
        goodMapper.deleteGoods(1);
        supplierMapper.deleteSupplier(1);

        orderMapper.restartIndex();
        goodMapper.restartIndex();
        supplierMapper.restartIndex();
    }

    @Test
    public void deleteOrderTest() {
        supplierMapper.addSupplier(supplier);
        goodMapper.addGood(goods);
        orderMapper.addOrder(order1);

        List<Order> orders = orderMapper.listAllOrder();
        assertEquals(1, orders.size());
        assertEquals("InProgress", orders.get(0).getStatus());

        orderMapper.deleteOrder(new Order(1, 1, 1, new Date(), new Date(2021, 12, 3), 10, new BigDecimal(1.5), new BigDecimal(0.5), "Deleted"));
        orders = orderMapper.listAllOrder();
        assertEquals(1, orders.size());
        assertEquals("Deleted", orders.get(0).getStatus());

        orderMapper.removeOrder(1);
        supplierMapper.deleteSupplier(1);
        goodMapper.deleteGoods(1);

        goodMapper.restartIndex();
        supplierMapper.restartIndex();
        orderMapper.restartIndex();
    }
}
