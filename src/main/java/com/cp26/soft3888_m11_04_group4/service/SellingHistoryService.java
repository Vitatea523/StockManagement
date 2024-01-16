package com.cp26.soft3888_m11_04_group4.service;

import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;

import java.util.List;
import java.util.Map;

/**
 * @author Yuyun Liu
 */
public interface SellingHistoryService {
    /**
     * The function to get all slow items in last week.
     * @return
     */
    List<Integer> listSlowItemsByWeek();

    /**
     * The function to get all fast items in last week.
     * @return
     */
    List<Integer> listFastItemsByWeek();

    /**
     * The function to get all high-profit margin items in last week.
     * @return
     */
    List<Integer> listHighProfitabilityItemsByPM();

    /**
     * The function to get all high-profit margin items in last week.
     * @return
     */
    List<Integer> listHighProfitabilityItemsByPMTimesFoC();
}
