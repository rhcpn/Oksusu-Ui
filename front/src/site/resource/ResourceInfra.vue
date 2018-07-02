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
          <v-select class="select-box left w200"
                    :items="items"
                    v-model="e1"
                    label="목록보기"
                    single-line
          ></v-select>
          <div class="right">
            <div class="input-srh w200">
              <v-icon>search</v-icon>
              <v-text-field
                v-model="search"
                :rules="searchRules"
                placeholder="검색어를 입력하세요"
                required
              ></v-text-field>
            </div>
            <v-btn class="btn-md-cr round" @click="filterOpen()">장비필터{{isFilterOpen? '닫기':'열기'}}</v-btn>
          </div>
        </div>
        <div class="panel-body">
          <equipment-filter :filterOpen="isFilterOpen"></equipment-filter>
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
import equipmentFilter from './equipment-filter'
export default {
  name: 'resource-infra',
  components: {MsfTree, equipmentFilter},
  data () {
    return {
      source: [], // 전체 데이터
      isFilterOpen: false
    }
  },
  methods: {
    sliderOpen () {
      this.$eventHub.$emit('slider-open')
      this.$eventHub.$emit('slider-change-data', {'data': '블라블라라'})
    },
    filterOpen () {
      this.isFilterOpen = !this.isFilterOpen
    }
  },
  created () {
    this.source = sampleData
  }
}
</script>
