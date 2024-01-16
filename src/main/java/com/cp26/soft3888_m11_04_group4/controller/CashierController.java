package com.cp26.soft3888_m11_04_group4.controller;

import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.SellingHistory;
import com.cp26.soft3888_m11_04_group4.service.CashierService;
import com.cp26.soft3888_m11_04_group4.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("/checkOut")
public class CashierController {

    @Autowired
    private CashierService cashierService;

    /**
     * The function is to capture the URL—/checkOut/checkOutItems for cashiers to check out purchases of the customers.
     * @param sellingHistoryList
     * @return JosonResult(a json object for the front-end)
     */
    @PostMapping("checkOutItems")
    public JsonResult checkOutItems(@RequestBody  List<SellingHistory> sellingHistoryList){
        try{
            int result = cashierService.checkOutItems(sellingHistoryList);
            if("".equals(result+"")){
                return JsonResult.build(400, "failed: the account already exists, please try log in.", null);
            }else{
                return JsonResult.build(20000, "success: user account created!", null);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return JsonResult.build(400, "failed: the account already exists, please try log in.", null);
        }
    }

    /**
     * The function is to capture the URL—/checkOut/manualItem for cashiers to manually add items by providing the list of items.
     * @return JsonResult<List<Goods>>(the list of items)
     */
    @GetMapping("manualItem")
    public JsonResult<List<Goods>> manualItem(){
        List<Goods> result = cashierService.manualItem();
        return JsonResult.build(20000, "success", result);
    }
}
