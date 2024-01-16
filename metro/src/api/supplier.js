import request from '@/utils/request'

export default {
  // list all suppliers
  listSupplier() {
    return request({
      url: '/api/supplier/list',
      method: 'get'
    })
  },
  // delete one supplier
  deleteById(id) {
    return request({
      // ${id}
      url: `/api/supplier/delete` ,
      params: { id },
      method: 'delete'
    })
  },
  fetchDataBySid(id) {
    return request({
      // ${sid}
      url: `/api/supplier/id`,
      params: { id },
      method: 'get'
    })
  },
  // edit supplier
  editSupplier(edit_data) {
    return request({
      url: `/api/supplier/edit`,
      method: 'put',
      data: edit_data
    })
  },

  // add supplier
  addSupplier(add_data) {
    return request({
      url: `/api/supplier/add`,
      method: 'post',
      data: add_data
    })
  },
  // add good to supplier
  addSupplyGoods(add_good) {
    return request({
      url: `/api/supplier/supply`,
      method: 'post',
      data: add_good
    })
  },
  // single supplier's goods on offer
  goodsOnOffer(id) {
    return request({
      url: `/api/supplier/offer`,
      params: { id },
      method: 'get'
    })
  }

}

