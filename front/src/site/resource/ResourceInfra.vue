<template>
  <div class="layout row gap-06">
    <div class="flex md3">
      <div class="panel-wrap">
        <v-tabs class="header-tabs"
                color="none"
                slider-color="none"
        >
          <v-tab
            v-for="n in tabList"
            :key="n"
            ripple
          >
            {{ n }}
          </v-tab>
          <v-tab-item
            v-for="n in tabList"
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
          <ul class="breadcrumbs left" v-if="selectDepthArray.length" >
            <li v-for="depth in selectDepthArray" :key="depth.id"><a href="#none">{{depth.name}}</a></li>
          </ul>
          <v-btn class="btn-md-tool" v-if="isViewTypeEnable()" v-bind:class="{ on: viewType == 'list' }" @click="viewType = 'list'"><v-icon>view_list</v-icon></v-btn>
          <v-btn class="btn-md-tool" v-if="isViewTypeEnable()" v-bind:class="{ on: viewType == 'img' }" @click="viewType = 'img'"><v-icon>view_module</v-icon></v-btn>
          <div class="right">
            <div class="input-srh w200">
              <v-icon>search</v-icon>
              <v-text-field
                @keyup.enter="searchInfo()"
                v-model="searchWord"
                placeholder="검색어를 입력하세요"
                required
              ></v-text-field>
            </div>
            <v-btn class="btn-md-cr round" @click="isFilterOpen = !isFilterOpen">장비필터{{isFilterOpen? '닫기':'열기'}}</v-btn>
          </div>
        </div>

        <div class="panel-body">
          <equipment-filter :filterOpen="isFilterOpen"></equipment-filter>
          <resource-infra-tab v-show="tabOpen" v-on:tabItemClick="tabItemClick" ref="tabData"></resource-infra-tab>
          <div class="inner-scroll" v-show="viewType == 'list'"><resource-infra-data :resultInfo="resultInfo" v-on:selectedData="selectedData" ref="resourceData"></resource-infra-data></div>
          <div class="inner-scroll" v-show="viewType == 'img'">
            <v-layout justify-center align-center>
              <img src="@/asset/images/common/top_view.png" alt="" style="width:1334px;height:842px;overflow:hidden" v-if="isViewTypeImageSelected() === false">
              <img src="@/asset/images/common/top_view_selected.png" alt="" style="width:1339px;height:842px;overflow:hidden" v-if="isViewTypeImageSelected() === true  ">
            </v-layout>
          </div>
        </div>
      </div>

    </div>
  </div>

</template>

<script>
import MsfTree from '../../common/component/tree/MsfTree.vue'
import ResourceInfraTab from './ResourceInfraTab.vue'
import ResourceInfraData from './ResourceInfraData.vue'
import equipmentFilter from './equipment-filter'
import { getNavigation } from '@/api/resourceInfra'
export default {
  name: 'resource-infra',
  components: {MsfTree, ResourceInfraTab, ResourceInfraData, equipmentFilter},
  methods: {
    itemClick: function (item, searchType) {
      if (searchType === undefined) {
        searchType = false
      }

      this.resultInfo = item
      if (!searchType) {
        if (this.resultInfo.depth === 4 || this.resultInfo.depth === 5) {
          this.tabOpen = true
          this.$refs.tabData.setData(this.resultInfo)
        } else {
          this.tabOpen = false
        }
      } else {}

      this.$refs.resourceData.setData(this.resultInfo, searchType)

      this.selectDepthArray = []
      this.getDepthArray(item)
      this.selectDepthArray.push(item)
      console.log(this.selectDepthArray)

      // 목록 / 상면도 보기 초기화
      this.viewType = 'list'
    },
    tabItemClick: function (type) {
      this.gridTabType = type
      this.$refs.resourceData.resultDataTabGrid(type)
    },
    getDepthArray: function (data) {
      if (data && data.hasOwnProperty('parent') && data.parent) {
        this.selectDepthArray.unshift(data.parent)
        this.getDepthArray(data.parent)
      }
    },
    searchInfo: function () {
      this.tabOpen = false
      if (this.resultInfo.length === 0) {
        if (this.$data.searchWord.length === 0) {
          this.$refs.resourceData.setData(this.resultInfo, false)
        } else {
          this.$refs.resourceData.setData(this.resultInfo, true)
        }
      } else {
        if (this.$data.searchWord.length === 0) {
          if (this.resultInfo.depth === 1 || this.resultInfo.depth === 2 || this.resultInfo.depth === 3) {
            this.$refs.resourceData.setData(this.resultInfo, false)
          } else {
            this.tabOpen = true
            this.$refs.tabData.setData(this.resultInfo)
            this.$refs.resourceData.resultDataTabGrid(this.gridTabType)
          }
        } else {
          this.$refs.resourceData.setData(this.resultInfo, true)
        }
      }
    },
    isViewTypeEnable: function () {
      return this.resultInfo.type && this.resultInfo.type !== 'datacenter' && this.resultInfo.type !== 'floor'
    },
    isViewTypeImageSelected: function () {
      return this.resultInfo.type === 'rack'
    },
    selectedData: function (data) {
      this.$refs.resourceTree[0].setActive(data.name, 'name')
    }
  },
  data: function () {
    return {
      source: [], // 전체 데이터
      resultInfo: [],
      errors: [],
      isFilterOpen: false,
      tabOpen: false,
      selectDepthArray: [],
      searchType: false,
      viewType: 'list',
      tabList: ['Infra', 'Service'],
      searchWord: '',
      gridTabType: ''

    }
  },
  created () {
    getNavigation().then(response => {
      this.source = response.data.data
      this.$refs.resourceTree[0].setSource(this.source)
      this.$refs.resourceTree[0].allExpand(false, 2)

      this.selectDepthArray = []
      this.selectDepthArray.push(this.source[0])
    }).catch(e => {
      this.errors.push(e)
    })
  }
}
</script>
