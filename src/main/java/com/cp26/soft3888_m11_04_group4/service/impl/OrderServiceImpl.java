package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.OrderMapper;
import com.cp26.soft3888_m11_04_group4.pojo.Order;
import com.cp26.soft3888_m11_04_group4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyun Liu
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * The function to list all orders in the system.
     * @return
     */
    @Override
    public List<Order> listAllOrder() {
        return this.orderMapper.listAllOrder();
    }

    /**
     * The function to list all orders from a supplier.
     * @param supplierId
     * @return
     */
    @Override
    public List<Order> listOrderBySupplier(int supplierId) {
        return this.orderMapper.listOrderBySupplier(supplierId);
    }

    /**
     * The function to list all orders for a goods.
     * @param goodsId
     * @return
     */
    @Override
    public List<Order> listOrderByGoods(int goodsId) {
        return this.orderMapper.listOrderByGoods(goodsId);
    }

    /**
     * The function to find an order by id.
     * @param orderId
     * @return
     */
    @Override
    public Order findOrderById(int orderId) {
        return this.orderMapper.findOrderById(orderId);
    }

    /**
     * The function that add an order.
     * @param newOrder
     * @return
     */
    @Override
    public int addOrder(Order newOrder) {
        return this.orderMapper.addOrder(newOrder);
    }

    /**
     * The function to mark order be "deleted" state.
     * @param order
     * @return
     */
    @Override
    public int deleteOrder(Order order) {
        return this.orderMapper.deleteOrder(order);
    }

    /**
     * The function to remove an order.
     * @param orderId
     * @return
     */
    @Override
    public int removeOrder(int orderId) {
        return this.orderMapper.removeOrder(orderId);
    }

    /**
     * The function to update order information.
     * @param order
     * @return
     */
    @Override
    public int updateOrder(Order order) {
        return this.orderMapper.updateOrder(order);
    }
}
