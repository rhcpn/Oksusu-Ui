<template>
  <div class="layout row gap-06">
    <div class="flex md3">
      <div class="panel-wrap">
        <v-tabs class="header-tabs"
                v-model="active"
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
              <v-card-text><msf-tree :source="source"></msf-tree></v-card-text>
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
          <v-tabs class="sub-tabs"
                  v-model="active"
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
                <v-card-text>{{ n }}</v-card-text>

                <v-btn color="primary" v-on:click="sliderOpen()">Open Dialog 1</v-btn>
              </v-card>
            </v-tab-item>
          </v-tabs>

        </div>
      </div>

    </div>
  </div>

</template>

<script>
import sampleData from '../../asset/json/getResource.json'
import MsfTree from '../../common/component/tree/MsfTree.vue'
export default {
  name: 'resource-infra',
  components: {MsfTree},
  data () {
    return {
      source: [], // 전체 데이터
      active: null
    }
  },
  methods: {
    sliderOpen () {
      this.$eventHub.$emit('slider-open')
      this.$eventHub.$emit('slider-change-data', {'data': '블라블라라'})
    }
  },
  created () {
    this.source = sampleData
  }
}
</script>
