<template>
  <div class="container">
    <h1>Resource Test</h1>
    <!--<div>module A constants: {{constants}}</div>-->
    <div>message: {{this.$message.MESSAGE_1}}, {{this.$message.MESSAGE_2}}, {{this.$message.MESSAGE_3}}</div>
  </div>
</template>

<script>

export default {
  name: 'resource-test',
  created: function () {
    // console.log('constants:', constants)
    console.log('$message:', this.$message)
  }
}

</script>
