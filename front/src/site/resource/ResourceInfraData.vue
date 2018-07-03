<template>
  <ag-grid-vue style="width: 100%; height: 100%;"
               class="ag-theme-balham"
               :columnDefs="columnDefs"
               :rowData="rowData">
  </ag-grid-vue>
</template>
<script>
import {AgGridVue} from 'ag-grid-vue'

let restUrl = '/resource/infra/datacenter.json'
let dataDepth = '1'
export default {
  name: 'resource-infra-data',
  components: {
    'ag-grid-vue': AgGridVue
  },
  data: function () {
    return {
      resultInfo: Object,
      resultData: [],
      errors: [],
      gridOptions: {},
      columnDefs: [],
      rowData: [],
      enableColResize: true
    }
  },
  methods: {

    setData: function (data) {
      if (data.length !== 0) {
        dataDepth = '1'
      }

      if (data.depth === 2) {
        dataDepth = '2'
        restUrl = '/resource/infra/floor.json'
      } else if (data.depth === 3) {
        dataDepth = '3'
        restUrl = '/resource/infra/room.json'
      } else if (data.depth === 4) {
        dataDepth = '4'
        restUrl = '/resource/infra/list.json?type=rack'
      } else if (data.depth === 5) {
        dataDepth = '5'
        restUrl = '/resource/infra/list.json?type=bm-server'
      } else {
        dataDepth = '1'
        restUrl = '/resource/infra/datacenter.json'
      }

      this.resultDataGrid()
    },
    resultDataGrid: function () {
      let result = []

      this.$http.get(restUrl)
        .then(response => {
          let headerEle = response.data
          let headerList = Object.keys(headerEle.data)
          let header
          if (headerList[1] !== undefined) {
            header = response.data.data[headerList[1]]
          }

          let fieldEle = response.data
          let fieldList = Object.keys(fieldEle.data)
          let fieldData
          let field
          if (fieldList[0] !== undefined) {
            fieldData = response.data.data[fieldList[0]]
            if (fieldList[0] !== undefined) {
              field = Object.keys(fieldData[0])
            }
            this.coldef(dataDepth, header, field)
          }

          for (let i = 0; i < fieldData.length; i++) {
            result.push(fieldData[i])
          }
          this.rowData = []
          this.rowData = result
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    resultDataTabGrid: function (type) {
      let result = []

      restUrl = '/resource/infra/list.json?type=' + type

      this.$http.get(restUrl)
        .then(response => {
          let headerEle = response.data
          let headerList = Object.keys(headerEle.data)
          let header
          if (headerList[1] !== undefined) {
            header = response.data.data[headerList[1]]
          }

          let fieldEle = response.data
          let fieldList = Object.keys(fieldEle.data)
          let fieldData
          let field
          if (fieldList[0] !== undefined) {
            fieldData = response.data.data[fieldList[0]]
            if (fieldList[0] !== undefined) {
              field = Object.keys(fieldData[0])
            }
            this.coldef(dataDepth, header, field)
          }

          for (let i = 0; i < fieldData.length; i++) {
            result.push(fieldData[i])
          }
          this.rowData = []
          this.rowData = result
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    onGridReady (params) {
      this.gridApi = params.api
      this.columnApi = params.columnApi
    },
    getSelectedRows () {
      const selectedNodes = this.gridApi.getSelectedNodes()
      const selectedData = selectedNodes.map(node => node.data)
      const selectedDataStringPresentation = selectedData.map(node => node.make + ' ' + node.model).join(', ')
      alert(`Selected nodes: ${selectedDataStringPresentation}`)
    },
    coldef: function (dataDepth, header, field) {
      this.columnDefs = []
      for (let i = 0; i < header.length; i++) {
        this.columnDefs.push({
          headerName: header[i],
          field: field[i]
        })
      }
    }
  },
  created () {
    this.setData(this.resultData ? this.resultData : [])
  },
  beforeMount () {
  }
}
</script>
