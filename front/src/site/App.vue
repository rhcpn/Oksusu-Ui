<template>
  <div id="app">
    <router-view/>
    <common-spinner ref="loader"></common-spinner>
    <interceptors-handler />
  </div>
</template>

<script>
import Vue from 'vue'
import commonSpinner from '@/common/component/common-spinner'
import httpInterceptors from '@/common/component/http-interceptors'

Vue.component('common-spinner', commonSpinner)
Vue.component('interceptors-handler', httpInterceptors)

export default {
  name: 'App'
}
</script>
