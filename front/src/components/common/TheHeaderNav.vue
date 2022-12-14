<template>
  <header ref="header">
    <div class="header-wrapper">
      <!-- <div class="main-logo"> -->
      <router-link
        class="main-logo"
        :to="{ name: 'MainPageView' }"
        style="color: white; font-size: 40px"
        >SSAFIT</router-link
      >
      <!-- </div> -->
      <nav style="position: left">
        <ul class="main-menu" v-if="isLogin">
          <li>{{ myUser.name }}님 안녕하세요!</li>
          <li>
            <router-link
              :to="{
                name: 'UserViewMypageMain',
                params: {
                  id: myUser.u_id,
                },
              }"
            >
              내 정보
            </router-link>
          </li>
          <li>
            <router-link :to="{ name: 'VideoViewSearch' }">
              온라인 PT
            </router-link>
          </li>
          <li>
            <router-link :to="{ name: 'TrainerViewMain' }"> 코치 </router-link>
          </li>
          <li @click="logout"><a>로그아웃</a></li>
        </ul>
        <ul class="main-menu" v-else>
          <li>
            <router-link :to="{ name: 'VideoViewSearch' }">
              온라인 PT
            </router-link>
          </li>
          <li>
            <router-link :to="{ name: 'TrainerViewMain' }"> 코치 </router-link>
          </li>
          <li @click="$emit('show-modal', 'LOGIN')"><a>로그인</a></li>
          <li @click="$emit('show-modal', 'JOIN')"><a>회원가입</a></li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script scoped>
import { mapState } from "vuex";

export default {
  name: "TheHeaderNav",
  data() {
    return {
      checkId: this.isLogin,
    };
  },
  methods: {
    logout() {
      this.$store.commit("CLEAR_ALL");
      // JWT 토큰 지우기
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("myId");
      this.$store.commit("CHECK_LOGIN");
      this.$router.push({ name: "MainPageView" }).catch((err) => {
        console.log(err);
      });
    },
  },
  computed: {
    ...mapState(["myUser", "isLogin"]),
    // isLogin() {
    //   return localStorage.getItem("access-token");
    // },
  },
  watch: {},
};
</script>

<style scoped>
/* Main Header Styles */
.header-wrapper {
  background-color: #0a0a0a;
  height: 100px;
  width: 1000px;
}
.main-header {
  position: absolute;
  top: 0;
  width: 100%;
  z-index: 1000;
  font-family: Montserrat, sans-serif;
  color: #fff;
  background: transparent;
}

.main-header .header-wrapper {
  display: block;
  width: 100%;

  margin: 0 auto;
  /* padding-top: 1em; */
}
.main-header .header-wrapper .main-logo {
  /* float: left; */
  margin-left: 4em;
  font-weight: 700;
  font-size: 1.875em;
}
.main-header .header-wrapper .main-menu {
  float: right;
  margin-right: 17em;
  text-align: center;
}
.main-header .header-wrapper .main-menu li {
  display: inline-block;
  padding: 0.75em 1.875em;
}
.main-header .header-wrapper .main-menu li a {
  padding-bottom: 0.75em;
  text-transform: uppercase;
  color: #fff;

  font-size: 0.9em;
  cursor: pointer;
}
.main-header .header-wrapper .main-menu li a:hover {
  color: #fce38a;
  border-bottom: 0.1875em solid #fce38a;
  transition: 0.1s;
}
.main-header .header-wrapper .main-menu li a span {
  font-size: 1.2em;
}
.main-header .header-wrapper .main-menu li:last-child {
  padding: 0.75em 0;
}
/* .main-header .header-wrapper .main-menu li:nth-child(6) a:hover,
.main-header .header-wrapper .main-menu li:last-child a:hover {
  border: none;
} */

.custom-header {
  width: 100%;
  height: 140px;
  font-family: Montserrat, sans-serif;
  color: #fff;

  background-image: linear-gradient(rgb(255, 255, 255), rgb(255, 255, 255));

  /* background-color: rgba(243, 129, 129, 0.9); */
}

.custom-header .header-wrapper {
  display: block;
  width: 100%;
  margin: 0 auto;
  padding-top: 1em;
}
.custom-header .header-wrapper .main-logo {
  float: left;
  margin-left: 4em;
  font-weight: 700;
  font-size: 1.875em;
}
.custom-header .header-wrapper .main-menu {
  float: right;
  margin-right: 17em;
  text-align: center;
}
.custom-header .header-wrapper .main-menu li {
  display: inline-block;
  padding: 0.75em 1.875em;
}
.custom-header .header-wrapper .main-menu li a {
  padding-bottom: 0.75em;
  text-transform: uppercase;
  color: #fff;
  font-size: 0.875em;
  cursor: pointer;
}
.custom-header .header-wrapper .main-menu li a:hover {
  color: #fce38a;
  border-bottom: 0.1875em solid #fce38a;
  transition: 0.1s;
}
.custom-header .header-wrapper .main-menu li a span {
  font-size: 1.2em;
}
.custom-header .header-wrapper .main-menu li:last-child {
  padding: 0.75em 0;
}
/* .custom-header .header-wrapper .main-menu li:nth-child(6) a:hover,
.custom-header .header-wrapper .main-menu li:last-child a:hover {
  border: none;
} */
</style>
