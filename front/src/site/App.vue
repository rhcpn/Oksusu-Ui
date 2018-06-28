<template>
  <div id="app" class="wrapper application--wrap">

    <!-- GNB -->
    <gnb-component :folding="menuFolding"></gnb-component>

    <!-- Header(TODO: component 로 분리) -->
    <div class="fixed-top-cont">
      <div class="top-left">
        <v-toolbar-side-icon id="foldingBtn" @click.stop="menuFoldingHandler()"></v-toolbar-side-icon>
      </div>
      <div class="top-right">
        <ul class="util-menu">
          <li>
            <v-badge left>
              <span slot="badge" class="bg-red">62</span>
              <v-icon color="white" class="md-18">notifications</v-icon>
            </v-badge>
          </li>
          <li>
            <v-menu offset-y>
              <v-btn flat small slot="activator"><v-icon left color="white" class="md-18">person</v-icon>User info<v-icon right color="white" class="md-18">expand_more</v-icon></v-btn>
              <!--<v-list>-->
                <!--<v-list-tile v-for="(item, index) in items" :key="index" @click="">-->
                  <!--<v-list-tile-title>{{ item.title }}</v-list-tile-title>-->
                <!--</v-list-tile>-->
              <!--</v-list>-->
            </v-menu>
          </li>
          <li>
            <v-btn flat class="btn-util-logout"><v-icon color="white">power_settings_new</v-icon></v-btn>
          </li>
        </ul>
      </div>
    </div>

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
import gnbComponent from '@/common/component/menu/gnb-component'
import commonSpinner from '@/common/component/common-spinner'
import httpInterceptors from '@/common/component/http-interceptors'

Vue.component('gnb-component', gnbComponent)
Vue.component('common-spinner', commonSpinner)
Vue.component('interceptors-handler', httpInterceptors)

export default {
  name: 'App',
  data () {
    return {
      menuFolding: false
    }
  },
  methods: {
    menuFoldingHandler: function () {
      this.menuFolding = !this.menuFolding
      $('.wrapper').toggleClass('slim-type')
    }
  }
}
</script>

<style>
@import url(https://fonts.googleapis.com/icon?family=Material+Icons);

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
