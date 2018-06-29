<template>
  <div>
    <VirtualList :size="60" :remain="20" class="list">
      <msf-tree-item v-on:expand="expand" v-on:checkclick="checkclick" v-for="item of list" :key="item.id" :data="item" :checked="item.checked"
            :expanded="item.expanded" :depth="item.depth" :label="item.resourceName" :half-checked="item.halfChecked"/>
    </VirtualList>
  </div>
</template>

<script>
import MsfTreeItem from './MsfTreeItem.vue'
import VirtualList from 'vue-virtual-scroll-list'

export default {
  name: 'msf-tree',
  components: {MsfTreeItem, VirtualList},
  props: {
    source: Array
  },
  methods: {
    addItem: function (items, obj, depth, parent) {
      obj.depth = depth
      obj.expanded = false
      if (obj.hasOwnProperty('children') && obj.children && obj.children.length > 0) {
        obj.expanded = true
      }
      obj.id = Math.random()
      obj.visible = true
      obj.checked = false
      obj.parent = parent
      obj.childAllCheck = false
      items.push(obj)
      if (obj.hasOwnProperty('children') && obj.children && obj.children.length > 0) {
        for (var i = 0; i < obj.children.length; i++) {
          this.addItem(items, obj.children[i], depth + 1, obj)
        }
      }
    },
    hasChild: function (data) {
      return data && data.hasOwnProperty('children') && data.children && data.children.length > 0
    },
    treeFilter: function (item) {
      if (item.parent) {
        return item.visible && this.isExpanded(item.parent)
      } else {
        return item.visible
      }
    },
    childVisibleChange (item, visible) {
      if (this.hasChild(item)) {
        let child = item.children
        for (var i = 0; i < child.length; i++) {
          child[i].visible = visible
          this.childVisibleChange(child[i], visible)
        }
      }
    },
    expand: function (item) {
      this.childVisibleChange(item, item.expanded)
      this.refresh()
    },
    isExpanded: function (item) {
      if (item.parent) {
        return this.isExpanded(item.parent) && item.expanded
      } else {
        return true
      }
    },
    checkclick: function (item) {
      let idx = this.items.indexOf(item)
      let bool = item.checked
      for (let i = idx + 1; i < this.items.length; i++) {
        let obj = this.items[i]
        if (obj.depth <= item.depth) { break }
        obj.checked = bool
      }

      // 절반체크 확인
      for (let i = this.items.length - 1; i >= 0; i--) {
        let obj = this.items[i]
        if (!this.hasChild(obj)) {
          obj.halfChecked = false
          continue
        } else { // 자식이 있다
          this.setCheckType(obj)
        }
      }
      this.refresh()
    },
    setCheckType: function (item) {
      let idx = this.items.indexOf(item)
      let isAllChecked = true
      let isAllUnChecked = true

      for (let i = idx + 1; i < this.items.length; i++) {
        let obj = this.items[i]
        if (obj.depth <= item.depth) { break }

        if (this.hasChild(obj)) { // 자식이 있는건 그룹이라 패스~
          continue
        }

        isAllChecked = isAllChecked && obj.checked
        isAllUnChecked = isAllUnChecked && !obj.checked
      }

      if (isAllChecked) {
        item.halfChecked = false
        item.checked = true
      } else if (isAllUnChecked) {
        item.halfChecked = false
        item.checked = false
      } else {
        item.halfChecked = true
        item.checked = false
      }
    },
    refresh: function () {
      this.list = this.items.filter(this.treeFilter)
    }
  },
  data () {
    return {
      items: [], // 전체 데이터
      list: [] // 닫쳐있는것 제외 하고 필터링 된것 제외한 데이터
    }
  },
  created () {
    // 계층 구조로 들어온 목록을 2차원으로 변형 하면서 필요한 프로퍼티를 입력  datas 는 하이라키 구조의 데이터
    let ar = []
    let sampleData = this.source
    console.log(this.source)
    for (let i = 0; i < sampleData.length; i++) {
      var obj = sampleData[i]
      this.addItem(ar, obj, 1, null)
    }
    this.items = ar
    this.refresh()
  }
}
</script>

<style>
  .scrollToIndex {
    padding-bottom: 20px;
  }
  input {
    outline: none;
    padding: .4em .5em;
    width: 55px;
    height: 16px;
    border-radius: 3px;
    border: 1px solid;
    border-color: #dddddd;
    font-size: 16px;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
  }
  input:focus {
    border-color: #6495ed;
  }
  small {
    color: #999;
  }
 .source {
    text-align: center;
    padding-top: 20px;
  }
  .source a {
    color: #999;
    text-decoration: none;
    font-weight: 100;
  }
</style>
