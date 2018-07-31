
<template>
  <ul class="wrapper">
    <!-- left area -->
    <li class="left-area section-wrap">
      <div class="section-title-area">
        <p class="section-title">사용자 로그 리스트</p>
        <select id="selectUserId" class="selectbox" style="float: right;" v-model="userSvnId" v-on:change="onUserSvnIdChange">
          <option value="6AC427B19A7A2B4C93DC6D335385D774">okservicetest01 시청 이력</option>
          <option value="FBC04B0DAC1B314F529B4BEC1CB6F123">okservicetest02 시청 이력</option>
          <option value="A5B30B8B64DE5AB9F388691127AF8920">okservicetest03 시청 이력</option>
          <option value="48A8A81C0D17C674300CDF86B084D1AD">okservicetest04 시청 이력</option>
          <option value="0D1FD1856007D5CF78A0C21EE1095680">okservicetest05 시청 이력</option>
          <option value="2DE61FD16FC75F476773BCE98524CD91">calmjjung 시청 이력</option>
          <option value="93D3B78055C3348D819C75A18AD55041">donnylee 시청 이력</option>
          <option value="772CB8F46A7D4BAE82B1C92917C855DE">jkraid 시청 이력</option>
          <option value="ED8E13E6ECC1563840FB98B57603BBF6">kevinesque 시청 이력</option>
          <option value="2BCECFBD3629D1658E3EF7CFBAB68EAD">lovelypdy 시청 이력</option>
          <!--<option value="706C9E7CEB86ACD1AD8A3803419CDF31">medialab10 시청 이력</option>-->
          <option value="5BCF5069A2A9C14945D1B0FECDE64DC5">pharoah 시청 이력</option>
          <option value="9E207BC911131E11F54735BAFAC1C192">rainism 시청 이력</option>
          <option value="6686086AE2A2BF7C6AB360DB7E4FD6A3">unjoa 시청 이력</option>
          <!--<option value="7C5D5ADE2EA710979894322B0AAF31DB">medialab01 시청 이력</option>-->
          <option value="45F951D7A3D13ED7D49269482C626A06">medialab02 시청 이력</option>
          <option value="4D5547799C99664E956485DEB0DECB60">medialab03 시청 이력</option>
          <option value="F0FC2C893553887627170A3F5E4E23C9">medialab04 시청 이력</option>
          <option value="C636BAAEB494A405B9F6D2CE6E2C6803">medialab05 시청 이력</option>
          <!--<option value="46E4204FE9CBBA2BF2E7783BDEE70B37">medialab06 시청 이력</option>-->
          <option value="0151C83E78DF15575A26718A2B50696A">medialab07 시청 이력</option>
        </select>
      </div>
      <ul class="list-wrap">
        <li class="list-box" v-for="item of list" :key="item.id" :data="item">
          <p class="thumb-img">
            <img v-bind:src="item.imgSrc" :title="item.title">
          </p>
          <dl class="mt4">
            <dt v-bind:class="getIconClass(item.videoType)"><span>{{item.title}}</span></dt>
            <dd v-if="item.videoType === '2'"><em>개봉일</em>{{item.ddShowing}}</dd>
            <dd v-else><em>방영일</em>{{item.dtTelevise}}</dd>
            <dd><em>시청일</em>{{item.watchDate}}</dd>
            <dd><em>시청시간</em>{{item.watchTime}}</dd>
          </dl>
        </li>
        <li class="list-box" v-show="watchVideoYn">
          시청 이력이 없습니다.
        </li>
      </ul>
    </li>
    <!-- //left area -->

    <!-- center area -->
    <li class="center-area section-wrap">
      <div class="section-title-area">
        <p class="section-title">추천 결과 리스트</p>
        <div class="section-right">{{this.userId}}을 위한 추천</div>
      </div>
      <ul class="list-wrap">
        <li class="list-box" v-for="item of RecommendationList" :key="item.id" :data="item">
          <p class="thumb-img"><img v-bind:src="item.imgSrc" :title="item.title"></p>
          <dl class="mt4">
            <dt v-bind:class="getIconClass(item.videoType)"><span>{{item.title}}</span></dt>
            <dd v-if="item.videoType === '2'"><em>개봉일</em>{{item.ddShowing}}</dd>
            <dd v-else><em>방영일</em>{{item.dtTelevise}}</dd>
            <!--<dd><em>추천 score</em>&nbsp;{{item.score}}</dd>-->
          </dl>
        </li>
        <li class="list-box" v-show="recommendationVideoYn">
          추천 결과가 없습니다.
        </li>
      </ul>
    </li>
    <!-- //center area -->

    <!-- right area -->
    <li class="right-area section-mobile-wrap">
      <div class="mobile-area">
        <div class="mobile-inner-box">
          <div class="mobile-inner-top">
            <img src="../../../src/asset/images/mobile_inner_top.png">
          </div>
          <iscroll-view ref="iscroll" class="mu-scroller-wrap mu-scroller" style="max-height:575px" :options="{scrollbars: true, mouseWheel: true, interactiveScrollbars: true, shrinkScrollbars: 'scale', fadeScrollbars: false}">
            <div class="mobile-inner-area">
              <p class="mobile-inner-menu"><img src="../../../src/asset/images/mobile_inner_menu.png"></p>
              <div class="mobile-inner-list">
                <div class="section-title-area">
                  <p class="section-title">추천 결과 리스트</p>
                  <div class="section-right">{{this.userId}}을 위한 추천</div>
                </div>
                <ul class="list-wrap">
                  <li class="list-box" v-for="item of PhoneList" :key="item.id" :data="item">
                    <p class="thumb-img"><img v-bind:src="item.imgSrc" :title="item.title"></p>
                    <dl class="mt4">
                      <dt v-bind:class="getIconClass(item.videoType)"><span>{{item.title}}</span></dt>
                      <dd v-if="item.videoType === '2'"><em>개봉일</em>{{item.ddShowing}}</dd>
                      <dd v-else><em>방영일</em>{{item.dtTelevise}}</dd>
                      <!--<dd><em>추천 score</em>&nbsp;{{item.score}}</dd>-->
                    </dl>
                  </li>
                  <li class="list-box" v-show="recommendationVideoYn">
                    추천 결과가 없습니다.
                  </li>
                </ul>
              </div>
              <div class="mobile-inner-footer">
                <img src="../../../src/asset/images/mobile_inner_footer.png">
              </div>
            </div>
            <!-- </div> -->
          </iscroll-view>
        </div>
      </div>
    </li>
    <!-- //right area -->
  </ul>
