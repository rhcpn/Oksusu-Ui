<template>
  <div>
    <VirtualList :size="18" :remain="35" class="list" :style="calculateHeight()" >
      <msf-tree-item v-on:expand="expand" v-on:checkClick="checkclick" v-on:itemClick="itemClick" v-for="item of list" :key="item.id" :data="item" :checked="item.checked"
                     :expanded="item.expanded" :depth="item.depth" :label="item.name" :half-checked="item.halfChecked"/>
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
    source: Array,
    filterFunction: Function
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
      var returnValue = false
      if (item.parent) {
        returnValue = item.visible && this.isExpanded(item.parent)
      } else {
        returnValue = item.visible
      }
      if (this.filterFunction) {
        returnValue = returnValue && this.isFilteredData(item)
      }
      return returnValue
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
    isFilteredData: function (item) {
      if (!item) { return false }
      if (this.hasChild(item)) {
        let children = item.children
        let returnVal = item.isFiltered
        for (var i = 0; i < children.length; i++) {
          let child = children[i]
          returnVal = returnVal || this.isFilteredData(child)
        }
        return returnVal
      }
      return item.isFiltered
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
      let idx = this.filteredItems.indexOf(item)
      let bool = item.checked
      for (let i = idx + 1; i < this.filteredItems.length; i++) {
        let obj = this.filteredItems[i]
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
    itemClick: function (item) { // 아이템 클릭 이벤트 버블링
      this.$emit('itemClick', item)
      for (let i = 0; i < this.items.length; i++) {
        let obj = this.items[i]
        obj.checked = false
      }
      item.checked = true
      this.refresh()
    },
    setActive: function (value, field) {
      for (let i = 0; i < this.items.length; i++) {
        let item = this.items[i]
        if (item[field] === value) { item.checked = true } else { item.checked = false }
      }
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
      if (this.filterFunction) { // 필터 내용이 있으면 확인
        var ar = []
        for (let i = 0; i < this.items.length; i++) {
          let item = this.items[i]
          item.isFiltered = false // 필터 되지 않은 내용
          if (this.hasChild(item)) { // 그룹은 신경쓰지 않는다.
            if (this.filterFunction(item)) {
              item.isFiltered = true
            }
            ar.push(item)
          } else {
            if (this.filterFunction(item)) {
              item.isFiltered = true // 필터링된 내용   필터링된 내용을가지고 있으면 해당 부모는 다 보여야 한다.
              ar.push(item)
            }
          }
        }
        this.filteredItems = ar
      } else {
        this.filteredItems = this.items
      }
      this.list = this.filteredItems.filter(this.treeFilter)
    },
    calculateHeight: function () {
      let w = window.innerHeight - 94 // 94 는 탑부분의 영역
      return {'height': w + 'px'}
    },
    getCheckedData: function () {
      var ar = []
      for (let i = 0; i < this.items.length; i++) {
        let obj = this.items[i]
        if (this.hasChild(obj)) { // 자식이 있는건 그룹이라 패스~
          continue
        }
        if (obj.checked) { ar.push(obj) }
      }
      return ar
    },
    setCheck: function (ar, key) {
      for (let i = 0; i < this.items.length; i++) {
        let obj = this.items[i]

        if (this.hasChild(obj)) { // 자식이 있는건 그룹이라 패스~
          continue
        }
        for (let j = 0; j < ar.length; j++) {
          if (obj[key] === ar[j]) {
            obj.checked = true
            this.checkclick(obj)
            break
          }
        }
      }
      this.refresh()
    },
    setSource: function (source) {
      let ar = []
      let sampleData = source
      for (let i = 0; i < sampleData.length; i++) {
        var obj = sampleData[i]
        this.addItem(ar, obj, 1, null)
      }
      this.items = ar
      this.refresh()
    },
    redraw: function () {
      this.$forceUpdate()
    },
    allExpand: function (bool, depth) { // 특정 depth 이상인 것 확장 변경
      for (let i = 0; i < this.items.length; i++) {
        let item = this.items[i]
        if (this.hasChild(item) && item.depth > depth) {
          item.expanded = bool
        }
      }
      this.refresh()
    }
  },
  data () {
    return {
      items: [], // 전체 데이터
      filteredItems: [],
      list: [] // 닫쳐있는것 제외 하고 필터링 된것 제외한 데이터
    }
  },
  created () {
    // 계층 구조로 들어온 목록을 2차원으로 변형 하면서 필요한 프로퍼티를 입력  datas 는 하이라키 구조의 데이터
    this.setSource(this.source ? this.source : [])
    window.addEventListener('resize', this.redraw)
  },
  destroyed () {
    window.removeEventListener('resize', this.redraw)
  }
}
</script>
