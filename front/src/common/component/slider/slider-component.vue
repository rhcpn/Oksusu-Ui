<template>
    <v-dialog
      v-model="dialog"
      hide-overlay
      transition="dialog-right-transition"
      scrollable
    >
      <!-- 우측 slider-container -->
      <div v-bind:class="[notDetailShow ? rackClass : defaultClass]">
        <div class="panel-wrap">
          <div class="panel-header">
            <h3 class="header-title left">장비이름(000.00.0.00)</h3>
            <ul class="breadcrumbs left">
              <li><a href="#none">판교</a></li>
              <li><a href="#none">2층</a></li>
              <li><a href="#none">2층 시스템실</a></li>
              <li><a href="#none">X,Y</a></li>
            </ul>
            <div class="right"><v-btn flat icon class="ico-sm" @click.native="closeDialog()"><v-icon class="md-18">close</v-icon></v-btn></div>
          </div>
          <div class="panel-body" v-on:click="showDetailInfo()">
            <img  v-if="notDetailShow" src="../../../asset/images/common/rack.png"/>
            <div class="container fluid fill-height" v-if="!notDetailShow" >
              <div class="layout row gap-06">
                <div class="flex md5">
                  <div class="" style="border:1px solid red;height:100%">
                    <img src="../../../asset/images/common/rack.png"/>
                  </div>
                </div>
                <div class="flex md7">
                  <div class="" style="height:100%">

                    <v-tabs class="sub-tabs"
                            v-model="active"
                            color="none"
                            slider-color="none"
                    >
                      <v-tab
                        v-for="n in 2"
                        :key="n"
                        ripple
                      >
                        Item {{ n }}
                      </v-tab>
                      <v-tab-item
                        v-for="n in 1"
                        :key="n"
                      >
                        <v-card flat>
                          <v-card-text>
                            <!-- 상단버튼영역 -->
                            <div class="btns-area">
                              <div class="left">
                                <v-btn class="btn-md-cr">장비기본정보</v-btn>
                                <v-btn class="btn-md-cr">장비일반정보</v-btn>
                                <v-btn class="btn-md-cr">장비운영정보</v-btn>
                              </div>
                              <div class="right">
                                <v-btn class="btn-md-cr" @click.native="panelAllOpen()">전체펼치기</v-btn>
                                <v-btn class="btn-md-cr" @click.native="panelAllClose()">전체닫기</v-btn>
                              </div>
                            </div>
                            <!-- //상단버튼영역 -->
                            <!-- 아코디언 -->
                            <v-expansion-panel v-model="panelList" expand>
                              <v-expansion-panel-content v-for="(item,i) in items" :key="i">
                                <div slot="header">Item</div>
                                <v-card>
                                  <v-card-text class="">테스트임 <br>테스트임<br>테스트임<br>테스트임<br>테스트임<br>테스트임
                                  {{item}}
                                  </v-card-text>
                                </v-card>
                              </v-expansion-panel-content>
                            </v-expansion-panel>
                            <!-- //아코디언 -->
                          </v-card-text>
                        </v-card>
                      </v-tab-item>
                    </v-tabs>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- //slider-container -->
    </v-dialog>
</template>

<script>
export default {
  name: 'slider',
  props: {
    source: String
  },
  data () {
    return {
      dialog: false,
      active: null,
      detailInfo: null,
      panelList: [true, true, true, true, true],
      items: 5,
      rackClass: 'slider-container w500',
      defaultClass: 'slider-container',
      notDetailShow: true
    }
  },
  methods: {
    panelAllOpen () {
      this.panelList = []
      this.panelList = [true, true, true, true, true];
    },
    panelAllClose () {
      this.panelList = [false, false, false, false, false]
    },
    showDetailInfo (data) {
      this.notDetailShow = false
    },
    closeDialog () {
      this.dialog = false
      this.notDetailShow = true
      this.detailInfo = null
    }
  },
  created () {
    this.$eventHub.$on('slider-open', () => {
      this.dialog = true
    })
    this.$eventHub.$on('slider-change-data', (data) => {
      this.detailInfo = data
    })
  }
}
</script>

<style scoped>

</style>
