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
          <slider-component></slider-component>
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
import sliderComponent from '@/common/component/slider/slider-component'

Vue.component('header-component', headerComponent)
Vue.component('gnb-component', gnbComponent)
Vue.component('common-spinner', commonSpinner)
Vue.component('interceptors-handler', httpInterceptors)
Vue.component('slider-component', sliderComponent)

export default {
  name: 'Main'
}
</script>

<style scoped>

</style>
