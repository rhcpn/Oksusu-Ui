import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const PageD = { template: '<div>This is D Page</div>' }
const PageE = { template: '<div>This is E Page</div>' }

const baseRoutes = [
  { path: '/login', name: 'Login', component: () => import('@/site/Login') }
]

const routes = [
  { path: '/', name: 'Resource-Infra', component: () => import('@/site/resource/ResourceInfra') },
  { path: '/B', name: 'B', component: () => import('@/site/moduleb/B') },
  { path: '/C', name: 'C', component: { template: '<div>This is C Page</div>' } },
  { path: '/D', name: 'D', component: PageD },
  { path: '/E', name: 'E', component: PageE },
  {
    path: '/F',
    name: 'F',
    component: { template: '<div><h2>This is F Page</h2><router-view></router-view></div>' },
    children: [
      {
        path: 'F1',
        name: 'F1',
        component: { template: '<div>This is F1 Page<router-view></router-view></div>' },
        children: [
          { path: 'F11', name: 'F11', component: { template: '<div>This is F11 Page</div>' } },
          { path: 'F12', name: 'F12', component: { template: '<div>This is F12 Page</div>' } }
        ]
      },
      { path: 'F2', name: 'F2', component: { template: '<div>This is F2 Page</div>' } }
    ]
  },
  {
    path: '/axios-sample',
    name: 'axios-sample',
    component: () => import('@/common/sample/axios-sample')
  }
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
