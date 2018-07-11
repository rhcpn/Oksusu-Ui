import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import resourceInfra from './modules/resourceInfra'
import permission from './modules/permission'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    resourceInfra,
    user,
    permission
  },
  getters
})

export default store
