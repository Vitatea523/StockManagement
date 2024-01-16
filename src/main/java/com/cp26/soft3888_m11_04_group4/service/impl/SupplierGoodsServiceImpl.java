package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.SupplierGoodsMapper;
import com.cp26.soft3888_m11_04_group4.pojo.SupplierGoods;
import com.cp26.soft3888_m11_04_group4.service.SupplierGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Yuyun Liu
 */
@Service
public class SupplierGoodsServiceImpl implements SupplierGoodsService {
    @Autowired
    private SupplierGoodsMapper supplierGoodsMapper;

    public SupplierGoodsServiceImpl(SupplierGoodsMapper supplierGoodsMapper) {
        this.supplierGoodsMapper = supplierGoodsMapper;
    }

    /**
     * The function to find the cost per carton value of a goods from a supplier.
     * @param supplierId
     * @param goodsId
     * @return
     */
    @Override
    public BigDecimal findCostPerCarton(Integer supplierId, Integer goodsId) {
        return supplierGoodsMapper.findCostPerCarton(supplierId, goodsId);
    }

    /**
     * The function to find the cost per unit value of a goods from a supplier.
     * @param supplierId
     * @param goodsId
     * @return
     */
    @Override
    public BigDecimal findCostPerUnit(Integer supplierId, Integer goodsId) {
        return supplierGoodsMapper.findCostPerUnit(supplierId, goodsId);
    }

    /**
     * The function to find information about supplier offers a goods.
     * @param supplierId
     * @param goodsId
     * @return
     */
    @Override
    public SupplierGoods querySupplierGoodsById(Integer supplierId, Integer goodsId) {
        return supplierGoodsMapper.querySupplierGoodsById(supplierId, goodsId);
    }

    /**
     * The function to add a record of supplier who can offer the goods.
     * @param supplierGoods
     * @return
     */
    @Override
    public int addSupplyGoods(SupplierGoods supplierGoods) {
        return supplierGoodsMapper.addSupplierGoods(supplierGoods);
    }

    /**
     * The function to delete a record of supplier who can offer the goods.
     * @param supplierId
     * @param goodsId
     * @return
     */
    @Override
    public int deleteSupplyGoods(Integer supplierId, Integer goodsId) {
        return supplierGoodsMapper.deleteSupplierGoods(supplierId, goodsId);
    }
}
