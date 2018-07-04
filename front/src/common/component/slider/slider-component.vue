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
            <div class="container fluid fill-height" v-show="!notDetailShow" >
              <div class="layout row gap-06">
                <div class="flex md5 col-fixed500">
                  <div class="">
                    <img src="../../../asset/images/common/rack.png"/>
                  </div>
                </div>
                <div class="flex md7 col-fluid500">
                  <div class="" style="height:100%">

                    <v-tabs class="sub-tabs"
                            v-model="active"
                            color="none"
                            slider-color="none"
                    >
                      <v-tab
                        v-for="n in tabItems"
                        :key="n"
                        ripple
                      >
                        {{ n }}
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
                                <v-btn class="btn-md-cr" @click="openBasicInfo">장비기본정보</v-btn><!--
                                <v-btn class="btn-md-cr" @click="openGeneralInfo">장비일반정보</v-btn>
                                <v-btn class="btn-md-cr" @click="openOperationalInfo">장비운영정보</v-btn>-->
                              </div>
                              <div class="right"><!--
                                <v-btn class="btn-md-cr" @click.native="panelAllOpen">전체펼치기</v-btn>
                                <v-btn class="btn-md-cr" @click.native="panelAllClose">전체닫기</v-btn>-->
                              </div>
                            </div>
                            <!-- //상단버튼영역 -->
                            <!-- 아코디언 -->
                            <v-expansion-panel v-model="panelList" expand>
                              <v-expansion-panel-content v-for="(item,i) in panelItems" :key="i" value="item === 1">
                                <div slot="header">{{item}}</div>
                                <v-card ng-if="detailInfo.length>0">
                                  <v-card-text class="">
                                    아이디:{{detailInfo.id}}<br>
                                    장비명:{{detailInfo.name}}<br>
                                    장비높이:{{detailInfo.unitSize}}<br>
                                    홀번호 :{{detailInfo.holeNo}}<br>
                                    상태 :{{detailInfo.status}}<br>
                                    제조사 :{{detailInfo.manufacturer}}<br>
                                    모델 :{{detailInfo.modelNo}}<br>
                                    시리얼 번호 :{{detailInfo.serialNumber}}<br>
                                    서비스 군 :{{detailInfo.serviceGroup}}<br>
                                    서비스 명 :{{detailInfo.serviceName}}<br>
                                    장비무게(Kg) :{{detailInfo.devTotalAmount}}<br>
                                    발열량(Btu/Hr) :{{detailInfo.heatValue}}<br>
                                    전원수량(개) :{{detailInfo.powerCount}}<br>
                                    장비전원(W) :{{detailInfo.systemPower}}<br>
                                    Host명 :{{detailInfo.hostName}}<br>
                                    CPU프로세서수(CPU) :{{detailInfo.cpuCnt}}<br>
                                    CPU전체 코어수(Core) :{{detailInfo.totalCpuCoreCnt}}<br>
                                    Memory 갯수 :{{detailInfo.memCnt}}<br>
                                    Memory 용량(GB) :{{detailInfo.totalMemCapcacity}}<br>
                                    Disk 갯수 :{{detailInfo.hddCnt}}<br>
                                    Disk 용량(GB) :{{detailInfo.totalHddCapacity}}<br>
                                    서비스 대표 IP :{{detailInfo.mainIpAddr}}<br>
                                    Port IP :{{detailInfo.nicNo}}<br>
                                    OS 명 :{{detailInfo.osName}}<br>
                                    OS 버전 :{{detailInfo.osVersion}}<br>
                                    입고일 :{{detailInfo.stockEquipmentComeAt}}<br>
                                    폐기 예정일 :{{detailInfo.disposeAt}}<br>
                                    메모 :{{detailInfo.remark}}<br>
                                    CPU모델명 :{{detailInfo.specType}}<br>
                                    프로세서 당 코어 수 :{{detailInfo.coreCount}}<br>
                                    담당자 :{{detailInfo.operatorName}}<br>
                                    타입 :{{detailInfo.type}}<br>
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
      detailInfo: {},
      panelList: [true, true, true],
      panelItems: ['장비 기본정보'],
      tabItems: ['장비정보', '알람정보'],
      rackClass: 'slider-container w500',
      defaultClass: 'slider-container',
      notDetailShow: true
    }
  },
  methods: {
    panelAllOpen () {
      this.panelList = []
      this.panelList = [true, true, true]
    },
    panelAllClose () {
      this.panelList = [false, false, false]
    },
    showDetailInfo (data) {
      this.notDetailShow = false
    },
    closeDialog () {
      this.dialog = false
      this.notDetailShow = true
      this.detailInfo = {}
    },
    setPanelData: function (location, bool) {
      var tempAr = []

      for (let i = 0; i < this.panelList.length; i++) {
        if (i === location) { tempAr.push(bool) } else { tempAr.push(this.panelList[i]) }
      }
      this.panelList = tempAr
    },
    openBasicInfo (location, bool) {
      this.setPanelData(0, true)
    },
    openGeneralInfo () {
      this.setPanelData(1, true)
    },
    openOperationalInfo () {
      this.setPanelData(2, true)
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
