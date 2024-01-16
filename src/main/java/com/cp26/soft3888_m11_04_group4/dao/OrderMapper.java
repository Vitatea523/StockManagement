package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.Order;
import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yuyun Liu
 */
@Mapper
public interface OrderMapper {

    /**
     * The function to list all orders in the database.
     * @return
     */
    List<Order> listAllOrder();

    /**
     * The function to list all order from a single supplier.
     * @param supplierId
     * @return
     */
    List<Order> listOrderBySupplier(int supplierId);

    /**
     * The function to list all order of a goods.
     * @param goodsId
     * @return
     */
    List<Order> listOrderByGoods(int goodsId);

    /**
     * The function to find detail information of a goods by id.
     * @param orderId
     * @return
     */
    Order findOrderById(int orderId);

    /**
     * The function to add a new order in the system.
     * The order created will be in status "InProgress".
     * @param newOrder
     * @return
     */
    int addOrder(Order newOrder);

    /**
     * The function to mark an order as "deleted" status.
     * @param order
     * @return
     */
    int deleteOrder(Order order);

    /**
     * The function to remove an order from database.
     * @param orderId
     * @return
     */
    int removeOrder(int orderId);

    /**
     * The function to update detail information of an order.
     * @param order
     * @return
     */
    int updateOrder(Order order);

    /**
     * The function to reindex the table.
     * @return
     */
    int restartIndex();
}
