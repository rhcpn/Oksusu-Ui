<template>
  <ag-grid-vue style="width: 100%; height: 100%;"
               :gridOptions="gridOptions"
               class="ag-theme-balham pt-2"
               :columnDefs="columnDefs"
               :rowData="rowData"
               rowSelection="single"
               :cellClicked="getSelectedRows"
               :gridReady="onGridReady"
  >
  </ag-grid-vue>
</template>
<script>
import {AgGridVue} from 'ag-grid-vue'

let restUrl = '/resource/infra/datacenter.json'
let dataDepth = '1'
export default {
  name: 'resource-infra-data',
  components: {
    'ag-grid-vue': AgGridVue,
    'LinkComponent': {
      template: '<div style="display:inline-block" @mouseover="mouseOver" @mouseleave="mouseLeave"><span v-html="searchKeyword(params)" @click="sliderOpen(true)"></span> <div v-if="aLinkShow" style="display:inline-block" ><a @click="sliderOpen(false)" v-if="rackShowlink()">실장도 보기</a><a @click="sliderOpen(true)" v-if="bmServerShowlink()">상세정보 보기</a></div></div>',
      data: function () {
        return {
          aLinkShow: false
        }
      },
      methods: {
        sliderOpen (show) {
          this.$http.get('/resource/infra/list.json?type=bm-server').then(response => {
            this.dialogData = response.data.data.hardwareList[1]
            this.$eventHub.$emit('slider-open')
            this.$eventHub.$emit('slider-change-data', this.dialogData)
            if (show) {
              this.$eventHub.$emit('slider-detail-show')
            }
            // console.log(dataDepth)
          }).catch(e => {
            this.errors.push(e)
          })
        },
        equipmentModify () {
          console.log('수정')
        },
        equipmentDelete () {
          console.log('삭제')
        },
        mouseOver () {
          this.aLinkShow = true
        },
        mouseLeave () {
          this.aLinkShow = false
        },
        searchKeyword (row) {
          if (row == null || row.value == null) {
            return
          }

          let val = String(row.value)
          let searchWord = 'ultra'
          if (val.indexOf(searchWord) > -1) {
            let arr = val.split(searchWord)
            val = arr[0] + '<span style="background-color: yellow">' + searchWord + '</span>' + arr[1]
          }

          return val
        },
        rackShowlink () {
          var show = false
          var dep = parseInt(dataDepth)

          // rack
          if (dep === 4) {
            show = true
          }

          return show
        },
        bmServerShowlink () {
          var show = false
          var dep = parseInt(dataDepth)

          // bmserver
          if (dep === 5) {
            show = true
          }

          return show
        }
      }
    }
  },
  data: function () {
    return {
      resultInfo: Object,
      resultData: [],
      errors: [],
      gridOptions: {},
      columnDefs: [],
      rowData: [],
      enableColResize: true,
      dialogData: [],
      searchType: false
    }
  },
  methods: {

    setData: function (data, searchType) {
      if (data.length !== 0) {
        dataDepth = '1'
      }
      this.searchType = searchType

      if (!this.searchType) {
        if (data.depth === 2) {
          dataDepth = '2'
          restUrl = '/resource/infra/floor.json'
        } else if (data.depth === 3) {
          dataDepth = '3'
          restUrl = '/resource/infra/room.json'
        } else if (data.depth === 4) {
          dataDepth = '4'
          // if (type === '') {
          restUrl = '/resource/infra/list.json?type=rack'
        } else if (data.depth === 5) {
          dataDepth = '5'
          restUrl = '/resource/infra/list.json?type=bm-server'
        } else {
          dataDepth = '1'
          restUrl = '/resource/infra/datacenter.json'
        }
      } else {
        restUrl = '/resource/infra/filterList.json'
      }

      this.resultDataGrid(this.searchType)
    },
    resultDataGrid: function (searchType) {
      let result = []

      this.$http.get(restUrl)
        .then(response => {
          let headerEle = response.data
          let headerList = Object.keys(headerEle.data)
          let header
          if (headerList[1] !== undefined) {
            header = response.data.data[headerList[1]]
          }

          let fieldData
          let field
          if (headerList[0] !== undefined) {
            fieldData = response.data.data[headerList[0]]
            if (headerList[0] !== undefined) {
              field = Object.keys(fieldData[0])
            }
          }

          // for (let i = 0; i < fieldData.length; i++) {
          // result.push(fieldData[i])
          result = fieldData
          // }
          this.rowData = []
          this.rowData = result

          this.coldef(dataDepth, header, field, searchType)

          this.gridOptions.api.refreshCells()
          this.gridOptions.api.refreshView()
          this.gridOptions.api.sizeColumnsToFit()
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

          let fieldData
          let field
          if (headerList[0] !== undefined) {
            fieldData = response.data.data[headerList[0]]
            if (headerList[0] !== undefined) {
              field = Object.keys(fieldData[0])
            }
          }

          // for (let i = 0; i < fieldData.length; i++) {
          // result.push(fieldData[i])
          result = fieldData
          // }
          this.rowData = []
          this.rowData = result

          this.coldef(dataDepth, header, field)
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    onGridReady (params) {
      this.gridApi = params.api
      this.columnApi = params.columnApi
    },
    getSelectedRows: function () {
      let selectedNodes
      let selectedData
      selectedNodes = this.gridApi.getSelectedNodes()
      selectedData = selectedNodes.map(node => node.data)
      this.$emit('selectedData', selectedData[0])
    },
    coldef: function (dataDepth, header, field, searchType) {
      this.columnDefs = []
      for (let i = 0; i < header.length; i++) {
        this.columnDefs.push({
          headerName: header[i].name,
          hide: header[i].hide,
          field: field[i]
        })

        if (searchType) {
          // this.columnDefs[2].colId = 'params' + i
          // this.columnDefs[i].cellRendererFramework = 'SearchComponent'
          // console.log(this.columnDefs[i].cellRenderer)
        }

        var depth = parseInt(dataDepth)
        if (i === 1 && depth >= 4) {
          this.columnDefs[1].cellRendererFramework = 'LinkComponent'
          this.columnDefs[1].width = 250
          continue
        } else {
          this.columnDefs[i].cellRenderer = this.searchRenderer
        }
      }
    },
    searchRenderer (row) {
      if (row == null || row.value == null) {
        return
      }

      let val = String(row.value)
      let searchWord = 'ultra'
      if (val.indexOf(searchWord) > 0) {
        let arr = val.split(searchWord)
        val = arr[0] + '<span style="background-color: yellow">' + searchWord + '</span>' + arr[1]
      }

      return val
    }
  },
  created () {
    this.setData(this.resultData ? this.resultData : [])
  },
  beforeMount () {
    this.gridOptions = {
      context: {
        componentParent: this
      }
    }
  }
}
</script>
