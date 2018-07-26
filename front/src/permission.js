import router from '@/router/routes'
// import store from '@/store'
// import { getToken } from '@/utils/auth'
/* router.beforeEach((to, from, next) => {
  console.log('requireAuth', to, from)
  if (to.name === 'login') return next()
  if (window.isAuthenticated) return next()
  next({
    path: '/login',
    query: { redirect: to.fullPath }
  })
}) */
/* function hasPermission (roles, permissionRoles) {
  if (roles.indexOf('admin') >= 0) return true
  if (!permissionRoles) return true
  return roles.some(role => permissionRoles.indexOf(role) >= 0)
} */

// const whiteList = ['/login']
// TODO: 로그인 페이지 숨김
// const whiteList = ['/']
router.beforeEach((to, from, next) => {
  // next({ path: '/' })
  next()
  /* if (getToken()) {
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      if (store.getters.roles.length === 0) {
        store.dispatch('getUserInfo').then(res => {
          const roles = res.data.data.roles
          store.dispatch('generateRoutes', { roles }).then(() => {
            router.addRoutes(store.getters.addRouters)
            next({ ...to, replace: true })
          })
        }).catch(() => {
          store.dispatch('fedLogOut').then(() => {
            alert('인증에 실패했습니다. 다시 로그인해주세요')
            next({ path: '/login' })
          })
        })
      } else {
        if (hasPermission(store.getters.roles, to.meta.roles)) {
          next()
        } else {
          next({path: '/401', replace: true, query: { noGoBack: true }})
        }
      }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next('/login')
    }
  } */
})

router.afterEach(() => {
})
