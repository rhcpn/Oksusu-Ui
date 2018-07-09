<template>
  <div id="loader">
    <loading :show="show" :label="label" overlay-class="common-spinner-overlay" loader-class="common-spinner-wrapper"></loading>
  </div>
</template>

<script>
import loading from 'vue-full-loading'

var _indicatorCount = []
var _indicatorTime = null
export default {
  name: 'common-spinner',
  components: {
    'loading': loading
  },
  data () {
    return {
      show: false,
      label: 'Loading...'
    }
  },
  methods: {
    showIndicator: function () {
      if (_indicatorCount.length === 0) {
        this.show = true

        if (_indicatorTime == null) {
          _indicatorTime = setInterval(function () {
            _indicatorCount = []
            this.hideIndicator()
          }, 6000)
        }
      }

      _indicatorCount.push(new Date())
    },
    hideIndicator: function () {
      clearInterval(_indicatorTime)
      _indicatorCount.pop()

      if (_indicatorCount.length !== 0) {
        return
      }

      this.show = false
    },
    lazyIndicator: function (lazySeconds) {
      if (lazySeconds == null || !_.isNumber(lazySeconds) || lazySeconds < 0) {
        lazySeconds = 300
      }

      this.showIndicator()
      setTimeout(this.hideIndicator, lazySeconds)
    }
  }
}
</script>

<style>
  .common-spinner-wrapper {
    position: absolute;
    display: inline-block;
    right: 50vw;
    bottom: 50vh;
  }

  .common-spinner-wrapper .wrapper {
    background: none !important;
  }

  .common-spinner-overlay {
    opacity: 1;
  }
</style>
