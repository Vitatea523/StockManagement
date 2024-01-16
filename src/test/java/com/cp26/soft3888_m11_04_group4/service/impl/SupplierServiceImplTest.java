package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.SupplierMapper;
import com.cp26.soft3888_m11_04_group4.pojo.GoodsOnOffer;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import com.cp26.soft3888_m11_04_group4.service.SupplierService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierServiceImplTest {

    private static SupplierService supplierService;
    private static Supplier supplier1;
    private static Supplier supplier2;
    private static SupplierMapper supplierMapper;

    @BeforeClass
    public static void prepare() {
        supplier1 = mock(Supplier.class);
        when(supplier1.getId()).thenReturn(1);
        when(supplier1.getBusinessName()).thenReturn("DefaultCompany1");

        supplier2 = mock(Supplier.class);
        when(supplier2.getId()).thenReturn(2);
        when(supplier2.getBusinessName()).thenReturn("DefaultCompany2");

        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier1);
        suppliers.add(supplier2);

        supplierMapper = mock(SupplierMapper.class);

        when(supplierMapper.findAllSuppliers()).thenReturn(suppliers);

        when(supplierMapper.findSupplierById(1)).thenReturn(supplier1);
        when(supplierMapper.findSupplierById(2)).thenReturn(supplier2);

        List<Supplier> supplierByName1 = new ArrayList<>();
        supplierByName1.add(supplier1);
        when(supplierMapper.findSupplierByName("DefaultCompany1")).thenReturn(supplierByName1);
        List<Supplier> supplierByName2 = new ArrayList<>();
        supplierByName2.add(supplier2);
        when(supplierMapper.findSupplierByName("DefaultCompany2")).thenReturn(supplierByName2);

        when(supplierMapper.addSupplier(supplier1)).thenReturn(1);
        when(supplierMapper.addSupplier(supplier2)).thenReturn(2);

        when(supplierMapper.updateSupplier(supplier1)).thenReturn(1);
        when(supplierMapper.updateSupplier(supplier2)).thenReturn(2);

        when(supplierMapper.deleteSupplier(1)).thenReturn(1);
        when(supplierMapper.deleteSupplier(2)).thenReturn(2);

        GoodsOnOffer goods1 = mock(GoodsOnOffer.class);
        when(goods1.getId()).thenReturn(1);
        GoodsOnOffer goods2 = mock(GoodsOnOffer.class);
        when(goods2.getId()).thenReturn(2);

        List<GoodsOnOffer> goodsList = new ArrayList<>();
        goodsList.add(goods1);
        goodsList.add(goods2);

        when(supplierMapper.getGoodsOnOffer(1)).thenReturn(null);
        when(supplierMapper.getGoodsOnOffer(2)).thenReturn(goodsList);

        supplierService = new SupplierServiceImpl(supplierMapper);
    }

    @Test
    public void findAllSupplierTest() {
        assertEquals(2, supplierService.findAllSuppliers().size());
    }

    @Test
    public void findSupplierByNameTest() {
        assertEquals(1, supplierService.findSupplierByName("DefaultCompany1").size());
        assertEquals(1, supplierService.findSupplierByName("DefaultCompany2").size());
    }

    @Test
    public void findSupplierByIdTest() {
        assertEquals(1, supplierService.findSupplierById(1).getId());
        assertEquals(2, supplierService.findSupplierById(2).getId());
    }

    @Test
    public void addSupplierTest() {
        assertEquals(1, supplierService.addSupplier(supplier1));
        assertEquals(2, supplierService.addSupplier(supplier2));
    }

    @Test
    public void updateSupplierTest() {
        assertEquals(1, supplierService.updateSupplier(supplier1));
        assertEquals(2, supplierService.updateSupplier(supplier2));
    }

    @Test
    public void deleteSupplierTest() {
        assertEquals(1, supplierService.deleteSupplier(1));
        assertEquals(2, supplierService.deleteSupplier(2));
    }
}
