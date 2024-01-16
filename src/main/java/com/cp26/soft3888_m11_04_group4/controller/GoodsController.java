package com.cp26.soft3888_m11_04_group4.controller;

import com.cp26.soft3888_m11_04_group4.pojo.*;
import com.cp26.soft3888_m11_04_group4.service.GoodService;
import com.cp26.soft3888_m11_04_group4.service.SellingHistoryService;
import com.cp26.soft3888_m11_04_group4.vo.JsonResult;
import org.apache.poi.hssf.usermodel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private SellingHistoryService sellingHistoryService;

    /**
     * The function is to get the target by the good id.
     * @param id The id of the target good.
     * @return A JsonResult Object, which contains the target good.
     */
    @GetMapping("/id")
    public JsonResult<Goods> queryGoodsById(@RequestParam int id) {
        Goods result = this.goodService.queryGoodsById(id);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is to get all the goods from the database.
     * @return A JsonResult Object, which contains a list with all the goods in the database.
     */
    @GetMapping("/listGoods")
    public JsonResult<List<Goods>> listGoods(){
        List<Goods> result =  goodService.listGoods();
        return JsonResult.build(20000, "success", result);
    }

    /**
     * The function is to get the stock level report.
     * @return A JsonResult Object, which contains a list with the stock level report.
     *
     */
    @GetMapping("/displayStockLevelReport")
    public JsonResult<List<StockLevelReport>> displayStockLevelReport(){
        List<StockLevelReport> result =  goodService.listStockLevel();
        return JsonResult.build(20000, "success", result);
    }

    /**
     * The function is to export the excel file for stock level report.
     * @param response The front end request.
     * @throws IOException if an I/O occurs during the flushing of the response.
     */
    @GetMapping("/generateStockLevelReport")
    public void excelDownload(HttpServletResponse response)throws IOException {
        List<String> header = new ArrayList<>();
        header.add("Item ID");
        header.add("Name");
        header.add("Category");
        header.add("Stock Level");
        header.add("Sales Frequency");

        List<StockLevelReport> result =  goodService.listStockLevel();

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("Stock Level Report");

        sheet.setDefaultColumnWidth(30);

        HSSFRow headRow = sheet.createRow(0);

        for (int i = 0; i < header.size(); i++) {
            HSSFCell cell = headRow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header.get(i));
            cell.setCellValue(text);
        }

        int rowIndex = 1;
        for(int i = 0; i < result.size(); i++){
            HSSFRow row = sheet.createRow(rowIndex++);
            HSSFCell cell01 = row.createCell(0);
            HSSFRichTextString itemID = new HSSFRichTextString(result.get(i).getGoodId().toString());
            cell01.setCellValue(itemID);

            HSSFCell cell02 = row.createCell(1);
            HSSFRichTextString name = new HSSFRichTextString(result.get(i).getName());
            cell02.setCellValue(name);

            HSSFCell cell03 = row.createCell(2);
            HSSFRichTextString category = new HSSFRichTextString(result.get(i).getCategoryName());
            cell03.setCellValue(category);

            HSSFCell cell04 = row.createCell(3);
            HSSFRichTextString stockLevel = new HSSFRichTextString(result.get(i).getStandard());
            cell04.setCellValue(stockLevel);

            HSSFCell cell05 = row.createCell(4);
            HSSFRichTextString salesFrequencyTemp = new HSSFRichTextString(result.get(i).getSalesFrequency().toString());
            cell05.setCellValue(salesFrequencyTemp);
        }


        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=Stock_Excel.xls");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * The function is to add a new good to the database.
     * @param goods The good needs to be added.
     * @return A JsonResult Object, which contains the added good.
     */
    @PostMapping("/add")
    public JsonResult<Goods> addNewGood(@RequestBody Goods goods) {
        Integer result = goodService.addNewGood(goods);

        if (result == 1) {
            return JsonResult.build(20000, "Success", goods);
        } else {
            return JsonResult.build(400, "Fail", goods);
        }
    }

    /**
     * The function is to get the target goods by name.
     * @param name The name of the target goods.
     * @return A JsonResult Object, which contains a list with the target goods.
     */
    @GetMapping("/name/{name}")
    public JsonResult<List<Goods>> queryGoodByName(@PathVariable String name) {
        List<Goods> result = goodService.queryGoodsByName(name);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is to edit the target good, it will change the data of the target good.
     * @param goods The good needs to be edited.
     * @return A JsonResult Object, which contains the edited good.
     */
    @PostMapping("/edit")
    public JsonResult<Goods> updateGoodById(@RequestBody Goods goods) {
        Integer result = goodService.updateGoodsById(goods);

        if (result == 1) {
            return JsonResult.build(20000, "Success", goods);
        }else {
            return JsonResult.build(400, "Fail", goods);
        }
    }

    /**
     * The function is to delete the target good from the database;
     * @param id The id of the target good.
     * @return 1 for success, other for fail.
     */
    @DeleteMapping("/delete")
    public JsonResult<Integer> deleteGood(@RequestParam int id) {
        Integer result = goodService.deleteGoods(id);

        if (result == 1) {
            return JsonResult.build(20000, "Success", null);
        } else {
          return JsonResult.build(400, "Fail", null);
        }
    }

    /**
     * The function is find the target good by APN.
     * @param APN The APN of the target good.
     * @return A JsonResult Object, which contains the target good.
     */
    @GetMapping("/APN/{APN}")
    public JsonResult<Goods> queryGoodsById(@PathVariable Long APN) {
        Goods result = this.goodService.queryGoodsByAPN(APN);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is find the target good by category.
     * @param category The category of the target good.
     * @return A JsonResult Object, which contains the target good.
     */
    @GetMapping("category/{category}")
    public JsonResult<List<Goods>> listGoodsByCategory(@PathVariable String category){
        List<Goods> result =  goodService.queryGoodsByCategory(category);
        return JsonResult.build(20000, "Success", result);
    }

    /**
     * The function is to find the best sell goods.
     * @return A JsonResult Object, which contains the best sell goods.
     */
    @GetMapping("/fast")
    public JsonResult<List<Integer>> listFastGoodsIds() {
        List<Integer> result = sellingHistoryService.listFastItemsByWeek();
        if(result == null) {
            return JsonResult.build(400, "Fail", null);
        } else {
            return JsonResult.build(20000, "Success", result);
        }
    }

    /**
     * The function is to find the slow sell goods.
     * @return A JsonResult Object, which contains the slow sell goods.
     */
    @GetMapping("/slow")
    public JsonResult<List<Integer>> listSlowGoodsIds() {
        List<Integer> result = sellingHistoryService.listSlowItemsByWeek();
        if(result == null) {
            return JsonResult.build(400, "Fail", null);
        } else {
            return JsonResult.build(20000, "Success", result);
        }
    }

    /**
     * The function is to list the high profit goods of the system.
     * @return A JsonResult Object, which contains the high profit goods.
     */
    @GetMapping("/highProfitMargin")
    public JsonResult<List<Integer>> listHighProfitGoodsIds() {
        List<Integer> result = sellingHistoryService.listHighProfitabilityItemsByPM();
        if(result == null) {
            return JsonResult.build(400, "Fail", null);
        } else {
            return JsonResult.build(20000, "Success", result);
        }
    }

    /**
     * The function is to list the high profit margin and frequency of sell goods of the system.
     * @return A JsonResult Object, which contains the high profit margin and frequency of sell goods.
     */
    @GetMapping("/highProfitMarginAndFrequencyOfSell")
    public JsonResult<List<Integer>> listHighProfitAndFrequencyOfSellGoodsIds() {
        List<Integer> result = sellingHistoryService.listHighProfitabilityItemsByPMTimesFoC();
        if(result == null) {
            return JsonResult.build(400, "Fail", null);
        } else {
            return JsonResult.build(20000, "Success", result);
        }
    }

    /**
     * The function is to find the cheapest supplier of the target goods.
     * @param goods The good want to be ordered.
     * @return A JsonResult Object, which contains the cheapest supplier of the target goods.
     */
    @GetMapping("/cheapestPrice")
    public JsonResult<List<CheapestGood>> findTheCheapestSupplier(@RequestBody Goods goods) {
        List<CheapestGood> suppliers = goodService.findTheCheapestSupplier(goods);
        if(suppliers == null) {
            return JsonResult.build(400, "Fail", null);
        } else {
            return JsonResult.build(20000, "Success", suppliers);
        }
    }
}
