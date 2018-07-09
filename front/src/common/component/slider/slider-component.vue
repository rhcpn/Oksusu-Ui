<template>
    <v-dialog
      v-model="dialog"
      hide-overlay
      transition="dialog-right-transition"
      scrollable
      class="dialog-rack"
      v-bind:class="[notDetailShow ? rackClass : defaultClass]"
    >
      <!-- 우측 slider-container -->
      <div class="slider-container" style="position:absolute; top:6px; right:0; bottom:6px; width:100%; box-shadow:0 2px 26px 5px rgba(0,0,0,.10); -webkit-transition:.2s cubic-bezier(.4,0,.2,1); transition:.2s cubic-bezier(.4,0,.2,1); z-index:2;">
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
            <img  v-if="notDetailShow" src="../../../asset/images/common/rack.png">
            <div class="container fluid fill-height" v-show="!notDetailShow" >
              <div class="layout row gap-06">
                <div class="flex md5 col-fixed500">
                  <div class="">
                    <img src="../../../asset/images/common/rack.png" alt="" style="width:478px;height:820px">
                  </div>
                </div>
                <div class="flex md7 col-fluid500">

                    <v-tabs class="sub-tabs" style="height:100%"
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
                        <v-card>
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

                            <div class="scroll-y p100 hfluid50" id="scroll">
                            <!-- 아코디언 -->
                              <v-expansion-panel expand >
                                <v-expansion-panel-content v-model="panelList[0]" id="defaultPanel">
                                  <div slot="header">{{panelItems[0]}}</div>
                                  <v-card ng-if="detailInfo.length>0">
                                    <v-card-text>
                                      <!-- data-tbl -->
                                      <div class="data-tbl border-type">
                                        <table>
                                          <colgroup>
                                            <col style="width:15%">
                                            <col style="width:auto">
                                            <col style="width:15%">
                                            <col style="width:auto">
                                            <col style="width:15%">
                                            <col style="width:auto">
                                          </colgroup>
                                          <tbody>
                                          <tr>
                                            <th>아이디</th>
                                            <td>{{detailInfo.id}}</td>
                                            <th>장비명</th>
                                            <td>{{detailInfo.name}}</td>
                                            <th>장비높이</th>
                                            <td>{{detailInfo.unitSize}}</td>
                                          </tr>
                                          <tr>
                                            <th>홀번호</th>
                                            <td>{{detailInfo.holeNo}}</td>
                                            <th>상태</th>
                                            <td>{{detailInfo.status}}</td>
                                            <th>제조사</th>
                                            <td>{{detailInfo.manufacturer}}</td>
                                          </tr>
                                          <tr>
                                            <th>모델</th>
                                            <td>{{detailInfo.modelNo}}</td>
                                            <th>시리얼 번호</th>
                                            <td>{{detailInfo.serialNumber}}</td>
                                            <th>서비스 군</th>
                                            <td>{{detailInfo.serviceGroup}}</td>
                                          </tr>
                                          <tr>
                                            <th>서비스 명</th>
                                            <td>{{detailInfo.serviceName}}</td>
                                            <th>장비무게(Kg)</th>
                                            <td>{{detailInfo.devTotalAmount}}</td>
                                            <th>발열량(Btu/Hr)</th>
                                            <td>{{detailInfo.heatValue}}</td>
                                          </tr>
                                          <tr>
                                            <th>전원수량(개)</th>
                                            <td>{{detailInfo.powerCount}}</td>
                                            <th>장비전원(W)</th>
                                            <td>{{detailInfo.systemPower}}</td>
                                            <th>Host명</th>
                                            <td>{{detailInfo.hostName}}</td>
                                          </tr>
                                          <tr>
                                            <th>CPU프로세서수(CPU)</th>
                                            <td>{{detailInfo.cpuCnt}}</td>
                                            <th>CPU전체 코어수(Core)</th>
                                            <td>{{detailInfo.totalCpuCoreCnt}}</td>
                                            <th>Memory 갯수</th>
                                            <td>{{detailInfo.memCnt}}</td>
                                          </tr>
                                          <tr>
                                            <th>Memory 용량(GB)</th>
                                            <td>{{detailInfo.totalMemCapcacity}}</td>
                                            <th>Disk 갯수</th>
                                            <td>{{detailInfo.hddCnt}}</td>
                                            <th>Disk 용량(GB)</th>
                                            <td>{{detailInfo.totalHddCapacity}}</td>
                                          </tr>
                                          <tr>
                                            <th>서비스 대표 IP</th>
                                            <td>{{detailInfo.mainIpAddr}}</td>
                                            <th>Port IP</th>
                                            <td>{{detailInfo.nicNo}}</td>
                                            <th>OS 명</th>
                                            <td>{{detailInfo.osName}}</td>
                                          </tr>
                                          <tr>
                                            <th>OS 버전</th>
                                            <td>{{detailInfo.osVersion}}</td>
                                            <th>입고일</th>
                                            <td>{{detailInfo.stockEquipmentComeAt}}</td>
                                            <th>폐기 예정일</th>
                                            <td>{{detailInfo.disposeAt}}</td>
                                          </tr>
                                          <tr>
                                            <th>CPU모델명</th>
                                            <td>{{detailInfo.specType}}</td>
                                            <th>프로세서 당 코어 수</th>
                                            <td>{{detailInfo.coreCount}}</td>
                                            <th>담당자</th>
                                            <td>{{detailInfo.operatorName}}</td>
                                          </tr>
                                          <tr>
                                            <th>타입</th>
                                            <td>{{detailInfo.type}}</td>
                                            <th>메모</th>
                                            <td colspan="3">{{detailInfo.remark}}</td>
                                          </tr>
                                          </tbody>
                                        </table>
                                      </div>
                                      <!-- //data-tbl -->
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
      rackClass: 'size-sm',
      defaultClass: '',
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
      var topPositon = panel.offset().top

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
