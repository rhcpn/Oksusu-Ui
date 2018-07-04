<template>
  <div class="treeitem">
    <div :style="{ width: data.depth * 20 + 'px'}"></div>
    <div class="expanded" :style="{cursor : hasChild(data) ? 'pointer' : ''}" :class="hasChild(data) ? expanded ? 'open' : 'closed' : 'empty'" v-on:click="onClick">
    </div>
    <div class="item" v-on:click="itemClick(data)"><div class="ico-tree" :class="getIcon()"></div><div style="padding-left: 5px"> {{ label }}</div></div>
  </div>
</template>

<script>
export default {
  name: 'msf-tree-item',
  props: {
    data: Object,
    checked: Boolean,
    expanded: Boolean,
    depth: Number,
    label: String,
    halfChecked: Boolean
  },
  methods: {
    getIcon: function () {
      let data = this.data
      if (data && data.hasOwnProperty('type') && data.type) {
        return 'i-' + data.type
      }
      return 'i-bm-server'
    },
    hasChild: function (data) {
      return data && data.hasOwnProperty('children') && data.children && data.children.length > 0
    },
    itemClick: function () {
      this.$emit('itemClick', this.data)
    },
    checkClick: function () {
      let bool = !this.data.checked
      this.data.checked = bool
      this.$emit('checkclick', this.data)
    },
    onClick: function () {
      let item = this.data
      if (!this.hasChild(item)) {
        return
      }
      item.expanded = !item.expanded
      this.$emit('expand', item)
    }
  },
  computed: {
    isChecked: function () {
      if (this.checked) {
        return 'checked'
      } else {
        if (this.halfChecked) {
          return 'halfchecked'
        } else {
          return ''
        }
      }
    }

  }
}
</script>
<style>
  .treeitem {
    width: 100%;
  }

  .treeitem div {
    display: inline-block;
  }
  .treeitem .expanded {
    width: 20px;
  }
  .treeitem .checkbox {
    width: 20px;
    height: 20px;
    border:1px solid #CCCCCC
  }
  .treeitem .empty {
    width:10px;
  }
  .checked {
    background-color: #00FF00
  }
  .halfchecked {
    background-color: #FFFF00
  }
  .treeitem .item {
    cursor: pointer;
    height:20px;
  }
</style>
