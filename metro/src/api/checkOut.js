import request from '@/utils/request'

export default {
  addCheckOutItem() {
    // axios实例
    return request({
      url: '/api/checkOut/manualItem',
      method: 'get'
    })
  },
  checkOut(data) {
    return request({
      url: '/api/checkOut/checkOutItems',
      method: 'post',
      data: data
    })
  }
}
