<template>
  <ag-grid-vue style="height: 500px;"
               class="ag-theme-balham"
               :gridOptions="gridOptions"
               :columnDefs="columnDefs"
               :rowData="rowData"
               :enableSorting="true"
               :enableFilter="true"
               rowSelection="single"
               :gridReady="onGridReady">
               <!--:cellClicked="doubleClick"-->
               <!--rowSelection="multiple" , "single"-->
               <!--:cellDoubleClicked="test"-->

  </ag-grid-vue>
</template>

<script>
import {AgGridVue} from 'ag-grid-vue'

export default {
  name: 'ag-grid',
  data () {
    return {
      gridOptions: {},
      columnDefs: null,
      rowData: null,
      enableColResize: true
    }
  },
  components: {
    'ag-grid-vue': AgGridVue
  },
  methods: {
    onGridReady (params) {
      this.gridApi = params.api
      this.columnApi = params.columnApi
    },
    getSelectedRows () {
      const selectedNodes = this.gridApi.getSelectedNodes()
      const selectedData = selectedNodes.map(node => node.data)
      const selectedDataStringPresentation = selectedData.map(node => node.make + ' ' + node.model).join(', ')
      alert(`Selected nodes: ${selectedDataStringPresentation}`)
    }
    // doubleClick () {
    //   var selectedRows = this.gridApi.getSelectedRows()
    //   var selectedRowsString = ''
    //   selectedRows.forEach(function (selectedRow, index) {
    //     if (index !== 0) {
    //       selectedRowsString += ', '
    //     }
    //     selectedRowsString += selectedRow.price
    //   })
    //   alert(selectedRowsString)
    // }
    // onCellValueChanged: function (params) {
    //   var colId = params.column.getId()
    //   if (colId === 'country') {
    //     var selectedCountry = params.data.country
    //     var selectedCity = params.data.city
    //
    //     var allowedCities = countyToCityMap[selectedCountry]
    //     var cityMismatch = allowedCities.indexOf(selectedCity) < 0
    //
    //     if (cityMismatch) {
    //       params.node.setDataValue('city', null)
    //     }
    //   }
    // }
  },
  beforeMount () {
    this.columnDefs = [
      {headerName: 'Make',
        field: 'make',
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        checkboxSelection: true},
      {headerName: 'Model', field: 'model', cellStyle: {color: 'red', 'background-color': 'green'}},
      {headerName: 'Price',
        field: 'price',
        editable: true,
        cellEditor: 'agLargeTextCellEditor',
        cellEditorParams: {
          maxLength: '20', // override the editor defaults
          cols: '10',
          rows: '2'
        }},
      {
        field: 'country',
        width: 100,
        editable: true,
        cellEditor: 'agSelectCellEditor',
        cellEditorParams: {
          values: ['Ireland', 'USA']
        }
      }
    ]

    this.rowData = [
      {make: 'Toyota',
        model: 'Celica',
        price: 35000,
        country: 'Ireland'
      },
      {make: 'Ford',
        model: 'Mondeo',
        price: 32000,
        country: 'Ireland'
      },
      {make: 'Porsche',
        model: 'Boxter',
        price: 72000,
        country: 'Ireland'
      }
    ]
  }
}
</script>
