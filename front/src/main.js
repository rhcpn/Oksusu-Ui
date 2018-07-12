import Vue from 'vue'
import App from '@/site/App'
import router from '@/router/routes'
import store from './store'
import axios from 'axios'
import VueI18n from 'vue-i18n'
import Vuetify from 'vuetify'
import messages from '@/common/component/messages-handler'
import ConfigMessages from '@/resource/config/messages'

import './permission'

import 'vuetify/dist/vuetify.min.css'
import '@/asset/css/common/reset.css'
import '@/asset/css/common/common.css'

import '../node_modules/ag-grid/dist/styles/ag-grid.css'
import '../node_modules/ag-grid/dist/styles/ag-theme-balham.css'

// 다국어 설정
Vue.use(VueI18n)
const i18n = new VueI18n({
  locale: 'ko',
  messages
})

// 공용 EventHub 선언
const eventHub = new Vue({
  created () {
    // 현재는 사용하지 않은 Event, 예를 들어 alarm-occurred와 같은 전역 Event일 경우 여기에 구현
    this.$on('alarm-occured', this.alarmOccurredHandler)
  },
  methods: {
    alarmOccurredHandler ($event) {
      console.log('alarmOccurredHandler', $event)
    }
  }
})
Object.defineProperties(Vue.prototype, {
  $eventHub: {
    get: function () {
      return eventHub
    }
  }
})

Vue.use(Vuetify)

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$message = ConfigMessages

var isAuthenticated = false
window.isAuthenticated = isAuthenticated

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  i18n,
  components: { App },
  template: '<App/>',
  data: {
    isAuthenticated
  },
  methods: {
    changeLocaleLanguage (lang) {
      this.$i18n.locale = lang
    }
  }
})
