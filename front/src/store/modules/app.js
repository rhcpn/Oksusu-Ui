import Cookies from 'js-cookie'
const app = {
  state: {
    language: Cookies.get('language') || 'kr' // this.$store.getters.language
  },
  mutations: {
    SET_LANGUAGE: (state, language) => {
      state.language = language
    }
  },
  actions: {
    setLanguage ({ commit }, language) { // this.$store.dispatch('setLanguage', lang)
      commit('SET_LANGUAGE', language)
    }
  }
}
export default app
