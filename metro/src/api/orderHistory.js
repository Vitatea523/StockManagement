import request from '@/utils/request'

export default {
  orderHistory() {
    return request({
      url: '/api/order',
      method: 'get'
    })
  },
  deleteOrder(order) {
    return request({
      url: '/api/order/delete',
      method: 'delete',
      data: order
    })
  },
  confirmOrder(oid, expireDate) {
    return request({
      url: '/api/order/confirm',
      method: 'put',
      params: {
        oid,
        expireDate
      }
    })
  },
  // send order email
  email(email_info) {
    return request({
      url: `/api/order/email`,
      method: 'post',
      data: email_info
    })
  }
}
