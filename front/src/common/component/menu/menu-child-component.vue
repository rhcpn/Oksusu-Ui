<template>
  <li class="parent-gnb" :class="{ 'active': isOpen }" @click="openMenu($event)">
    <a href="javascript:"><v-icon>{{data.iconClass}}</v-icon><span>{{data.name}}</span><v-icon class="arrow" v-if="data.children">keyboard_arrow_right</v-icon></a>

    <!-- Menu Depth2 -->
    <ul class="gnb-list" v-if="isFolding || (data.children && isOpen)" :style="{ display: ((isFolding || isOpen)? 'list-item':'none') }">
      <li class="child-gnb" v-for="child in data.children" :key="child.path + child.name" v-on:click.stop="menuActiveHandler($event, 'child')">
        <a href="javascript:"><span>{{child.name}}</span></a>
      </li>
    </ul>
  </li>
</template>

<script>
export default {
  name: 'menu-child-component',
  created: function () {
    // Default Setting
    let current = this.$router.history.current
    if (current == null || current.matched == null) {
      return
    }

    let matched = _.groupBy(current.matched, 'name')
    if (matched.hasOwnProperty(this.data.name)) {
      this.isOpen = true
      setTimeout(function () {
        $("li.child-gnb:contains('" + current.name + "')").addClass('active')
      }, 300)
    }

    // Event Listener
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
      this.menuActiveHandler(event, 'parent')
      if (this.isOpen === true) {
        this.$eventHub.$emit('menu-open', this.data)
      }

      if (this.data != null && this.data.path != null && this.data.path !== '') {
        this.$router.push(this.data.path)
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
    menuActiveHandler: function (event, type) {
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
      if (type === 'parent') {
        $('.gnb-list li').removeClass('active')
      } else {
        $(currentTarget).parents('.parent-gnb').addClass('active')
      }
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
