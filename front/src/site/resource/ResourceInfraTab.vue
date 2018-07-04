<template>
  <v-tabs class="sub-tabs"
          v-model="active"
          color="none"
          slider-color="none">
    <v-tab
      v-for="item in tabList"
      :key="item.type"
      ripple
      @click="tabItemClick(item.type)">
     {{ item.name }}({{ item.count }})
    </v-tab>
  </v-tabs>
</template>
<script>
let restUrl
let dataDepth
export default {
  name: 'resource-infra-tab',
  components: {
  },
  data: function () {
    return {
      tabList: [],
      resultData: [],
      errors: [],
      active: null
    }
  },
  methods: {
    tabItemClick: function (type) {
      console.log(type)
      this.$emit('tabItemClick', type)
    },
    setData: function (data) {
      if (data.depth === 4) {
        dataDepth = '4'
        restUrl = '/resource/infra/roomTabList.json'
      } else if (data.depth === 5) {
        dataDepth = '5'
        restUrl = '/resource/infra/rackTabList.json'
      }

      if (data.depth === 4 || data.depth === 5) {
        this.resultTabData()
      }
    },
    resultTabData: function () {
      this.$http.get(restUrl)
        .then(response => {
          this.tabList = null
          let headerEle = response.data.data
          let headerList = (dataDepth === '4' ? headerEle.roomTabList : headerEle.rackTabList)

          let list = []
          for (let i = 0; i < headerList.length; i++) {
            list.push(headerList[i])
          }
          this.tabList = list
          this.active = 0
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  },
  created () {
    this.setData(this.resultData ? this.resultData : [])
  },
  beforeMount () {
  }
}
</script>
