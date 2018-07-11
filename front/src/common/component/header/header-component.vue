<template>
  <div class="fixed-top-cont">
    <div class="top-left">
      <v-toolbar-side-icon id="foldingBtn" @click.stop="isFolding = !isFolding"></v-toolbar-side-icon>
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
          <v-btn flat class="btn-util-logout" @click="logout"><v-icon color="white">power_settings_new</v-icon></v-btn>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'header-component',
  props: {
    menuFolding: {
      type: Boolean,
      require: true
    }
  },
  data () {
    return {
      isFolding: this.menuFolding
    }
  },
  watch: {
    isFolding: {
      immediate: true,
      handler (val, oldVal) {
        setTimeout(this.menuWrapperHandler, 100)
      }
    }
  },
  methods: {
    menuWrapperHandler () {
      let wrapper = $('.wrapper')
      if (this.isFolding === true) {
        wrapper.addClass('slim-type')
      } else {
        wrapper.removeClass('slim-type')
      }
      this.$eventHub.$emit('menu-folding', this.isFolding)
    },
    logout () {
      this.$store.dispatch('LogOut').then(() => {
        location.reload()
      })
    }
  }
}
</script>
