<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <v-btn color="primary" v-on:click="sliderOpen()">Open Dialog 1</v-btn>
  </div>
</template>

<script>
export default {
  name: 'A',
  data () {
    return {
      msg: 'A Module Page'
    }
  },
  methods: {
    sliderOpen () {
      this.$eventHub.$emit('slider-open')
      this.$eventHub.$emit('slider-change-data', {'data': '블라블라라'})
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
