import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const baseRoutes = [
  { path: '/login', name: 'Login', component: () => import('@/site/Login') }
]

const routes = [
  { path: '/', name: 'Resource-Infra', component: () => import('@/site/resource/ResourceInfra') }
]

const MenuData = require('@/asset/json/menu')

function initRouter () {
  let router = addRoute(routes)
  router = router.concat(baseRoutes)
  return router
}

function addRoute (routes) {
  let list = []
  for (let route of routes) {
    for (let menu of MenuData) {
      if (route.name === menu.name && menu.auth === true) {
        list.push(route)
        if (route.children) {
          let childrenRoutes = route.children
          delete route.children
          let childrenList = addRoute(childrenRoutes)
          if (childrenList.length > 0) {
            route.children = childrenList
          }
        }
      }
    }
  }
  return list
}

const router = new VueRouter({
  routes: initRouter()
})

router.beforeEach((to, from, next) => {
  // console.log('requireAuth', to, from)
  if (to.name === 'Login') return next()
  if (window.isAuthenticated) return next()
  next({
    path: '/login',
    query: { redirect: to.fullPath }
  })
})

export default router
