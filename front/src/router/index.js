import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'A',
      component: () => import('@/site/modulea/A')
    },
    {
      path: '/B',
      name: 'B',
      component: () => import('@/site/moduleb/B')
    },
    {
      path: '/axios-sample',
      name: 'axios-sample',
      component: () => import('@/common/sample/axios-sample')
    }
  ]
})
