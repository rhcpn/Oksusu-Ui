<template>
  <div id="app">
    <router-view/>

    <!-- Common Components -->
    <common-spinner ref="loader"></common-spinner>
    <interceptors-handler />
  </div>
</template>

<script>
import commonSpinner from '@/common/component/common-spinner'
import httpInterceptors from '@/common/component/http-interceptors'

export default {
  name: 'App',
  components: {
    'common-spinner': commonSpinner,
    'interceptors-handler': httpInterceptors
  }
}
</script>

<style>
  /* @import url(https://fonts.googleapis.com/icon?family=Material+Icons); */
</style>
