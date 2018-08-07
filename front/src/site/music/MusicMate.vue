<template>
  <ul class="wrapper">
    <!-- left area -->
    <li class="left-area section-wrap">
      <div class="section-title-area">
        <p class="section-title">청취 이력 리스트</p>
        <select id="selectUserId" class="selectbox" style="float: right;" v-model="userSvnId" v-on:change="onUserSvnIdChange">
          <option value="6AC427B19A7A2B4C93DC6D335385D774">okservicetest01 청취 이력</option>
          <option value="FBC04B0DAC1B314F529B4BEC1CB6F123">okservicetest02 청취 이력</option>
          <option value="A5B30B8B64DE5AB9F388691127AF8920">okservicetest03 청취 이력</option>
          <option value="48A8A81C0D17C674300CDF86B084D1AD">okservicetest04 청취 이력</option>
          <option value="0D1FD1856007D5CF78A0C21EE1095680">okservicetest05 청취 이력</option>
          <option value="2DE61FD16FC75F476773BCE98524CD91">calmjjung 청취 이력</option>
          <option value="93D3B78055C3348D819C75A18AD55041">donnylee 청취 이력</option>
          <option value="772CB8F46A7D4BAE82B1C92917C855DE">jkraid 청취 이력</option>
          <option value="ED8E13E6ECC1563840FB98B57603BBF6">kevinesque 청취 이력</option>
          <option value="2BCECFBD3629D1658E3EF7CFBAB68EAD">lovelypdy 청취 이력</option>
          <!--<option value="706C9E7CEB86ACD1AD8A3803419CDF31">medialab10 청취 이력</option>-->
          <option value="5BCF5069A2A9C14945D1B0FECDE64DC5">pharoah 청취 이력</option>
          <option value="9E207BC911131E11F54735BAFAC1C192">rainism 청취 이력</option>
          <option value="6686086AE2A2BF7C6AB360DB7E4FD6A3">unjoa 청취 이력</option>
          <!--<option value="7C5D5ADE2EA710979894322B0AAF31DB">medialab01 청취 이력</option>-->
          <option value="45F951D7A3D13ED7D49269482C626A06">medialab02 청취 이력</option>
          <option value="4D5547799C99664E956485DEB0DECB60">medialab03 청취 이력</option>
          <option value="F0FC2C893553887627170A3F5E4E23C9">medialab04 청취 이력</option>
          <option value="C636BAAEB494A405B9F6D2CE6E2C6803">medialab05 청취 이력</option>
          <!--<option value="46E4204FE9CBBA2BF2E7783BDEE70B37">medialab06 청취 이력</option>-->
          <option value="0151C83E78DF15575A26718A2B50696A">medialab07 청취 이력</option>
        </select>
      </div>
      <ul class="list-wrap">
        <!--<li class="list-box" v-for="item of list" :key="item.id" :data="item">
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
          청취 이력이 없습니다.
        </li>-->

        <li class="list-box">
          <dt class="icon-movie"><span><button @click="musicPlayClick('5315682')">SoluMate</button></span></dt>
          <dt class="icon-movie"><span><button @click="musicPlayClick('5262784')">Way Back Home</button></span></dt>
        </li>
      </ul>
    </li>
    <!-- //left area -->

    <iframe :src="musicPlayUrl" width="500px" height="900px" allow="autoplay"/>
  </ul>
</template>

<script>
let count = 0
export default {
  name: 'music-mate',
  methods: {
    onUserSvnIdChange: function () {
      let tempUserId
      let tempUserIdArray = []
      tempUserId = $('#selectUserId option:selected').text()
      tempUserIdArray = tempUserId.split(' ')

      console.log('userId= ' + tempUserIdArray[0])

      this.userId = tempUserIdArray[0]
    },
    musicPlayClick: function (idParam) {
      if (idParam !== null) {
        count++
        this.musicPlayUrl = 'https://music.bugs.co.kr/newPlayer?trackId=' + idParam + '&autoplay=true&html5=true&' + count
      } else {
        this.musicPlayUrl = 'https://music.bugs.co.kr/newPlayer?&autoplay=false&html5=true'
      }
    }
  },
  data: function () {
    return {
      userSvnId: '6AC427B19A7A2B4C93DC6D335385D774',
      userId: 'okservicetest01',
      musicId: '',
      musicPlayUrl: 'https://music.bugs.co.kr/newPlayer?&autoplay=false&html5=true',
      list: [], // 시청 영상 리스트
      RecommendationList: [], // 추천 영상 리스트
      PhoneList: [], // 추천 영상 리스트(핸드폰 용)
      errors: [],
      watchVideoYn: false,
      recommendationVideoYn: false
    }
  }
}
</script>
