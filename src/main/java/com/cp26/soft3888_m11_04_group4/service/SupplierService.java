package com.cp26.soft3888_m11_04_group4.service;

import com.cp26.soft3888_m11_04_group4.pojo.GoodSupplier;
import com.cp26.soft3888_m11_04_group4.pojo.Goods;
import com.cp26.soft3888_m11_04_group4.pojo.GoodsOnOffer;
import com.cp26.soft3888_m11_04_group4.pojo.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yuyun Liu
 */
public interface SupplierService {

    /**
     * The function to list all suppliers' information.
     * @return
     */
    List<Supplier> findAllSuppliers();

    /**
     * The function to find a supplier according to business name.
     * @param businessName
     * @return
     */
    List<Supplier> findSupplierByName(@Param("businessName") String businessName);

    /**
     * The function to find a supplier according to id.
     * @param id
     * @return
     */
    Supplier findSupplierById(@Param("id") int id);

    /**
     * The function to find a supplier according to goods id.
     * @param goodsId
     * @return
     */
    List<GoodSupplier> findSupplierByGoodsId(@Param("goodsId") int goodsId);

    /**
     * The function to add a supplier.
     * @param supplier
     * @return
     */
    int addSupplier(Supplier supplier);

    /**
     * The function to update information of a supplier.
     * @param supplier
     * @return
     */
    int updateSupplier(Supplier supplier);

    /**
     * The function to delete a supplier from the system.
     * @param id
     * @return
     */
    int deleteSupplier(int id);

    /**
     * The function to get all goods that the supplier can offer.
     * @param id
     * @return
     */
    List<GoodsOnOffer> getGoodsOnOffer(int id);

}
