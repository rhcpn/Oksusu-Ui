<template>
  <div class="fixed-top-cont">
    <div class="top-left">
      Oksusu Demo &nbsp;&nbsp;<button>[Refresh]</button>
      <!--<v-toolbar-side-icon id="foldingBtn" @click.stop="isFolding = !isFolding"></v-toolbar-side-icon>-->
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
      this.$store.dispatch('logOut').then(() => {
        location.reload()
      })
    }
  }
}
</script>
