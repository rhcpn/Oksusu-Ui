<template>
  <div id="app">
    <img src="@/asset/logo.png">
    <nav>
      <router-link :to="{ name: 'A' }">Module A</router-link>
      <router-link :to="{ name: 'B' }">Module B</router-link>
      <router-link :to="{ name: 'C' }">Module C</router-link>
      <router-link :to="{ name: 'D' }">Module D</router-link>
      <router-link :to="{ name: 'F' }">Module F</router-link>
      <router-link :to="{ name: 'F1' }">Module F1</router-link>
      <router-link to="/F/F2">Module F2</router-link>
      <router-link to="/axios-sample">Axios Sample Page</router-link>
    </nav>
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

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
nav {
  padding: 20px 20px 20px 0;
}

nav a {
  padding: 10px;
  text-decoration: none;
  background: #fff;
  border-radius: 3px;
  color: rgb(0, 110, 255);
  font-weight: bold;
  margin-right: 15px;
}
</style>
