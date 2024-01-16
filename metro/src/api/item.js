import request from '@/utils/request'

export default {
  listItem() {
    // axios实例
    return request({
      url: '/api/goods/listGoods',
      method: 'get'
    })
  },

  deleteById(id) {
    return request({
      // ${id}
      url: '/api/goods/delete',
      method: 'delete',
      params: { id }
    })
  },
  fetchDataBySid(id) {
    return request({
      // ${sid}
      url: '/api/goods/id',
      method: 'get',
      params: { id }
    })
  },

  editItem(edit_data) {
    return request({
      url: `/api/goods/edit`,
      method: 'post',
      data: edit_data
    })
  },
  addItem(add_data) {
    return request({
      url: `/api/goods/add`,
      method: 'post',
      data: add_data
    })
  },
  suppliergood(id) {
    return request({
      url: '/api/supplier/goodsId',
      method: 'get',
      params: { id }
    })
  },
  fetchlist(id) {
    return request({
      url: '/api/inventory/list/goodsId',
      method: 'get',
      params: { id }
    })
  },
  slowItem() {
    // axios实例
    return request({
      url: '/api/goods/slow',
      method: 'get'
    })
  },
  fastItem() {
    // axios实例
    return request({
      url: '/api/goods/fast',
      method: 'get'
    })
  },
  mostItem() {
    // axios实例
    return request({
      url: '/api/goods/highProfitMargin',
      method: 'get'
    })
  },


}

