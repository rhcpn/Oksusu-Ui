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
                <div class="flex md7 col-fluid500" style="overflow-y:scroll" id="scroll">
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
                                <v-btn class="btn-md-cr" @click="openPanelAndScrollMove('default',0)">기본 정보</v-btn>
                                <v-btn class="btn-md-cr" @click="openPanelAndScrollMove('normal',1)">일반 정보</v-btn>
                                <v-btn class="btn-md-cr" @click="openPanelAndScrollMove('configuration',2)">구성 정보</v-btn>
                                <v-btn class="btn-md-cr" @click="openPanelAndScrollMove('maintenance',3)">유지보수 정보</v-btn>
                                <v-btn class="btn-md-cr" @click="openPanelAndScrollMove('manager',4)">담당자 정보</v-btn>
                              </div>
                              <div class="right">
                                <v-btn class="btn-md-cr" @click.native="panelAllOpen">전체펼치기</v-btn>
                                <v-btn class="btn-md-cr" @click.native="panelAllClose">전체닫기</v-btn>
                              </div>
                            </div>
                            <!-- //상단버튼영역 -->
                            <!-- 아코디언 -->
                            <div>
                              <v-expansion-panel expand >
                                <v-expansion-panel-content v-model="panelList[0]" id="defaultPanel">
                                  <div slot="header">{{panelItems[0]}}</div>
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
                                <v-expansion-panel-content v-model="panelList[1]" id="normalPanel">
                                  <div slot="header">{{panelItems[1]}}</div>
                                  <v-card>
                                    <v-card-text class="">
                                      테스트임 <br>테스트임<br>테스트임<br>테스트임<br>테스트임<br>테스트임
                                    </v-card-text>
                                  </v-card>
                                </v-expansion-panel-content>
                                <v-expansion-panel-content v-model="panelList[2]" id="configurationPanel">
                                  <div slot="header">{{panelItems[2]}}</div>
                                  <v-card>
                                    <v-card-text class="">
                                      테스트임 <br>테스트임<br>테스트임<br>테스트임<br>테스트임<br>테스트임
                                    </v-card-text>
                                  </v-card>
                                </v-expansion-panel-content>
                                <v-expansion-panel-content v-model="panelList[3]" id="maintenancePanel">
                                  <div slot="header">{{panelItems[3]}}</div>
                                  <v-card>
                                    <v-card-text class="">
                                      테스트임 <br>테스트임<br>테스트임<br>테스트임<br>테스트임<br>테스트임
                                    </v-card-text>
                                  </v-card>
                                </v-expansion-panel-content>
                                <v-expansion-panel-content v-model="panelList[4]" id="managerPanel">
                                  <div slot="header">{{panelItems[4]}}</div>
                                  <v-card>
                                    <v-card-text class="">
                                      테스트임 <br>테스트임<br>테스트임<br>테스트임<br>테스트임<br>테스트임
                                    </v-card-text>
                                  </v-card>
                                </v-expansion-panel-content>
                              </v-expansion-panel>
                              <!-- //아코디언 -->
                            </div>

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
      panelList: [true, true, true, true, true],
      panelItems: ['기본 정보', ' 일반 정보', '구성 정보', '유지보수 정보', '담당자 정보'],
      tabItems: ['장비정보', '알람정보'],
      rackClass: 'slider-container w500',
      defaultClass: 'slider-container',
      notDetailShow: true
    }
  },
  methods: {
    panelAllOpen () {
      this.panelList = [...Array(this.panelItems.length).keys()].map(_ => true)
    },
    panelAllClose () {
      this.panelList = []
    },
    showDetailInfo (data) {
      this.notDetailShow = false
    },
    closeDialog () {
      this.dialog = false
      this.notDetailShow = true
      this.detailInfo = {}
    },
    openPanelAndScrollMove (panelType, index) {
      this.panelList[index] = true
      this.$forceUpdate()
      var panel = $('#' + panelType + 'Panel')
      var topPositon = panel.offset().top + panel.outerHeight()

      setTimeout(function () {
        $('#scroll').scrollTop(topPositon)
      }, 300)
    }
  },
  created () {
    this.$eventHub.$on('slider-open', () => {
      this.dialog = true
    })
    this.$eventHub.$on('slider-change-data', (data) => {
      this.detailInfo = data
    })
    this.$eventHub.$on('slider-detail-show', () => {
      this.notDetailShow = false
    })
  }
}
</script>

<style scoped>

</style>
