<template>
  <section class="charts">
    <h3>pie-basic</h3>
    <vue-highcharts :options="pieOptions" ref="pieChart"></vue-highcharts>
  </section>
</template>
<script>

import VueHighcharts from '../../common/component/highchart/VueHighcharts'
import * as data from './data/data'

export default {
  components: {
    VueHighcharts
  },
  data () {
    return {
      pieOptions: data.PieData
    }
  },
  mounted () {
  },
  methods: {}
}
</script>
