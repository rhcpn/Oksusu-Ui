import Vue from 'vue'
import App from '@/site/App'
import router from '@/router/routes'
import axios from 'axios'
import VueI18n from 'vue-i18n'
import Vuetify from 'vuetify'
import messages from '@/common/component/messages-handler'
import ConfigMessages from '@/resource/config/messages'

import 'vuetify/dist/vuetify.min.css'
import '@/asset/css/common/reset.css'
import '@/asset/css/common/common.css'

// 다국어 설정
Vue.use(VueI18n)
const i18n = new VueI18n({
  locale: 'ko',
  messages
})

Vue.use(Vuetify)

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$message = ConfigMessages

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  components: { App },
  template: '<App/>',
  methods: {
    changeLocaleLanguage (lang) {
      this.$i18n.locale = lang
    }
  }
})
