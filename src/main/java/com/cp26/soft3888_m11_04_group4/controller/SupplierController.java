package com.cp26.soft3888_m11_04_group4.controller;

import com.cp26.soft3888_m11_04_group4.pojo.*;
import com.cp26.soft3888_m11_04_group4.service.SupplierGoodsService;
import com.cp26.soft3888_m11_04_group4.service.SupplierService;
import com.cp26.soft3888_m11_04_group4.vo.JsonResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Yuyun Liu
 */

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierGoodsService supplierGoodsService;

    /**
     * The function to list all suppliers in the system.
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/list")
    public JsonResult<List<Supplier>> findAllSuppliers() {
        List<Supplier> result = supplierService.findAllSuppliers();
        System.out.println(result);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function to find supplier by name.
     * @param name
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/name/{name}")
    public JsonResult<List<Supplier>> findSupplierByName(@PathVariable String name) {
        List<Supplier> result = supplierService.findSupplierByName(name);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function to find supplier by id.
     * @param id
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/id")
    public JsonResult<Supplier> findSupplierById(@RequestParam int id) {
        Supplier result = supplierService.findSupplierById(id);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is to find supplier goods by id.
     * @param id
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/goodsId")
    public JsonResult<List<GoodSupplier>> findSupplierByGoodsId(@RequestParam int id) {
        List<GoodSupplier> result = supplierService.findSupplierByGoodsId(id);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function to add a new supplier in the system.
     * @param newSupplier
     * @return JsonResult(a json object for the front-end)
     */
    @PostMapping("/add")
    public JsonResult<Supplier> addSupplier(@RequestBody Supplier newSupplier) {
        Integer result = supplierService.addSupplier(newSupplier);

        if(result == 1) {
            return JsonResult.build(20000, "Success", newSupplier);
        } else {
            return JsonResult.build(400, "Fail", newSupplier);
        }
    }

    /**
     * The function is to update the information of a supplier.
     * @param supplier
     * @return JsonResult(a json object for the front-end)
     */
    @PutMapping("/edit")
    public JsonResult<Supplier> updateSupplier(@RequestBody Supplier supplier) {
        Integer result = supplierService.updateSupplier(supplier);

        if(result == 1) {
            return JsonResult.build(20000, "Success", supplier);
        } else {
            return JsonResult.build(400, "Fail", supplier);
        }
    }

    /**
     * The function to delete a supplier from the system.
     * @param id
     * @return JsonResult(a json object for the front-end)
     */
    @DeleteMapping("/delete")
    public JsonResult<Integer> deleteSupplier(@RequestParam int id) {
        Integer result = supplierService.deleteSupplier(id);

        if(result == 1) {
            return JsonResult.build(20000, "Success", null);
        } else {
            return JsonResult.build(400, "Fail", null);
        }
    }

    /**
     * The function to add a goods that can be offered by the supplier.
     * @param supplierGoods
     * @return JsonResult(a json object for the front-end)
     */
    @PostMapping("/supply")
    public JsonResult<SupplierGoods> addSupplyGoods(@RequestBody SupplierGoods supplierGoods) {
        Integer result = supplierGoodsService.addSupplyGoods(supplierGoods);

        if(result == 1) {
            return JsonResult.build(20000, "Success", supplierGoods);
        } else {
            return JsonResult.build(400, "Fail", supplierGoods);
        }
    }

    /**
     * The function to get all goods offered by the supplier.
     * @param id
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/offer")
    public JsonResult<List<GoodsOnOffer>> goodsOnOffer(@RequestParam int id) {
        List<GoodsOnOffer> result = supplierService.getGoodsOnOffer(id);
        return JsonResult.build(20000, "Success", result);
    }

}
