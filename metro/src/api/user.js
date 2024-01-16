import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/user/login',
    method: 'post',
    data: data
  })
}

export function getInfo(token) {
  return request({
    url: '/api/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/api/user/logout',
    method: 'post'
  })
}

export function createAccount(data) {
  return request({
    url: '/api/user/createAccount',
    method: 'post',
    data: data
  })
}

export function getAccounts() {
  return request({
    url: '/api/user/getAccounts',
    method: 'get'
  })
}

export function deleteAccount(id) {
  return request({
    url: '/api/user/deleteAccount',
    method: 'delete',
    params: { id }
  })
}

export function resetPassword(id, password) {
  return request({
    url: '/api/user/resetPassword',
    method: 'post',
    params: { id, password }
  })
}
