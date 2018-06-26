<template>
  <div>
    <h1>Axios Sample Page</h1>
    <span>
      <h3>다국어 테스트: {{ $t('test') }}</h3>
      <button @click="changeLanguage">change Language</button>
    </span>
    <div>
      <button @click="requestBulk">REQUEST BULK</button>
      <button @click="searchTermSuccess">GET SUCCESS</button>
      <button @click="searchTermFail">GET FAIL[404]</button>
      <div v-for="post in posts" :key="post.id">
        <h1>{{post.title}}</h1>
        <p>{{post.body}}</p>
      </div>
    </div>
  </div>
</template>

<script>
var lang = 'ko'
export default {
  name: 'axios-sample',
  data () {
    return {
      posts: []
    }
  },
  computed: {
  },
  methods: {
    requestBulk: function () {
      for (let i = 0; i < 10; i++) {
        this.$http.get('https://jsonplaceholder.typicode.com/posts')
          .then((result) => {
            console.log(`[indicator test] request success${i}`)
          })
      }
    },
    changeLanguage: function () {
      if (lang === 'en') {
        lang = 'ko'
      } else {
        lang = 'en'
      }

      this.$root.changeLocaleLanguage(lang)
    },
    searchTermSuccess: function () {
      const baseURI = 'https://jsonplaceholder.typicode.com'
      this.$http.get(`${baseURI}/posts`)
        .then((result) => {
          console.log(result)
          console.log('=========jquery test', $('body'))
          console.log('=========moment test', moment())
          console.log('=========underscore test', _.each([1, 2, 3], x => { console.log('=========' + x) }))
          console.log('=========다국어', this.$t('test'))
          this.posts = result.data
        })
    },
    searchTermFail: function () {
      const baseURI = 'https://jsonplaceholder.typicode.com/tt'
      this.$http.get(`${baseURI}/posts`)
        .then((result) => {
          console.log(result)
          this.posts = result.data
        })
    }
  }
}
</script>

<style scoped>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    max-width: 560px;
  }
</style>
