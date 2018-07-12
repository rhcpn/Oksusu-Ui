<template>
  <div id="login-wrap">
    <div id="login-container">
      <h1>SK Telecom</h1>

      <div class="visual-area">
        <ul class="slider-number">
          <li><a href="#" class="number1">Datacenter Management with Scalable Architecture</a></li>
          <li><a href="#" class="number2 on">Resources Management</a></li>
          <li><a href="#" class="number3">On-demand OS &amp; Software Provisioning</a></li>
          <li><a href="#" class="number4">Realtime Analytics for Log &amp; Metrics</a></li>
          <li><a href="#" class="number5">Realtime Monitoring &amp; Anomaly Detection</a></li>
          <li><a href="#" class="number6">Openstack Cluster Management</a></li>
          <li><a href="#" class="number7">Operator Friendly</a></li>
        </ul>
        <div class="slider-view">
          <ul class="slider-list" style="width: 1792px; left: -256px;">
            <li class="slider-mv1">
              <strong>Datacenter Management with Scalable Architecture</strong>
              <p>- Scale-out구조에 기반, 데이터센터 규모 및 관리대상에 따른 용이한 확장 <br>- 다수 데이터센터의 통합적 관리</p>
            </li>
            <li class="slider-mv2">
              <strong>Resources Management</strong>
              <p>- 물리 및 가상자원의 통합된 관리 <br>- 다양한 종류의 Hardware와 Software 자원의 등록과 관리 <br>- 운영중인 서비스 관점의 자원그룹을 통한 관리<br></p>
            </li>
            <li class="slider-mv3">
              <strong>On-demand OS &amp; Software Provisioning</strong>
              <p>- OS와 Software의 용이한 자동 설치 <br>- 프로세스에 기반한 작업의 신청, 수행 및 이력관리 <br>- 원격장비에 대한 명령 및 script 수행</p>
            </li>
            <li class="slider-mv4">
              <strong>Realtime Analytics for Log &amp; Metric</strong>
              <p>- Log와 Metric의 실시간 분석 및 통합 모니터링 <br>- 실시간 조건검색 및 Alarm 발생</p>
            </li>
            <li class="slider-mv5">
              <strong>Realtime Monitoring and Anomaly Detection</strong>
              <p>- 종합적 Health Monitoring 및 자원간 Metric Trend 실시간 비교 분석 <br> - Machine Learning 기반 Anomaly Detection</p>
            </li>
            <li class="slider-mv6">
              <strong>Openstack Cluster Management</strong>
              <p>- 다수 Openstack 클러스터의 통합적 관리 <br>- Openstack VM의 편리한 생성 및 관리</p>
            </li>
            <li class="slider-mv7">
              <strong>Operator Friendly</strong>
              <p>- 운영자의 작업 흐름 및 사용 패턴을 고려한 UX <br>- Drag &amp; Drop에 기반한 그래프 통합 및 연계분석</p>
            </li>
          </ul>
        </div>
      </div>
      <div class="login-area">

        <v-form @submit.prevent="onSubmit(id, password)">
          <legend>T-CORE Login</legend>
          <h2>T-CORE Rackscale Operation Intelligence</h2>
          <v-text-field prepend-icon="person" name="login" label="" type="text" class="login-input" v-model="id"></v-text-field>
          <v-text-field prepend-icon="lock" name="password" label="" id="password" type="password"
                        class="login-input" v-model="password"></v-text-field>
        </v-form>

        <div class="login-btns">
          <v-btn class="login" @click.prevent="onSubmit">Login</v-btn>
        </div>

        <p class="login-info">본 솔루션은 <em>Chrome Browser</em>에서 사용하실 수 있습니다.<br>해상도 <em>1920*1024</em>에 최적화 되어 있습니다.</p>
        <!-- 2016-01-22 ADD: 로그인 안내문구/ 2016-02-02 MOV -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      id: '',
      password: '',
      msg: ''
    }
  },
  methods: {
    onSubmit (email, password) {
      // TODO. login check 하는 로직 추가
      if (!this.id) {
        this.id = 'admin'
      }
      this.$store.dispatch('loginByUsername', { 'username': this.id, 'password': this.password }).then(() => {
        this.$router.push({ path: '/' })
      }).catch(e => {
        console.log(e)
      })
      // window.isAuthenticated = true
      // this.redirect()
    },
    redirect () {
      // let returnPath = this.$route.query.redirect || '/'
      // this.$router.push(returnPath)
    }
  }
}
</script>

<style scoped>

</style>
