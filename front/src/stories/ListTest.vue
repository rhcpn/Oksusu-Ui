<template>
  <div class="container">
    <h1>Welcome to List</h1>
    <div id="recordlist">
      <ul class="list-group">
        <li v-for="i in 11" :key="i" class="list-group-item" on-click="#">
          <a href="#" class="list-cell" v-bind:id="'' + i">{{ i-1 }} times 4 equals {{ (i-1) *4 }}.</a>
        </li>
      </ul>
    </div>

  </div>
</template>

<script>

import '../asset/css/bootstrap.css'

const log = () => console.log('Welcome to list!')

export default {
  name: 'list-test',

  props: {
    showApp: {
      type: Function,
      default: log
    }
  },

  methods: {
    onClick (event) {
      event.preventDefault()
      this.showApp()
    }
  }
}
</script>
