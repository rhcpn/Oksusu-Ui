import request from '@/utils/request'
export function getNavigation () {
  return request({
    url: '/resource/infra/navigation.json',
    method: 'get'
  })
}
