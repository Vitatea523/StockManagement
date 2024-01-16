package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import com.cp26.soft3888_m11_04_group4.pojo.SupplierGoods;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierGoodsMapperTest {

    @Autowired
    private SupplierGoodsMapper supplierGoodsMapper;

    @Autowired
    private GoodMapper goodsMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    private static Supplier supplier1;
    private static Goods goods1;
    private static Goods goods2;

    private static SupplierGoods supplierGoods1;
    private static SupplierGoods supplierGoods2;

    @BeforeClass
    public static void prepare(){
        supplier1 = new Supplier(1, "DefaultCompany1", "Alice", "alice@gmail.com", "972091820");

        goods1 = new Goods(1, "Chocolate1", new Long(273897328), "Unit", new BigDecimal(1.5), 0, 4,0, 10, 5, 1, "Food");
        goods2 = new Goods(2, "Chocolate2", new Long(273897328), "Unit", new BigDecimal(1.5), 0, 4,0, 10, 5, 1, "Food");

        supplierGoods1 = new SupplierGoods(1, 1, new BigDecimal(28), new BigDecimal(2));
        supplierGoods2 = new SupplierGoods(1, 2, new BigDecimal(30), new BigDecimal(2.5));
    }

    private void setup(){
        supplierMapper.addSupplier(supplier1);
        goodsMapper.addGood(goods1);
        goodsMapper.addGood(goods2);
        supplierGoodsMapper.addSupplierGoods(supplierGoods1);
        supplierGoodsMapper.addSupplierGoods(supplierGoods2);
    }

    private void destroy(){
        supplierGoodsMapper.deleteSupplierGoods(1, 1);
        supplierGoodsMapper.deleteSupplierGoods(1, 2);

        supplierMapper.deleteSupplier(1);
        goodsMapper.deleteGoods(1);
        goodsMapper.deleteGoods(2);

        supplierMapper.restartIndex();
        goodsMapper.restartIndex();
    }

    @Test
    public void findCostPerCartonTest() {
        setup();
        assertEquals(new BigDecimal("28.00"), supplierGoodsMapper.findCostPerCarton(supplierGoods1.getSupplierId(), supplierGoods1.getGoodsId()));
        assertEquals(new BigDecimal("30.00"), supplierGoodsMapper.findCostPerCarton(supplierGoods2.getSupplierId(), supplierGoods2.getGoodsId()));
        destroy();
    }

    @Test
    public void findCostPerUnitTest() {
        setup();
        assertEquals(new BigDecimal("2.00"), supplierGoodsMapper.findCostPerUnit(supplierGoods1.getSupplierId(), supplierGoods1.getGoodsId()));
        assertEquals(new BigDecimal("2.50"), supplierGoodsMapper.findCostPerUnit(supplierGoods2.getSupplierId(), supplierGoods2.getGoodsId()));
        destroy();
    }

    @Test
    public void querySupplierGoodsByIdTest() {
        setup();

        SupplierGoods supplierGoods01Temp = supplierGoodsMapper.querySupplierGoodsById(1, 1);

        assertNotNull(supplierGoods01Temp);
        assertEquals(1, supplierGoods01Temp.getSupplierId());
        assertEquals(1, supplierGoods01Temp.getGoodsId());
        assertEquals(new BigDecimal("28.00"), supplierGoods01Temp.getCostPerCarton());
        assertEquals(new BigDecimal("2.00"), supplierGoods01Temp.getCostPerUnit());

        SupplierGoods supplierGoods02Temp = supplierGoodsMapper.querySupplierGoodsById(1, 2);

        assertNotNull(supplierGoods02Temp);
        assertEquals(1, supplierGoods02Temp.getSupplierId());
        assertEquals(2, supplierGoods02Temp.getGoodsId());
        assertEquals(new BigDecimal("30.00"), supplierGoods02Temp.getCostPerCarton());
        assertEquals(new BigDecimal("2.50"), supplierGoods02Temp.getCostPerUnit());

        destroy();
    }

    @Test
    public void addSupplierGoodsTest() {
        supplierMapper.addSupplier(supplier1);
        goodsMapper.addGood(goods1);
        goodsMapper.addGood(goods2);
        supplierGoodsMapper.addSupplierGoods(supplierGoods1);
        supplierGoodsMapper.addSupplierGoods(supplierGoods2);

        SupplierGoods supplierGoods01 = supplierGoodsMapper.querySupplierGoodsById(1, 1);

        assertNotNull(supplierGoods01);
        assertEquals(1, supplierGoods01.getSupplierId());
        assertEquals(1, supplierGoods01.getGoodsId());

        SupplierGoods supplierGoods02 = supplierGoodsMapper.querySupplierGoodsById(1, 2);

        assertNotNull(supplierGoods02);
        assertEquals(1, supplierGoods02.getSupplierId());
        assertEquals(2, supplierGoods02.getGoodsId());

        destroy();
    }

    @Test
    public void deletSupplierGoodsTest() {
        setup();

        SupplierGoods supplierGoods01 = supplierGoodsMapper.querySupplierGoodsById(1, 1);

        assertNotNull(supplierGoods01);
        assertEquals(1, supplierGoods01.getSupplierId());
        assertEquals(1, supplierGoods01.getGoodsId());

        SupplierGoods supplierGoods02 = supplierGoodsMapper.querySupplierGoodsById(1, 2);

        assertNotNull(supplierGoods02);
        assertEquals(1, supplierGoods02.getSupplierId());
        assertEquals(2, supplierGoods02.getGoodsId());

        supplierGoodsMapper.deleteSupplierGoods(1, 1);

        assertEquals(null, this.supplierGoodsMapper.querySupplierGoodsById(1, 1));

        supplierGoodsMapper.deleteSupplierGoods(1, 2);

        assertEquals(null, this.supplierGoodsMapper.querySupplierGoodsById(1, 2));

        supplierMapper.deleteSupplier(1);
        goodsMapper.deleteGoods(1);
        goodsMapper.deleteGoods(2);
        supplierMapper.restartIndex();
        goodsMapper.restartIndex();
    }
}