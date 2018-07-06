import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const baseRoutes = [
  { path: '/login', name: 'Login', component: () => import('@/site/Login') }
]

const routes = [
  {
    path: '',
    name: '운영',
    iconClass: 'view_list',
    component: () => import('@/site/Main'),
    position: 'top',
    children: [
      { path: '/',
        name: '자원관리',
        component: () => import('@/site/resource/ResourceInfra')
      },
      { path: '',
        name: '작업관리'
      }
    ]
  },
  {
    path: '',
    name: '대시보드',
    iconClass: 'desktop_windows',
    position: 'top',
    children: [
      { path: '',
        name: 'Grafana'
      },
      { path: '',
        name: 'Custom'
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
