package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import com.cp26.soft3888_m11_04_group4.pojo.SupplierGoods;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
 * @author Yuyun Liu
 */
@Mapper
public interface SupplierGoodsMapper {
    /**
     * The function to find the cost per carton value of a goods from a supplier.
     * @param supplierId
     * @param goodsId
     * @return
     */
    BigDecimal findCostPerCarton (Integer supplierId, Integer goodsId);

    /**
     * The function to find the cost per unit value of a goods from a supplier.
     * @param supplierId
     * @param goodsId
     * @return
     */
    BigDecimal findCostPerUnit (Integer supplierId, Integer goodsId);

    /**
     * The function to find information about supplier offers a goods.
     * @param supplierId
     * @param goodsId
     * @return
     */
    SupplierGoods querySupplierGoodsById(Integer supplierId, Integer goodsId);

    /**
     * The function to add a record of supplier who can offer the goods.
     * @param supplierGoods
     * @return
     */
    int addSupplierGoods(SupplierGoods supplierGoods);

    /**
     * The function to delete a record of supplier who can offer the goods.
     * @param supplierId
     * @param goodsId
     * @return
     */
    int deleteSupplierGoods(Integer supplierId, Integer goodsId);
}
