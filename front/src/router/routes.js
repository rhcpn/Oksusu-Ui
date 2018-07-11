import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const BASE_ROUTES = [
  { path: '', name: 'Main', component: () => import('@/site/Main') },
  { path: '/login', name: 'Login', component: () => import('@/site/Login') },
  {
    path: '*',
    name: '404',
    component: { template: '<div><v-layout justify-center align-center><h2>Page Not Found</h2></v-layout></div>' }
  }
]

const ROUTES = [
  {
    path: '/',
    name: 'Inventory',
    iconClass: 'view_list',
    position: 'top',
    component: () => import('@/site/resource/ResourceInfra')
  },
  {
    path: '',
    name: '작업관리',
    iconClass: 'view_list',
    position: 'top',
    component: { template: '<router-view/>' }
  },
  {
    path: '',
    name: '대시보드',
    iconClass: 'desktop_windows',
    position: 'top',
    component: { template: '<div>dashboard<router-view/></div>' },
    children: [
      { path: '/dashboard/grafana',
        name: 'Grafana',
        component: { template: '<div>grafana</div>' }
      },
      { path: '/dashboard/custom',
        name: 'Custom',
        component: { template: '<div>custom</div>' }
      }
    ]
  },
  {
    path: '',
    name: '데이터분석',
    iconClass: 'pie_chart',
    position: 'top'
  },
  {
    path: '',
    name: '관리자',
    iconClass: 'people',
    position: 'top',
    children: [
      { path: '',
        name: '사용자 관리'
      },
      { path: '',
        name: '시스템 모니터링'
      }
    ]
  },
  {
    path: '',
    name: '설정',
    iconClass: 'settings',
    position: 'bottom',
    children: [
      { path: '',
        name: '자원 관리',
        children: [
          { path: '',
            name: 'Infra'
          },
          { path: '',
            name: 'Service'
          }
        ]
      },
      { path: '',
        name: '알람 정의'
      },
      { path: '',
        name: '알람 공지 설정'
      },
      { path: '',
        name: '수집 설정'
      },
      { path: '',
        name: '메트릭 설정'
      },
      { path: '',
        name: '데이터 센터 설정'
      }
    ]
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

router.beforeEach((to, from, next) => {
  console.log('requireAuth', to, from)
  if (to.name === 'Login') return next()
  if (window.isAuthenticated) return next()
  next({
    path: '/login',
    query: { redirect: to.fullPath }
  })
})

export default router
