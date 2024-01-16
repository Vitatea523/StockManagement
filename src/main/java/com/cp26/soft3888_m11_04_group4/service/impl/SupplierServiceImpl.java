package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.SupplierMapper;
import com.cp26.soft3888_m11_04_group4.pojo.GoodSupplier;
import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.GoodsOnOffer;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import com.cp26.soft3888_m11_04_group4.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyun Liu
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierMapper supplierMapper) {
        this.supplierMapper = supplierMapper;
    }

    /**
     * The function to list all suppliers' information.
     * @return
     */
    @Override
    public List<Supplier> findAllSuppliers() {
        return supplierMapper.findAllSuppliers();
    }

    /**
     * The function to find a supplier according to business name.
     * @param businessName
     * @return
     */
    @Override
    public List<Supplier> findSupplierByName(String businessName) {
        return supplierMapper.findSupplierByName(businessName);
    }

    /**
     * The function to find a supplier according to id.
     * @param id
     * @return
     */
    @Override
    public Supplier findSupplierById(int id) {
        return supplierMapper.findSupplierById(id);
    }

    /**
     * The function to find a supplier according to goods id.
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodSupplier> findSupplierByGoodsId(int goodsId) {
        return supplierMapper.findSupplierByGoodsId(goodsId);
    }

    /**
     * The function to add a supplier.
     * @param supplier
     * @return
     */
    @Override
    public int addSupplier(Supplier supplier) {
        return supplierMapper.addSupplier(supplier);
    }

    /**
     * The function to update information of a supplier.
     * @param supplier
     * @return
     */
    @Override
    public int updateSupplier(Supplier supplier) {
        return supplierMapper.updateSupplier(supplier);
    }

    /**
     * The function to delete a supplier from the system.
     * @param id
     * @return
     */
    @Override
    public int deleteSupplier(int id) {
        return supplierMapper.deleteSupplier(id);
    }

    /**
     * The function to get all goods that the supplier can offer.
     * @param id
     * @return
     */
    @Override
    public List<GoodsOnOffer> getGoodsOnOffer(int id) {
        return supplierMapper.getGoodsOnOffer(id);
    }
}
