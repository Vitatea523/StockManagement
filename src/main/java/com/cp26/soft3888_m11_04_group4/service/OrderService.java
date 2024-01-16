package com.cp26.soft3888_m11_04_group4.service;

import com.cp26.soft3888_m11_04_group4.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yuyun Liu
 */
public interface OrderService {

    /**
     * The function to list all orders in the system.
     * @return
     */
    List<Order> listAllOrder();

    /**
     * The function to list all orders from a supplier.
     * @param supplierId
     * @return
     */
    List<Order> listOrderBySupplier(@Param("businessName") int supplierId);

    /**
     * The function to list all orders for a goods.
     * @param goodsId
     * @return
     */
    List<Order> listOrderByGoods(@Param("goodsId") int goodsId);

    /**
     * The function to find an order by id.
     * @param orderId
     * @return
     */
    Order findOrderById(@Param("orderId") int orderId);

    /**
     * The function that add an order.
     * @param newOrder
     * @return
     */
    int addOrder(Order newOrder);

    /**
     * The function to mark order be "deleted" state.
     * @param order
     * @return
     */
    int deleteOrder(Order order);

    /**
     * The function to remove an order.
     * @param orderId
     * @return
     */
    int removeOrder(@Param("orderId") int orderId);

    /**
     * The function to update order information.
     * @param order
     * @return
     */
    int updateOrder(Order order);
}
