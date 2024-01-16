import request from '@/utils/request'

export default {
  displayStockLevelReport() {
    return request({
      url: '/api/goods/displayStockLevelReport',
      method: 'get'
    })
  },
  generateStockLevelReport() {
    return request({
      url: '/api/goods/generateStockLevelReport',
      method: 'get'
    })
  }
}
