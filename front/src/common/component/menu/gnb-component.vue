<template>
  <aside>
    <h1 class="logo"><a href="#none">T-CORE</a></h1>
    <div class="gnb">
      <!-- Depth1 -->
      <ul class="gnb-menu" v-if="menus.length">
        <menu-child-component v-for="menu in menus" :key="menu.path" :data="menu"></menu-child-component>
      </ul>
    </div>
    <div class="bottom-cont">
      하단메뉴
    </div>
  </aside>
</template>

<script>
import Vue from 'vue'
import menuChild from '@/common/component/menu/menu-child-component'

Vue.component('menu-child-component', menuChild)

const SLIM_MENU_WIDTH = 50
export default {
  name: 'gnb-component',
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
    },
    folding: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      wrapperWidth: this.width,
      isFolding: this.folding,
      foldingIcon: '▶',
      menus: []
    }
  },
  watch: {
    isFolding: {
      immediate: true,
      handler (val, oldVal) {
        console.log('is Folding event;', val, oldVal)
        if (val === true) {
          this.wrapperWidth = SLIM_MENU_WIDTH
          this.foldingIcon = '▶'
        } else {
          this.wrapperWidth = this.width
          this.foldingIcon = '◀'
        }
      }
    }
  },
  methods: {
    init: function () {
      this.menus = this.$router.options.routes
    },
    menuFoldingHandler: function () {
      this.isFolding = !this.isFolding
    }
  }
}
</script>
