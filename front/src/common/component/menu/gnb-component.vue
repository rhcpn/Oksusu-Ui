<template>
  <aside>
    <h1 class="logo"><a href="#none">T-CORE</a></h1>

    <!-- Top -->
    <div class="gnb">
      <ul class="gnb-menu" v-if="topMenus != null && topMenus.length > 0">
        <menu-child-component v-for="(menu, key) in topMenus" :key="key" :data="menu"></menu-child-component>
      </ul>
    </div>

    <!-- Bottom -->
    <div class="bottom-cont" v-if="bottomMenus != null && bottomMenus.length > 0">
      <div class="gnb bottom">
        <ul class="gnb-menu">
          <menu-child-component v-for="(menu, key) in bottomMenus" :key="key" :data="menu"></menu-child-component>
        </ul>
      </div>
    </div>
  </aside>
</template>

<script>
import menuChild from '@/common/component/menu/menu-child-component'

var menus = []
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
      topMenus: [],
      bottomMenus: []
    }
  },
  methods: {
    init: function () {
      // menus = this.$router.options.routes
      // Main에 할당된 menu만 보여준다
      for (let i = 0; i < this.$router.options.routes.length; i++) {
        if (this.$router.options.routes[i].name === 'Main') {
          menus = this.$router.options.routes[i].children
          break
        }
      }
      if (menus && menus.length) {
        let menuTypes = _.groupBy(menus, 'position')
        this.topMenus = menuTypes.top
        this.bottomMenus = menuTypes.bottom
      }
    }
  }
}
</script>
