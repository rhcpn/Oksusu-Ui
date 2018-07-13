import request from '@/utils/request'
export function getUserInfo (token) {
  return request({
    url: '/login/getUserInfo.json',
    method: 'get',
    params: { token }
  })
}
export function loginByUsername (name, password) {
  return request({
    url: '/login/loginByUsername.json',
    method: 'get',
    params: { name }
  })
}
export function logout () {
  return request({
    url: '/login/logout.json',
    method: 'get'
  })
}
