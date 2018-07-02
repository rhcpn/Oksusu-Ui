<template>
  <div class="filter-tbl mb-2" v-show="filterOpen">
    <table>
      <colgroup>
        <col style="width:15%">
        <col style="width:auto">
      </colgroup>
      <tbody id="filter">
      <tr>
        <th>항목</th>
        <td>
          <ul class="filter-list">
            <li v-for="field in data.filterHwFieldList" :id="field.type" :key="field.type" @click="filterHandler(field)"><a href="#none">{{field.name || field.type}}</a></li>
          </ul>
        </td>
      </tr>
      <tr>
        <th>H/W상태</th>
        <td>
          <ul class="filter-list">
            <li v-for="status in data.filterHwStatusList" :id="status.type" :key="status.type" @click="filterHandler(status)"><a href="#none">{{status.name || status.type}}</a></li>
          </ul>
        </td>
      </tr>
      <tr>
        <th>H/W타입</th>
        <td>
          <ul class="filter-list">
            <li v-for="hwType in data.filterHwTypeList" :id="hwType.type" :key="hwType.type" @click="filterHandler(hwType)"><a href="#none">{{hwType.name || hwType.type}}</a></li>
          </ul>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="filter-selected">
      <v-btn flat class="btn-md" @click="removefilter()"><v-icon>replay</v-icon>전체해제</v-btn>
      <v-btn flat class="btn-md" v-for="item in selectedItem" :key="item.type" @click="removefilter(item)"><v-icon class="md-18">clear</v-icon>{{item.name || item.type}}</v-btn>

    </div>
  </div>
</template>

<script>
var selectedMap = {}
export default {
  name: 'equipment-filter',
  created: function () {
    this.$http.get('/resource/infra/filterItemList.json')
      .then(response => {
        if (response == null || response.data == null || response.data.data == null) {
          return
        }
        console.log('=================================================================', response.data.data)

        this.data = response.data.data
      })
      .catch(e => {
        this.errors.push(e)
      })
  },
  props: {
    filterOpen: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      data: null,
      selectedItem: []
    }
  },
  methods: {
    filterHandler (item) {
      if (item == null) {
        return
      }

      let sel = $(`#${item.type}`)
      if (selectedMap[item.type] == null) {
        this.selectedItem.push(item)
        selectedMap[item.type] = item
        sel.addClass('active')
      } else {
        let idx = _.findIndex(this.selectedItem, {type: item.type})
        this.selectedItem.splice(idx, 1)
        delete selectedMap[item.type]
        sel.removeClass('active')
      }
    },
    removefilter (target) {
      if (target == null) {
        selectedMap = {}
        this.selectedItem = []
        $('#filter li').removeClass('active')
      } else {
        let idx = _.findIndex(this.selectedItem, {type: target.type})
        this.selectedItem.splice(idx, 1)
        delete selectedMap[target.type]
        $(`#${target.type}`).removeClass('active')
      }
    }
  }
}
</script>
