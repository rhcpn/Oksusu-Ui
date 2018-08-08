import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

export const BASE_ROUTES = [
  { path: '', name: 'Main', component: () => import('@/site/Main') }/* ,
  { path: '/login', name: 'Login', component: () => import('@/site/Login') },
  {
    path: '*',
    name: '404',
    component: { template: '<div><v-layout justify-center align-center><h2>Page Not Found</h2></v-layout></div>' }
  },
  {
    path: '/401',
    name: '401',
    component: { template: '<div><v-layout justify-center align-center><h2>Access denied</h2></v-layout></div>' }
  } */
]

export const ROUTES = [
  {
    path: '/',
    name: 'oksusuVideo',
    position: 'top',
    component: () => import('@/site/oksusu/VideoMain')
  }
]

const MenuData = require('@/asset/json/menu')

function initRouter () {
  let router = addChildren(addRoute(ROUTES))
  console.log(router)
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

function addChildren (children) {
  let router = []
  for (let route of BASE_ROUTES) {
    if (route.name === 'Main') {
      route.children = children
    }
    router.push(route)
  }
  return router
}

const router = new VueRouter({
  routes: initRouter()
})

export default router
