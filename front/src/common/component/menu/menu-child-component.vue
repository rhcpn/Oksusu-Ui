<template>
  <li class="parent-gnb" v-bind:class="{ 'active': isOpen }" @click="openMenu()">
    <a href="#">
      <v-icon>view_list</v-icon>
      <span>{{data.name}}</span>
      <v-icon class="arrow" v-if="data.children">keyboard_arrow_right</v-icon>
    </a>
    <!-- Depth2 -->
    <ul class="gnb-list" v-if="data.children && isOpen" v-bind:style="{ display: (isOpen? 'list-item':'none') }">
      <li class="child-gnb" v-for="child in data.children" :key="child.path" v-on:click.stop="openChildMenu($event)" v-bind:class="{ 'active': isChildSelect }">
        <a href="#">
          <span>{{child.name}}</span>
        </a>
      </li>
    </ul>
  </li>
</template>

<script>
export default {
  name: 'menu-child-component',
  created: function () {
    this.$eventHub.$on('menu-open', this.openMenuEventHandler)

    // Design Code
    $('.parent-gnb').click(function () {
      $(this).parent().find('>ul').slideDown()
      $(this).parent().addClass('active')
      $(this).parent().siblings().find('ul').slideUp()
      $(this).parent().siblings().removeClass('active').find('.active').removeClass('active')
    })
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
      isChildSelect: false
    }
  },
  computed: {},
  methods: {
    openMenu: function () {
      this.isOpen = !this.isOpen

      if (this.isOpen === true) {
        this.$eventHub.$emit('menu-open', this.data)
      }
    },
    openMenuEventHandler: function (currentMenu) {
      if (this.data.name === currentMenu.name) {
        return
      }
      this.isOpen = false
    },
    openChildMenu: function (event) {
      if (event == null) {
        return
      }

      let currentTarget
      let path = event.path
      for (let i = 0; i < path.length; i++) {
        if (path[i].nodeName === 'LI') {
          if ($(path[i]).hasClass('child-gnb')) {
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
  }
}
</script>
