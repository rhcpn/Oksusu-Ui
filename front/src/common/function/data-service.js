import axios from 'axios'

const methodList = ['request', 'get', 'delete', 'head', 'options', 'post', 'put', 'patch']

function execute (method, url, data, headers, successHandler, errorHandler) {
  return axios({
    method: method,
    url: url,
    data: JSON.stringify(data),
    headers: headers
  }).then(function (response) {
    console.log(response)
    if (successHandler) {
      successHandler(response.data)
    }
  }).catch(function (error) {
    console.log(error)
    if (errorHandler) {
      errorHandler(error)
    }
  })
}

export default {
  methodList: methodList,
  httpGet: function (url, data, successHandler, errorHandler, headers = null) {
    if (url && url.length > 0) {
      return execute('get', url, data, headers, successHandler, errorHandler)
    }
  },
  httpPost: function (url, data, successHandler, errorHandler, headers = null) {
    if (url && url.length > 0) {
      return execute('post', url, data, headers, successHandler, errorHandler)
    }
  },
  httpPut: function (url, data, successHandler, errorHandler, headers = null) {
    if (url && url.length > 0) {
      return execute('put', url, data, headers, successHandler, errorHandler)
    }
  },
  httpDelete: function (url, data, successHandler, errorHandler, headers = null) {
    if (url && url.length > 0) {
      return execute('delete', url, data, headers, successHandler, errorHandler)
    }
  }
}
