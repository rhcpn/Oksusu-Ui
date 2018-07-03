<template>
  <div class="layout row gap-06">
    <div class="flex md3">
      <div class="panel-wrap">
        <v-tabs class="header-tabs"
                color="none"
                slider-color="none"
        >
          <v-tab
            v-for="n in 2"
            :key="n"
            ripple
          >
            Item {{ n }}
          </v-tab>
          <v-tab-item
            v-for="n in 2"
            :key="n"
          >
            <v-card flat>
              <v-card-text><msf-tree :source="source" v-on:itemClick="itemClick" ref="resourceTree"></msf-tree></v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs>

      </div>
    </div>

    <div class="flex md9">
      <div class="panel-wrap">
        <div class="panel-header">
          <ul class="breadcrumbs left">
            <li><a href="#none">SK</a></li>
            <li><a href="#none">Data center1</a></li>
            <li><a href="#none">Data center2</a></li>
          </ul>
        </div>

        <div class="panel-body">
          <resource-infra-data :resultInfo="resultInfo" ref="resourceData"></resource-infra-data>
        </div>
      </div>

    </div>
  </div>

</template>

<script>
import MsfTree from '../../common/component/tree/MsfTree.vue'
import ResourceInfraData from './ResourceInfraData.vue'
export default {
  name: 'resource-infra',
  components: {MsfTree, ResourceInfraData},
  methods: {
    itemClick: function (item) {
      this.resultInfo = item
      this.$refs.resourceData.setData(this.resultInfo)
    }
  },
  data: function () {
    return {
      source: [], // 전체 데이터
      resultInfo: [],
      errors: []
    }
  },
  created () {
    this.$http.get('/resource/infra/navigation.json')
      .then(response => {
        this.source = response.data.data
        this.$refs.resourceTree[0].setSource(this.source)
      })
      .catch(e => {
        this.errors.push(e)
      })
  }
}
</script>
