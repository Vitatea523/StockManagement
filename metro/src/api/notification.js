import request from '@/utils/request'

export default {
  getPopUpNotifications(token) {
    return request({
      url: '/api/notifications/popUp',
      method: 'get',
      params:{ token }
    })
  },
  getAllNotifications(token) {
    // axios实例
    return request({
      url: '/api/notifications/all',
      method: 'get',
      params: { token }
    })
  },
  makeNotificationSettings(data) {
    return request({
      url: '/api/notifications/setting',
      method: 'put',
      data: data
    })
  },
  deleteNotification(goodsId, type) {
    return request({
      url: '/api/notifications/delete',
      method: 'delete',
      params: { goodsId, type }
    })
  }
}
