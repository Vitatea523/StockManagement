package com.cp26.soft3888_m11_04_group4.controller;

import com.cp26.soft3888_m11_04_group4.pojo.Inventory;
import com.cp26.soft3888_m11_04_group4.pojo.InventoryOrder;
import com.cp26.soft3888_m11_04_group4.service.InventoryService;
import com.cp26.soft3888_m11_04_group4.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yuyun Liu
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    /**
     * The function is to generate an inventory list with desired format.
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("list")
    public JsonResult<List<InventoryOrder>> generateInventoryList() {
        List<InventoryOrder> result = this.inventoryService.generateInventoryList();
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is to generate an inventory list of a specific goods with desired format.
     * @param id
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/list/goodsId")
    public JsonResult<List<InventoryOrder>> generateInventoryListByGoodsId(@RequestParam int id) {
        List<InventoryOrder> result = this.inventoryService.generateInventoryListByGoodsId(id);
        return JsonResult.build(20000, "Success", result);
    }
}
