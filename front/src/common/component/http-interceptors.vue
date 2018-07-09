<script>
import axios from 'axios'
import commonSpinner from './common-spinner'

var _loader
export default {
  name: 'interceptorsHandler',
  components: {
    'common-spinner': commonSpinner
  },
  template: ' ',
  inherit: true,
  created: function () {
    // ajax request Handler
    axios.interceptors.request.use(this.requestSuccessHandler, this.requestErrorHandler)
    // ajax response Handler
    axios.interceptors.response.use(this.responseSuccessHandler, this.responseErrorHandler)
    // loader setting
    _loader = this.$parent.$refs.loader
  },
  methods: {
    requestSuccessHandler: function (config) {
      var loader = true
      if (config && config.data) {
        var p
        try {
          p = JSON.parse(config.data)
        } catch (e) {
          p = config.data
        }

        if (p != null && typeof p === 'object' && p.loader === false) {
          loader = false
          delete p.loader

          if (Object.keys(p).length === 0) {
            p = null
          }

          config.data = JSON.stringify(p)
        }
      }

      if (loader) {
        // _loader.showIndicator()
        _loader.lazyIndicator(1000000)
      }

      return config || Promise.when(config)
    },

    requestErrorHandler: function (err) {
      _loader.lazyIndicator()
      return Promise.reject(err)
    },

    responseSuccessHandler: function (config) {
      _loader.lazyIndicator()

      var contentType = config.headers['content-type']
      if (contentType && (contentType.indexOf('application/json') > -1 || contentType.indexOf('application/x-www-form-urlencoded') > -1)) {
        var data = this.isSuccess(config.data)
        if (!data) {
          return Promise.reject(config)
        }
      }

      return config || Promise.when(config)
    },

    responseErrorHandler: function (err) {
      _loader.lazyIndicator()

      this.ajaxErrorHandler(err)
      return Promise.reject(err)
    },

    isSuccess: function (response) {
      if (response == null || response === '' || response === 'undefined') {
        return false
      }

      if (response.hasOwnProperty('result') === false) {
        return true
      }

      if (response.result === 0) {
        if (response.errorMessage == null || response.errorMessage === '') {
          alert('Unknown Error.')
        } else {
          alert(response.errorMessage)
        }

        return false
      }

      return true
    },

    ajaxErrorHandler: function (res) {
      if (res.status === '200' || res.statusText === 'success') {
        return
      }

      var message = ''
      var statusErrorMap = {
        '400': '400 Bad Request.',
        '401': '401 Unauthorized.',
        '403': '403 Forbidden.',
        '404': '404 Not Found.',
        '500': '500 Internal Server Error.',
        '503': '503 Service Unavailable.',
        '901': '901 Restart'
      }

      if (res.status) {
        message = statusErrorMap[res.status]
      } else {
        message = 'Unknown Error.'
      }

      alert(message)

      if (res.status === '901') {
        window.location = '/'
      }
    }
  }
}
</script>
