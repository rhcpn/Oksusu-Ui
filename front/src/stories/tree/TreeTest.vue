<template>
  <msf-tree :source="source"></msf-tree>
</template>

<script>
import sampleData from '../../asset/json/getResource.json'
import MsfTree from './MsfTree.vue'
export default {
  name: 'tree-test',
  components: {MsfTree},
  data () {
    return {
      source: [] // 전체 데이터
    }
  },
  methods: {},
  created () {
    this.source = sampleData
  }
}
</script>