</template>

<script>
let urlImg
let urlPhoneImg

export default {
  name: 'simple-oksusu',
  methods: {
    onUserSvnIdChange: function () {
      console.log(this.userSvnId)
      let tempUserId
      let tempUserIdArray = []
      tempUserId = $('#selectUserId option:selected').text()
      tempUserIdArray = tempUserId.split(' ')

      console.log('userId= ' + tempUserIdArray[0])

      this.userId = tempUserIdArray[0]
      this.getWatchVideoList(this.userSvnId)
      this.getRecommendationVideoList(this.userSvnId)
    },
    getWatchVideoList: function (param) {
      let result = []

      this.$http.get('/oksusu/getWatchVideoList.json',
        {
          params: {
            userSvnId: param
          }
        }).then(response => {
        result = response.data.data.list

        if (result.length === 0) {
          this.watchVideoYn = true
        } else {
          this.watchVideoYn = false
        }

        var promises = []
        for (var i = 0; i < result.length; i++) {
          var p = this.$http.get('http://seg.oksusu.com:8080/seg/index.php?m=getContent&IF=IF-NSMXPG-003&ver=2.0&response_format=json&con_id=' + result[i].videoSvcId).then(response => {
            let url = response.data.content.thum_path
            let tempUrl = response.data.content.pstr_path
            console.log('url= ' + url)

            if (url !== null) {
              urlImg = url.replace(':type', '173_96')
            } else {
              urlImg = tempUrl.replace(':type', '173_96')
            }
            console.log('imageUrl= ' + urlImg)

            var r = {key: response.data.content.con_id, url: urlImg}
            return r
          })
          promises.push(p)
        }

        var self = this
        Promise.all(promises).then(function (values) {
          console.log('urls=' + values)
          for (var i = 0; i < result.length; i++) {
            for (var j = 0; j < values.length; j++) {
              if (result[i].videoSvcId === values[j].key) {
                result[i].imgSrc = values[j].url
                break
              }
            }
          }
          self.list = result
        })
      })
    },
    getIconClass: function (videoType) {
      let returnVal
      if (videoType === '2') {
        returnVal = 'icon-movie'
      } else if (videoType === '3') {
        returnVal = 'icon-vod'
      } else {
        returnVal = 'icon-clip'
      }
      return returnVal
    },
    getRecommendationVideoList: function (param) {
      let resultList = []
      let resultPhoneList = []

      this.$http.get('/oksusu/getRecommendationVideoList.json',
        {
          params: {
            userSvnId: param
          }
        }).then(response => {
        resultList = response.data.data.list
        resultPhoneList = response.data.data.list

        if (resultList.length === 0) {
          this.recommendationVideoYn = true
        } else {
          this.recommendationVideoYn = false
        }

        var promises = []
        for (var i = 0; i < resultList.length; i++) {
          var p = this.$http.get('http://seg.oksusu.com:8080/seg/index.php?m=getContent&IF=IF-NSMXPG-003&ver=2.0&response_format=json&con_id=' + resultList[i].videoSvcId).then(response => {
            let url = response.data.content.thum_path
            let tempUrl = response.data.content.pstr_path
            console.log('url= ' + url)

            if (url !== null) {
              urlImg = url.replace(':type', '173_96')
              urlPhoneImg = url.replace(':type', '368_205')
            } else {
              urlImg = tempUrl.replace(':type', '173_96')
              urlPhoneImg = tempUrl.replace(':type', '368_205')
            }
            console.log('urlImg= ' + urlImg)
            console.log('urlPhoneImg= ' + urlPhoneImg)

            var list = {key: response.data.content.con_id, url: urlImg}
            var phonelist = {key: response.data.content.con_id, url: urlPhoneImg}
            return {list, phonelist}
          })
          promises.push(p)
        }

        var self = this
        Promise.all(promises).then(function (values) {
          for (var i = 0; i < resultList.length; i++) {
            for (var j = 0; j < values.length; j++) {
              if (resultList[i].videoSvcId === values[j].list.key) {
                resultList[i].imgSrc = values[j].list.url
                resultPhoneList[i].imgSrc = values[j].phonelist.url
                break
              }
            }
          }
          self.scrollInit()
          self.RecommendationList = resultList
          self.PhoneList = resultPhoneList
        })
      })
        .catch(e => {
          this.errors.push(e)
        })
    },
    scrollInit: function () {
      $('.list-wrap').scrollTop(0)

      const iscroll = this.$refs.iscroll
      setTimeout(function () {
        iscroll.refresh()
        iscroll.scrollTo(0, 0, 100) // 핸드폰 scroll 위치 초기화
      }, 300)
    }
  },
  data: function () {
    return {
      userSvnId: '6AC427B19A7A2B4C93DC6D335385D774',
      userId: 'okservicetest01',
      list: [], // 시청 영상 리스트
      RecommendationList: [], // 추천 영상 리스트
      PhoneList: [], // 추천 영상 리스트(핸드폰 용)
      errors: [],
      watchVideoYn: false,
      recommendationVideoYn: false
    }
  },
  mounted () {
    this.getWatchVideoList(this.userSvnId)
    this.getRecommendationVideoList(this.userSvnId)
  }
}
</script>
