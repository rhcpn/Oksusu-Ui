<template>
  <aside>
    <h1 class="logo"><a href="#none">T-CORE</a></h1>
    <div class="gnb">
      <!-- Menu Depth1 -->
      <ul class="gnb-menu" v-if="menus.length">
        <menu-child-component v-for="menu in menus" :key="menu.path" :data="menu" v-if="menu.name !== 'Login'"></menu-child-component>
      </ul>
    </div>
    <div class="bottom-cont">
      하단메뉴
    </div>
  </aside>
</template>

<script>
import menuChild from '@/common/component/menu/menu-child-component'

export default {
  name: 'gnb-component',
  components: {
    'menu-child-component': menuChild
  },
  created: function () {
    let router = this.$router
    if (router == null || router.options == null || router.options.routes == null) {
      setTimeout(this.init, 1000)
      return
    }

    this.init()
  },
  props: {
    width: {
      type: Number,
      default: 300
    }
  },
  data () {
    return {
      wrapperWidth: this.width,
      menus: []
    }
  },
  methods: {
    init: function () {
      for (let route of this.$router.options.routes) {
        if (route.name === 'Main') {
          this.menus = route.children
        }
      }
    }
  }
}
</script>
