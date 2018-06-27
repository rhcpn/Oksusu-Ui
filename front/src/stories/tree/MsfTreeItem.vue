<template>
  <div class="treeitem">
    <div :style="{ width: data.depth * 10 + 'px'}"></div>
    <div class="expanded" :style="{cursor : hasChild(data) ? 'pointer' : ''}" v-on:click="onClick">{{ hasChild(data)?
      expanded ? '-' : '+' : ''}}
    </div>
    <div class="checkbox" v-bind:class="isChecked" v-on:click="checkClick"></div>
    <div class="item" v-on:click="checkClick(data)">{{ label }}</div>
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
    hasChild: function (data) {
      return data && data.hasOwnProperty('children') && data.children && data.children.length > 0
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
    height: 30px;
  }
  .treeitem .expanded {
    width: 20px;
  }
  .treeitem .checkbox {
    width: 20px;
    height: 20px;
    border:1px solid #CCCCCC
  }
  .checked {
    background-color: #00FF00
  }
  .halfchecked {
    background-color: #FFFF00
  }
  .treeitem .item {
    cursor: pointer
  }
</style>
