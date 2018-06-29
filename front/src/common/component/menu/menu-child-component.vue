<template>
  <li class="parent-gnb" v-bind:class="{ 'active': isOpen }" @click="openMenu($event)">
    <a href="#"><v-icon>view_list</v-icon><span>{{data.name}}</span><v-icon class="arrow" v-if="data.children">keyboard_arrow_right</v-icon></a>

    <!-- Menu Depth2 -->
    <ul class="gnb-list" v-if="isFolding || (data.children && isOpen)" v-bind:style="{ display: ((isFolding || isOpen)? 'list-item':'none') }">
      <li class="child-gnb" v-for="child in data.children" :key="child.path" v-on:click.stop="openChildMenu($event, 'child')">
        <a href="#"><span>{{child.name}}</span></a>
      </li>
    </ul>
  </li>
</template>

<script>
export default {
  name: 'menu-child-component',
  created: function () {
    this.$eventHub.$on('menu-open', this.menuOpenEventHandler)
    this.$eventHub.$on('menu-folding', this.menuFoldingEventHandler)
  },
  props: {
    data: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      isOpen: false,
      isFolding: false
    }
  },
  computed: {},
  methods: {
    openMenu: function (event) {
      this.isOpen = !this.isOpen
      this.openChildMenu(event, 'parent')
      if (this.isOpen === true) {
        this.$eventHub.$emit('menu-open', this.data)
      }
    },
    menuOpenEventHandler: function (currentMenu) {
      if (this.data.name === currentMenu.name) {
        return
      }
      this.isOpen = false
    },
    menuFoldingEventHandler: function (folding) {
      this.isFolding = folding
    },
    openChildMenu: function (event, type) {
      if (event == null) {
        return
      }

      let currentTarget
      let path = event.path
      for (let i = 0; i < path.length; i++) {
        if (path[i].nodeName === 'LI') {
          if ($(path[i]).hasClass(`${type}-gnb`)) {
            currentTarget = path[i]
            break
          }
        }
      }

      $(currentTarget).siblings().removeClass('active')
      if (!$(currentTarget).hasClass('active')) {
        $(currentTarget).addClass('active')
      }
    }
  },
  destroyed: function () {
    this.$eventHub.$off('menu-open')
    this.$eventHub.$off('menu-folding')
  }
}
</script>
