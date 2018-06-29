import axios from 'axios'

export default class DataService {
  constructor () {
    this.methodList = ['request', 'get', 'delete', 'head', 'options', 'post', 'put', 'patch']
  }

  execute (method, url, data, headers, successHandler, errorHandler) {
    return axios({
      method: method,
      url: url,
      data: JSON.stringify(data),
      headers: headers
    }).then(function (response) {
      if (successHandler) {
        successHandler(response.data)
      }
    }).catch(function (error) {
      if (errorHandler) {
        errorHandler(error)
      }
    })
  }

  httpGet (url, data, successHandler, errorHandler, headers = null) {
    if (url && url.length > 0) {
      return this.execute('get', url, data, headers, successHandler, errorHandler)
    }
  }
  httpPost (url, data, successHandler, errorHandler, headers = null) {
    if (url && url.length > 0) {
      return this.execute('post', url, data, headers, successHandler, errorHandler)
    }
  }
  httpPut (url, data, successHandler, errorHandler, headers = null) {
    if (url && url.length > 0) {
      return this.execute('put', url, data, headers, successHandler, errorHandler)
    }
  }
  httpDelete (url, data, successHandler, errorHandler, headers = null) {
    if (url && url.length > 0) {
      return this.execute('delete', url, data, headers, successHandler, errorHandler)
    }
  }
}
