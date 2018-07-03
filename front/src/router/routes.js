import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const baseRoutes = [
  { path: '/login', name: 'Login', component: () => import('@/site/Login') }
]

const routes = [
  {
    path: '',
    name: 'Main',
    component: () => import('@/site/Main'),
    position: 'top',
    children: [
      { path: '/', name: 'Resource-Infra', component: () => import('@/site/resource/ResourceInfra') }
    ]
  },
  { path: '',
    name: 'Setting',
    position: 'bottom',
    component: { template: '<div>This is Setting Page</div>' },
    children: [
      { path: '/a', name: '자원관리', position: 'bottom', component: { template: '<div>This is 자원관리 Page</div>' } },
      { path: '/b', name: '알람정의', position: 'bottom', component: { template: '<div>This is 알람정의 Page</div>' } },
      { path: '/c', name: '알람공지설정', position: 'bottom', component: { template: '<div>This is 알람공지설정 Page</div>' } },
      { path: '/d', name: '수집설정', position: 'bottom', component: { template: '<div>This is 수집설정 Page</div>' } },
      { path: '/e', name: '메트릭설정', position: 'bottom', component: { template: '<div>This is 메트릭설정 Page</div>' } },
      { path: '/f', name: '데이터센터설정', position: 'bottom', component: { template: '<div>This is 데이터센터설정 Page</div>' } }
    ]
  },
  { path: '',
    name: '도움말',
    position: 'bottom',
    component: { template: '<div>This is 도움말 Page</div>' },
    children: [
      { path: '/g', name: 'How to & Tutorials', position: 'bottom', component: { template: '<div>This is How to & Tutorials Page</div>' } },
      { path: '/h', name: 'T-CORE 버전정보', position: 'bottom', component: { template: '<div>This is T-CORE 버전정보 Page</div>' } }
    ]
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
