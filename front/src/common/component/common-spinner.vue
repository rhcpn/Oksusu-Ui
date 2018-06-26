<template>
  <div id="loader">
    <loading :show="show" :label="label" loader-class="common-spinner-wrapper"></loading>
  </div>
</template>

<script>
import Vue from 'vue'
import loading from 'vue-full-loading'

Vue.component('loading', loading)

var _self
var _indicatorCount = []
var _indicatorTime = null
export default {
  name: 'common-spinner',
  data () {
    return {
      show: false,
      label: 'Loading...'
    }
  },
  created: function () {
    _self = this
  },
  methods: {
    showIndicator: function () {
      if (_indicatorCount.length === 0) {
        this.show = true

        if (_indicatorTime == null) {
          _indicatorTime = setInterval(function () {
            _indicatorCount = []
            _self.hideIndicator()
          }, 60000)
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
      if (lazySeconds == null || _.isNumber(lazySeconds) || lazySeconds < 0) {
        lazySeconds = 300
      }

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
</style>
