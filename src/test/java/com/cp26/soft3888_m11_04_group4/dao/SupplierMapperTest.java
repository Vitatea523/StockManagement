package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierMapperTest {

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private SupplierGoodsMapper supplierGoodsMapper;

    private static Supplier supplier1;
    private static Supplier supplier2;

    @BeforeClass
    public static void prepare() {
        supplier1 = new Supplier(1, "DefaultCompany1", "Alice", "alice@gmail.com", "972091820");
        supplier2 = new Supplier(2, "DefaultCompany2", "Bob", "bob@gmail.com", "3762782");
    }

    private void setup() {
        supplierMapper.addSupplier(supplier1);
        supplierMapper.addSupplier(supplier2);
    }

    private void destroy() {
        supplierMapper.deleteSupplier(supplier1.getId());
        supplierMapper.deleteSupplier(supplier2.getId());
        supplierMapper.restartIndex();
    }

    @Test
    public void findAllSuppliersTest() {
        setup();

        List<Supplier> allSuppliers = supplierMapper.findAllSuppliers();

        assertNotNull(allSuppliers);
        assertTrue(allSuppliers.size() == 2);

        assertTrue(allSuppliers.get(0) instanceof Supplier);
        assertTrue(allSuppliers.get(0).getBusinessName().equals("DefaultCompany1"));
        assertTrue(allSuppliers.get(0).getContactName().equals("Alice"));
        assertTrue(allSuppliers.get(0).getEmail().equals("alice@gmail.com"));
        assertTrue(allSuppliers.get(0).getPhoneNumber().equals("972091820"));

        destroy();
    }

    @Test
    public void findSupplierByIdTest1() {
        setup();

        Supplier supplier = supplierMapper.findSupplierById(1);
        assertNotNull(supplier);
        assertEquals("DefaultCompany1", supplier.getBusinessName());
        assertEquals("Alice", supplier.getContactName());
        assertEquals("alice@gmail.com", supplier.getEmail());
        assertEquals("972091820", supplier.getPhoneNumber());

        destroy();
    }

    @Test
    public void findSupplierByIdTest2() {
        setup();

        Supplier supplier = supplierMapper.findSupplierById(2);
        assertNotNull(supplier);
        assertEquals("DefaultCompany2", supplier.getBusinessName());
        assertEquals("Bob", supplier.getContactName());
        assertEquals("bob@gmail.com", supplier.getEmail());
        assertEquals("3762782", supplier.getPhoneNumber());

        destroy();
    }

    @Test
    public void addSupplierSizeTest() {
        List<Supplier> allSuppliers = supplierMapper.findAllSuppliers();

        supplierMapper.addSupplier(supplier1);
        allSuppliers = supplierMapper.findAllSuppliers();

        assertEquals(1, allSuppliers.size());

        supplierMapper.deleteSupplier(supplier1.getId());

        allSuppliers = supplierMapper.findAllSuppliers();
        assertEquals(0, allSuppliers.size());

        supplierMapper.restartIndex();
    }

    @Test
    public void addSupplierContentTest() {
        supplierMapper.addSupplier(supplier1);
        Supplier supplier = supplierMapper.findSupplierById(1);

        assertNotNull(supplier);
        assertEquals("DefaultCompany1", supplier.getBusinessName());
        assertEquals("Alice", supplier.getContactName());
        assertEquals("alice@gmail.com", supplier.getEmail());
        assertEquals("972091820", supplier.getPhoneNumber());

        supplierMapper.deleteSupplier(1);
        supplierMapper.restartIndex();
    }

    @Test
    public void findSupplierByNameTest() {
        setup();

        List<Supplier> companies = supplierMapper.findSupplierByName("DefaultCompany1");
        assertEquals(1, companies.size());
        assertEquals("Alice", companies.get(0).getContactName());
        assertEquals("alice@gmail.com", companies.get(0).getEmail());
        assertEquals("972091820", companies.get(0).getPhoneNumber());

        destroy();
    }

    @Test
    public void findSupplierByGoodsIdTest() {
        setup();

        Goods goods = new Goods(1, "Chocolate", new Long(273897328), "Unit", new BigDecimal(1.5), 0, 4,0, 10, 5, 1, "Food");
        this.goodMapper.addGood(goods);

        SupplierGoods supplierGoods = new SupplierGoods(1, 1, new BigDecimal(10), new BigDecimal(0.5));
        this.supplierGoodsMapper.addSupplierGoods(supplierGoods);

        List<GoodSupplier> suppliers = this.supplierMapper.findSupplierByGoodsId(1);
        assertEquals(1, suppliers.size());
        assertEquals(new Integer(1), suppliers.get(0).getId());

        this.supplierGoodsMapper.deleteSupplierGoods(1, 1);
        this.goodMapper.deleteGoods(1);
        this.goodMapper.restartIndex();
        this.goodMapper.deleteGoods(1);
        this.goodMapper.restartIndex();

        destroy();
    }

    @Test
    public void updateSupplierTest() {
        setup();

        supplier1.setBusinessName("newName");
        supplier1.setContactName("Alex");
        supplier1.setEmail("alex@gmail.com");
        supplier1.setPhoneNumber("291892");

        this.supplierMapper.updateSupplier(supplier1);

        Supplier supplier = supplierMapper.findSupplierById(1);

        assertNotNull(supplier);
        assertEquals("newName", supplier.getBusinessName());
        assertEquals("Alex", supplier.getContactName());
        assertEquals("alex@gmail.com", supplier.getEmail());
        assertEquals("291892", supplier.getPhoneNumber());

        supplier1.setBusinessName("DefaultCompany1");
        supplier1.setContactName("Alice");
        supplier1.setEmail("alice@gmail.com");
        supplier1.setPhoneNumber("972091820");

        destroy();
    }

    @Test
    public void goodsOnOfferTest() {
        setup();

        Goods goods = new Goods(1, "Chocolate", new Long(273897328), "Unit", new BigDecimal(1.5), 0, 4,0, 10, 5, 1, "Food");
        this.goodMapper.addGood(goods);

        SupplierGoods supplierGoods = new SupplierGoods(1, 1, new BigDecimal(10), new BigDecimal(0.5));
        this.supplierGoodsMapper.addSupplierGoods(supplierGoods);

        List<GoodsOnOffer> goodsOnOffer1 = supplierMapper.getGoodsOnOffer(1);
        assertEquals(1, goodsOnOffer1.size());

        List<GoodsOnOffer> goodsOnOffer2 = supplierMapper.getGoodsOnOffer(2);
        assertEquals(0, goodsOnOffer2.size());

        this.supplierGoodsMapper.deleteSupplierGoods(1, 1);
        this.goodMapper.deleteGoods(1);
        this.goodMapper.restartIndex();
        this.goodMapper.deleteGoods(1);
        this.goodMapper.restartIndex();

        destroy();
    }

}
