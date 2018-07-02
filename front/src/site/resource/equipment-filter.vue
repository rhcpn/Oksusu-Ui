<template>
  <div class="filter-tbl mb-2" v-show="isOpen">
    <table>
      <colgroup>
        <col style="width:15%">
        <col style="width:auto">
      </colgroup>
      <tbody>
      <tr>
        <th>항목</th>
        <td>
          <ul class="filter-list">
            <li class="active"><a href="#none">장비용도</a></li>
            <li><a href="#none">Host 명</a></li>
            <li><a href="#none">H/W 타입</a></li>
            <li><a href="#none">H/W 상태</a></li>
            <li><a href="#none">서비스대표 IP</a></li>
            <li><a href="#none">담당자 정보</a></li>
            <li><a href="#none">Service명</a></li>
            <li><a href="#none">ERP번호</a></li>
            <li><a href="#none">바코드 No</a></li>
            <li><a href="#none">메모</a></li>
            <li><a href="#none">시리얼번호</a></li>
          </ul>
        </td>
      </tr>
      <tr>
        <th>H/W상태</th>
        <td>
          <ul class="filter-list">
            <li><a href="#none">사용중</a></li>
            <li><a href="#none">유휴</a></li>
            <li class="active"><a href="#none">불휴</a></li>
            <li><a href="#none">재활용예정</a></li>
            <li><a href="#none">제각예정</a></li>
            <li><a href="#none">제각</a></li>
          </ul>
        </td>
      </tr>
      <tr>
        <th>H/W타입</th>
        <td>
          <ul class="filter-list">
            <li><a href="#none">BM Server</a></li>
            <li><a href="#none">Rack</a></li>
            <li class="active"><a href="#none">Switch</a></li>
            <li><a href="#none">Storage</a></li>
            <li><a href="#none">ETC</a></li>
            <li><a href="#none">제각</a></li>
          </ul>
        </td>
      </tr>
      <tr>
        <th>반입일</th>
        <td>
          <div class="layout row gap06">
            <div class="flex md5">
              <v-dialog
                ref="dialog"
                v-model="modal"
                :return-value.sync="date"
                persistent
                lazy
                full-width
                width="290px"
              >
                <v-text-field
                  slot="activator"
                  v-model="date"
                  prepend-icon="event"
                  readonly
                ></v-text-field>
                <v-date-picker v-model="date" scrollable>
                  <v-spacer></v-spacer>
                  <v-btn flat color="primary" @click="modal = false">Cancel</v-btn>
                  <v-btn flat color="primary" @click="$refs.dialog.save(date)">OK</v-btn>
                </v-date-picker>
              </v-dialog>
            </div>
            <div class="flex md5">
              <v-dialog
                ref="dialog"
                v-model="modal"
                :return-value.sync="date"
                persistent
                lazy
                full-width
                width="290px"
              >
                <v-text-field
                  slot="activator"
                  v-model="date"
                  prepend-icon="event"
                  readonly
                ></v-text-field>
                <v-date-picker v-model="date" scrollable>
                  <v-spacer></v-spacer>
                  <v-btn flat color="primary" @click="modal = false">Cancel</v-btn>
                  <v-btn flat color="primary" @click="$refs.dialog.save(date)">OK</v-btn>
                </v-date-picker>
              </v-dialog>
            </div>
            <div class="flex md2"><v-btn class="btn-md-cr ico"><v-icon>search</v-icon></v-btn></div>
          </div>

        </td>
      </tr>
      <tr>
        <th>수정일</th>
        <td>
          <div class="layout row wrap gap06">
            <div class="flex md5">
              <v-dialog
                ref="dialog"
                v-model="modal"
                :return-value.sync="date"
                persistent
                lazy
                full-width
                width="290px"
              >
                <v-text-field
                  slot="activator"
                  v-model="date"

                  prepend-icon="event"
                  readonly
                ></v-text-field>
                <v-date-picker v-model="date" scrollable>
                  <v-spacer></v-spacer>
                  <v-btn flat color="primary" @click="modal = false">Cancel</v-btn>
                  <v-btn flat color="primary" @click="$refs.dialog.save(date)">OK</v-btn>
                </v-date-picker>
              </v-dialog>
            </div>
            <div class="flex md5">
              <v-dialog
                ref="dialog"
                v-model="modal"
                :return-value.sync="date"
                persistent
                lazy
                full-width
                width="290px"
              >
                <v-text-field
                  slot="activator"
                  v-model="date"

                  prepend-icon="event"
                  readonly
                ></v-text-field>
                <v-date-picker v-model="date" scrollable>
                  <v-spacer></v-spacer>
                  <v-btn flat color="primary" @click="modal = false">Cancel</v-btn>
                  <v-btn flat color="primary" @click="$refs.dialog.save(date)">OK</v-btn>
                </v-date-picker>
              </v-dialog>
            </div>
            <div class="flex md2"><v-btn class="btn-md-cr ico"><v-icon>search</v-icon></v-btn></div>
          </div>

        </td>
      </tr>
      </tbody>
    </table>
    <div class="filter-selected">
      <v-btn flat class="btn-md"><v-icon>replay</v-icon>전체해제</v-btn>
      <v-btn flat class="btn-md"><v-icon class="md-18">clear</v-icon>장비용도</v-btn>
      <v-btn flat class="btn-md"><v-icon class="md-18">clear</v-icon>상태</v-btn>
      <v-btn flat class="btn-md"><v-icon class="md-18">clear</v-icon>Switch</v-btn>

    </div>
  </div>
</template>

<script>

export default {
  name: 'equipment-filter',
  created: function () {
  },
  props: {
    filterOpen: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      isOpen: this.filterOpen
    }
  },
  methods: {
  }
}
</script>
