<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="onSubmit(email, password)">
      <input type="text" v-model="email" placeholder="Email Address">
      <input type="password" v-model="password" placeholder="Password">
      <input type="submit" value="Login">
    </form>
    <p><i></i></p>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      email: '',
      password: '',
      msg: ''
    }
  },
  methods: {
    onSubmit (email, password) {
      // TODO. login check 하는 로직 추가
      window.isAuthenticated = true
      this.redirect()
    },
    redirect () {
      let returnPath = this.$route.query.redirect || '/'
      // 리다이렉트 처리
      this.$router.push(returnPath)
    }
  }
}
</script>

<style scoped>

</style>
