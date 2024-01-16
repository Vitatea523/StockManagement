package com.cp26.soft3888_m11_04_group4.controller;

import com.cp26.soft3888_m11_04_group4.pojo.*;
import com.cp26.soft3888_m11_04_group4.service.*;
import com.cp26.soft3888_m11_04_group4.service.GoodService;
import com.cp26.soft3888_m11_04_group4.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * @author Yuyun Liu
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private SupplierGoodsService supplierGoodsService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private GoodService goodService;

    @Autowired
    private InventoryService inventoryService;

    /**
     * The function is to list all orders in the system.
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("")
    public JsonResult<List<Order>> listAllOrder() {
        List<Order> result = this.orderService.listAllOrder();
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is to list all orders in the system according to supplier id.
     * @param supplierId
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/supplierId/{supplierId}")
    public JsonResult<List<Order>> listOrderBySupplier(@PathVariable int supplierId) {
        List<Order> result = this.orderService.listOrderBySupplier(supplierId);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is to list all orders in the system according to goods id.
     * @param goodsId
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/goodsId/{goodsId}")
    public JsonResult<List<Order>> listOrderByGoods(@PathVariable int goodsId) {
        List<Order> result = this.orderService.listOrderByGoods(goodsId);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is to get the in the system according to its id.
     * @param orderId
     * @return JsonResult(a json object for the front-end)
     */
    @GetMapping("/id/{orderId}")
    public JsonResult<Order> findOrderById(@PathVariable int orderId) {
        Order result = this.orderService.findOrderById(orderId);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * "Cancel" will not add a new order to database, but "save" will create a new order
     * @param newOrder
     * @return
     */
    @PostMapping("/email")
    public JsonResult<Order> submitOrder(@RequestBody Order newOrder) {
        newOrder.setDate(null);
        newOrder.setExpireDate(null);
        newOrder.setStatus("InProgress");

        Integer supplierId = newOrder.getSupplierId();
        Integer goodsId = newOrder.getGoodsId();
        BigDecimal costPerCarton = this.supplierGoodsService.findCostPerCarton(supplierId, goodsId);
        BigDecimal costPerUnit = this.supplierGoodsService.findCostPerUnit(supplierId, goodsId);

        newOrder.setCurrentCostPerCarton(costPerCarton);
        newOrder.setCurrentCostPerUnit(costPerUnit);

        Integer result = this.orderService.addOrder(newOrder);

        if(result == 1) {
            Supplier supplier = this.supplierService.findSupplierById(supplierId);
            Goods goods = this.goodService.queryGoodsById(goodsId);
            SupplierGoods supplierGoods = this.supplierGoodsService.querySupplierGoodsById(supplierId, goodsId);

            Integer amount = newOrder.getAmount();

            System.out.println("################################### Prepare to send email!");
            if(this.sendEmailService.sendEmail(supplier, goods, supplierGoods, amount) == 1) {
                System.out.println("################################### Finish to send email!");
                return JsonResult.build(20000, "Success", newOrder);
            } else {
                return  JsonResult.build(400, "Fail to send email", newOrder);
            }
        } else {
            return JsonResult.build(400, "Fail to order", newOrder);
        }
    }

    /**
     * Once confirm the receipt of the goods, the inventory will be created.
     * @param
     * @return
     */
    @PutMapping("/confirm")
    public JsonResult<Integer> confirmReceipt(@RequestParam() int oid,@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date expireDate) {
        Order order = this.orderService.findOrderById(oid);

        order.setStatus("Done");
        order.setExpireDate(expireDate);

        Date date = new Date();
        order.setDate(date);

        Integer result = this.orderService.updateOrder(order);
        if(result == 1) {
            Integer goodsId = order.getGoodsId();
            Integer orderId = order.getId();
            Integer amount = order.getAmount();

            // Create an inventory
            BigDecimal price = goodService.queryGoodsById(goodsId).getPrice();
            BigDecimal cost = orderService.findOrderById(orderId).getCurrentCostPerUnit();
            BigDecimal GPPercent = price.subtract(cost).divide(price, 2, RoundingMode.HALF_UP);
            Inventory inventory = new Inventory(goodsId, orderId, amount, GPPercent);
            this.inventoryService.addInventory(inventory);

            // Change goods amount
            Goods goods = this.goodService.queryGoodsById(goodsId);
            goods.setAmount(goods.getAmount() + amount);
            this.goodService.updateGoodsById(goods);

            return JsonResult.build(20000, "Success", null);
        } else {
            return JsonResult.build(400, "Fail", null);
        }
    }

    /**
     * Update count, update cost, update status for "delete"
     * @param order
     * @return
     */
    @DeleteMapping("/delete")
    public JsonResult<Integer> deleteOrder(@RequestBody Order order) {
        order.setStatus("Deleted");
        Integer result = this.orderService.updateOrder(order);

        if(result == 1) {
            return JsonResult.build(20000, "Success", null);
        } else {
            return JsonResult.build(400, "Fail", null);
        }
    }
}
