package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Yuyun Liu
 */
@Mapper
public interface SellingHistoryMapper {

    /**
     * The function to delete a selling history record.
     * @param id
     * @return
     */
    int deleteSellingHistory(int id);

    /**
     * The function to list all selling history of the system.
     * @return
     */
    List<SellingHistory> listAllSellingHistory();

    /**
     * The function to reindex the table.
     * @return
     */
    int restartIndex();

    /**
     * The function to all a selling history.
     * This function can only be called by a cashier role.
     * @param sellingHistoryList
     * @return
     */
    int addSellingHistoryListByCashier(@Param(value = "list") List<SellingHistory> sellingHistoryList);
}
