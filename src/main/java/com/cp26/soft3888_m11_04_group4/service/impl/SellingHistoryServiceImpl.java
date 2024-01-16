package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.GoodMapper;
import com.cp26.soft3888_m11_04_group4.dao.SellingHistoryMapper;
import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.GoodsInfo;
import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import com.cp26.soft3888_m11_04_group4.service.SellingHistoryService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author Yuyun Liu
 */

class ItemSell {
    private Integer id;
    private Integer totalAmount;
    private BigDecimal totalRevenue;
    private BigDecimal averageCost;
    private Integer frequency;
    private BigDecimal price;

    public ItemSell() {
    }

    public ItemSell(Integer id, Integer totalAmount, BigDecimal totalRevenue, BigDecimal averageCost, Integer frequency, BigDecimal price) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.totalRevenue = totalRevenue;
        this.averageCost = averageCost;
        this.frequency = frequency;
        this.price = price;
    }

    public BigDecimal getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(BigDecimal averageCost) {
        this.averageCost = averageCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

@Service
public class SellingHistoryServiceImpl implements SellingHistoryService {

    @Autowired
    private SellingHistoryMapper sellingHistoryMapper;

    @Autowired
    private GoodMapper goodMapper;

    /**
     * The function to get the range of past one week.
     * @param date
     * @return
     */
    private List<Date> getLastWeekStartAndEndDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 6);
        Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        Date end = c.getTime();

        List<Date> dates = new ArrayList<>();
        dates.add(start);
        dates.add(end);

        return dates;
    }

    /**
     * The function to get the amount status of all goods.
     * @return
     */
    private Map<Integer, Integer> getAllGoodsAmountStatus() {
        List<Goods> goods = this.goodMapper.queryAllGoods();

        Map<Integer, Integer> statistic = new HashMap<>();
        for(int i = 0; i < goods.size(); i++) {
            statistic.put(goods.get(i).getGoodId(), 0);
        }

        Date start = this.getLastWeekStartAndEndDate(new Date()).get(0);
        Date end = this.getLastWeekStartAndEndDate(new Date()).get(1);

        List<SellingHistory> sellingHistories = this.sellingHistoryMapper.listAllSellingHistory();
        for(int i = 0; i < sellingHistories.size(); i++) {
            if(sellingHistories.get(i).getSoldDate().compareTo(start) >= 0 && sellingHistories.get(i).getSoldDate().compareTo(end) <= 0) {
                Integer goodsId = sellingHistories.get(i).getId();
                Integer amount = sellingHistories.get(i).getAmount();
                Integer existAmount = statistic.get(goodsId);
                if(existAmount == null) {
                    existAmount = 0;
                }

                statistic.put(goodsId, (amount + existAmount));
            }
        }

        return statistic;
    }

    /**
     * The function to get all slow items in last week.
     * @return
     */
    @Override
    public List<Integer> listSlowItemsByWeek() {
        Map<Integer, Integer> statistic = this.getAllGoodsAmountStatus();

        // read from json
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/resources/config/ProfitabilityConfig.json");
            Object obj = parser.parse(reader);
            JSONObject config = (JSONObject) obj;
            int slowStandard = (int)(long)config.get("slow");

            List<Integer> result = new ArrayList<>();

            for(Map.Entry element: statistic.entrySet()) {
                Integer id = (Integer) element.getKey();
                Integer amount = (Integer) element.getValue();

                if(amount <= slowStandard) {
                    result.add(id);
                }
            }

            return result;

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }

    }

    /**
     * The function to get all fast items in last week.
     * @return
     */
    @Override
    public List<Integer> listFastItemsByWeek() {
        Map<Integer, Integer> statistic = this.getAllGoodsAmountStatus();

        // read from json
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/resources/config/ProfitabilityConfig.json");
            Object obj = parser.parse(reader);
            JSONObject config = (JSONObject) obj;
            int fastStandard = (int)(long)config.get("fast");

            List<Integer> result = new ArrayList<>();

            for(Map.Entry element: statistic.entrySet()) {
                Integer id = (Integer) element.getKey();
                Integer amount = (Integer) element.getValue();

                if(amount >= fastStandard) {
                    result.add(id);
                }
            }

            return result;

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * The function to calculate profit margin.
     * @param revenue
     * @param cost
     * @return
     */
    private BigDecimal getProfitMargin(BigDecimal revenue, BigDecimal cost) {
        return revenue.subtract(cost).divide(revenue, 2, RoundingMode.HALF_UP);
    }

    /**
     * The function to get the comprehensive status of all goods.
     * @return
     */
    private Map<Integer, ItemSell> getAllGoodsStatus() {
        List<GoodsInfo> goodsInfoList = this.goodMapper.findAllGoodsInfo();

        Map<Integer, ItemSell> statistic = new HashMap<>();
        for(int i = 0; i < goodsInfoList.size(); i++) {
            int goodsId = goodsInfoList.get(i).getGoodsId();
            BigDecimal averageCost = goodsInfoList.get(i).getAverageCost();
            System.out.println(averageCost);
            BigDecimal price = goodsInfoList.get(i).getPrice();
            ItemSell itemSell = new ItemSell(goodsId, 0, BigDecimal.ZERO, averageCost, 0, price);
            statistic.put(goodsId, itemSell);
        }

        Date start = this.getLastWeekStartAndEndDate(new Date()).get(0);
        Date end = this.getLastWeekStartAndEndDate(new Date()).get(1);

        List<SellingHistory> sellingHistories = this.sellingHistoryMapper.listAllSellingHistory();
        for(int i = 0; i < sellingHistories.size(); i++) {
            if(sellingHistories.get(i).getSoldDate().compareTo(start) >= 0 && sellingHistories.get(i).getSoldDate().compareTo(end) <= 0) {
                Integer goodsId = sellingHistories.get(i).getGoodsId();
                ItemSell itemSell = statistic.get(goodsId);

                if(itemSell != null) {
                    BigDecimal money = sellingHistories.get(i).getMoney();
                    BigDecimal existMoney = statistic.get(goodsId).getTotalRevenue();
                    itemSell.setTotalRevenue(existMoney.add(money));

                    Integer amount = sellingHistories.get(i).getAmount();
                    Integer existAmount = statistic.get(goodsId).getTotalAmount();
                    itemSell.setTotalAmount(existAmount + amount);

                    Integer existFrequency = statistic.get(goodsId).getFrequency();
                    itemSell.setFrequency(existFrequency + 1);
                }
            }
        }

        return statistic;
    }

    /**
     * The function to get all high-profit margin items in last week.
     * @return
     */
    @Override
    public List<Integer> listHighProfitabilityItemsByPM() {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/resources/config/ProfitabilityConfig.json");
            Object obj = parser.parse(reader);
            JSONObject config = (JSONObject) obj;
            BigDecimal profitMarginStandard = new BigDecimal((double)config.get("profitMargin"));

            Map<Integer, ItemSell> goodsStatus = this.getAllGoodsStatus();

            Map<Integer, BigDecimal> goodsProfitMargin = new HashMap<>();
            for(Map.Entry element: goodsStatus.entrySet()) {
                Integer id = (Integer) element.getKey();
                ItemSell itemSell = (ItemSell) element.getValue();

                BigDecimal revenue = itemSell.getPrice();
                BigDecimal cost = itemSell.getAverageCost();
                if(cost == null) {
                    continue;
                }
                BigDecimal profitMargin = this.getProfitMargin(revenue, cost);

                goodsProfitMargin.put(id, profitMargin);
            }

            List<Integer> highProfitMarginItems = new ArrayList<>();
            for(Map.Entry element: goodsProfitMargin.entrySet()) {
                if(((BigDecimal)element.getValue()).compareTo(profitMarginStandard) >= 0) {
                    highProfitMarginItems.add(((Integer)element.getKey()));
                }
            }

            return highProfitMarginItems;

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * The function to get all high-profit margin items in last week.
     * @return
     */
    @Override
    public List<Integer> listHighProfitabilityItemsByPMTimesFoC() {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/resources/config/ProfitabilityConfig.json");
            Object obj = parser.parse(reader);
            JSONObject config = (JSONObject) obj;
            BigDecimal profitMarginFocStandard = new BigDecimal((double)config.get("profitMarginFoc"));

            Map<Integer, ItemSell> goodsStatus = this.getAllGoodsStatus();

            Map<Integer, BigDecimal> goodsProfitMargin = new HashMap<>();
            for(Map.Entry element: goodsStatus.entrySet()) {
                Integer id = (Integer) element.getKey();
                ItemSell itemSell = (ItemSell) element.getValue();

                BigDecimal revenue = itemSell.getTotalRevenue();
                if(itemSell.getAverageCost() == null) {
                    continue;
                }
                BigDecimal cost = itemSell.getAverageCost().multiply(new BigDecimal(itemSell.getTotalAmount()));
                BigDecimal profitMargin = this.getProfitMargin(revenue, cost);

                goodsProfitMargin.put(id, profitMargin);
            }

            List<Integer> highProfitMarginItems = new ArrayList<>();
            for(Map.Entry element: goodsProfitMargin.entrySet()) {
                if(((BigDecimal)element.getValue()).compareTo(profitMarginFocStandard) >= 0) {
                    highProfitMarginItems.add(((Integer)element.getKey()));
                }
            }

            return highProfitMarginItems;

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }
}
