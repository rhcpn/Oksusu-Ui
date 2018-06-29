<template>
  <div id="app" class="wrapper application--wrap">

    <!-- GNB -->
    <gnb-component></gnb-component>

    <!-- Header-->
    <header-component :menuFolding="false"></header-component>

    <!-- Content -->
    <div class="content">
      <div class="content--wrap">
        <div class="container fluid fill-height">
          <router-view/>
        </div>
      </div>
    </div>

    <!-- Common Components -->
    <common-spinner ref="loader"></common-spinner>
    <interceptors-handler />
  </div>
</template>

<script>
import Vue from 'vue'
import headerComponent from '@/common/component/header/header-component'
import gnbComponent from '@/common/component/menu/gnb-component'
import commonSpinner from '@/common/component/common-spinner'
import httpInterceptors from '@/common/component/http-interceptors'

Vue.component('header-component', headerComponent)
Vue.component('gnb-component', gnbComponent)
Vue.component('common-spinner', commonSpinner)
Vue.component('interceptors-handler', httpInterceptors)

export default {
  name: 'App'
}
</script>

<style>
  @import url(https://fonts.googleapis.com/icon?family=Material+Icons);
</style>
