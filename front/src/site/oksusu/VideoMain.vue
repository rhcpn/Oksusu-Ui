<template>
  <ag-grid-vue style="width: 33%; height: 100%;"
               class="ag-theme-balham"
               :columnDefs="columnDefs"
               :rowData="rowData"
               rowSelection="single"
               :gridReady="onGridReady">
  </ag-grid-vue>
</template>

<script>
export default {
  name: 'simple-oksusu',
  // components: {ResourceInfraData},
  methods: {
    onGridReady (params) {
      this.gridApi = params.api
      this.columnApi = params.columnApi
    },
    resultData: function () {
      let result = []

      this.$http.get('/oksusu/getWatchVideoList.json')
        .then(response => {
          // for (let i = 0; i < fieldData.length; i++) {
          // result.push(fieldData[i])
          result = response.data.data
          // }
          this.rowData = []
          this.rowData = result

          /* this.gridOptions.api.refreshCells()
          this.gridOptions.api.refreshView()
          this.gridOptions.api.sizeColumnsToFit() */
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  },
  data: function () {
    return {
      source: [], // 전체 데이터
      columnDefs: null,
      rowData: null,
      enableColResize: true,
      errors: []
    }
  },
  created () {
    this.resultData()
  },
  beforeMount () {
    this.columnDefs = [
      {
        headerName: '시청 영상',
        field: 'USER_SVC_ID',
        width: 700
        // header: {'text-align': 'center'}
      }
    ]
  }
}
</script>
