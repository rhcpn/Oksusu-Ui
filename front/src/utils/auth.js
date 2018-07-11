import Cookies from 'js-cookie'

const TokenKey = 'Tcore-Admin-Token'

export function getToken () {
  console.log(Cookies.get(TokenKey))
  return Cookies.get(TokenKey)
}

export function setToken (token) {
  console.log(Cookies.get(TokenKey))
  return Cookies.set(TokenKey, token)
}

export function removeToken () {
  console.log(Cookies.get(TokenKey))
  return Cookies.remove(TokenKey)
}
