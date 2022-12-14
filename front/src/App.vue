<template>
  <div id="app">
    <TheHeaderNav
      @show-modal="popUp($event)"
      :class="customClass"></TheHeaderNav>

    <TheSidebar :isToggled="isToggled"></TheSidebar>
    <button
      type="button"
      :class="{ btn: true, 'hamburger-btn': true, toggled: isToggled }"
      id="menu-toggle"
      @click="isToggled = !isToggled">
      <i class="bi bi-cart4"></i>
    </button>
    <router-view id="section1" />
    <div class="overlay" v-if="showModal" @click="showModal = false"></div>
    <UserViewModal
      @close-modal="showModal = false"
      v-if="showModal"
      :selected="modalContent"
      @want-regist="modalContent = 'JOIN'">
    </UserViewModal>

    <TheVideoModal v-if="nowRoutineList.length"></TheVideoModal>

    <br />
    <the-footer></the-footer>
  </div>
</template>

<script>
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import UserViewModal from "@/components/common/TheModal.vue";
import TheSidebar from "./components/common/TheSidebar.vue";
import TheVideoModal from "./components/common/TheVideoModal.vue";
import TheFooter from "./components/common/TheFooter.vue";

export default {
  name: "TheApp",
  data() {
    return {
      showModal: false,
      modalContent: "",
      isToggled: true,
      customClass: "main-header",
    };
  },
  computed: {
    myUser() {
      return this.$store.getters.myUser;
    },
    myCart() {
      return this.$store.getters.myCart;
    },
    nowRoutineList() {
      return this.$store.getters.nowRoutineList;
    },
    // myPurchaseList() {
    //   return this.$store.getters.myPurchaseList;
    // },
    // myFavoriteList() {
    //   return this.$store.getters.myFavoriteList;
    // },
  },
  watch: {
    $route(to, from) {
      // console.log("되냐?", from, to);
      console.log(from);
      if (to.name == "MainPageView") {
        this.customClass = "main-header";
      } else {
        this.customClass = "custom-header";
      }
    },
    myUser() {
      const u_id = this.myUser.u_id;
      if (u_id) {
        this.$store.dispatch("getFavoriteList", u_id);
        this.$store.dispatch("getBuyList", u_id);
      }
    },
  },
  components: {
    TheHeaderNav,
    UserViewModal,
    TheSidebar,
    TheVideoModal,
    TheFooter,
  },
  methods: {
    popUp(select) {
      this.modalContent = select;
      this.showModal = true;
    },
  },
  created() {
    if (!sessionStorage.getItem("access-token")) {
      // alert("어딜 감히!!!");
      // this.$router.push({ name: "MainPageView" });
    } else if (!this.myUser.id && sessionStorage.getItem("myId")) {
      console.log("하이", this.myUser);
      this.$store.dispatch("getUserById", sessionStorage.getItem("myId"));
    }

    const cart = JSON.parse(localStorage.getItem("myCart"));
    if (cart) {
      this.$store.commit("SET_MY_CART", cart);
    }
  },
};
</script>

<style>
* {
  margin: 0px;
  padding: 0px;
}

body {
  margin: 0 auto;
  padding: 0;
  font-size: 16px;
  background: #fff;

  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
body section {
  display: block;
}
body h1,
body h2,
body h3,
body h4,
body h5,
body h6,
body ul,
body li {
  margin: 0;
  padding: 0;
}
body h1 {
  font-family: Montserrat, sans-serif;
  font-weight: 700;
  text-transform: uppercase;
}
body h2 {
  font-family: Montserrat, sans-serif;
  font-size: 1.875em;
  font-weight: 700;
  text-transform: uppercase;
}
body h3 {
  font-family: Montserrat, sans-serif;
  font-size: 1.5em;
  font-weight: 400;
}
body h4 {
  font-family: Montserrat, sans-serif;
  font-size: 0.875em;
  font-weight: 400;
  text-transform: uppercase;
}
body p {
  line-height: 150%;
  font-family: Roboto, sans-serif;
  font-size: 0.9375em;
  color: #999;
}
body li {
  list-style-type: none;
}
body a {
  text-decoration: none;
}

/* #app {

  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
} */

@media only screen and (max-width: 1990px) {
  body {
    font-size: 18px;
  }
  .main-header .header-wrapper {
    width: 65%;
  }
  #section-1 {
    height: 40em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper {
    padding-top: 7.3em;
  }
}
@media only screen and (max-width: 1680px) {
  body {
    font-size: 18px;
  }
  .main-header .header-wrapper {
    width: 65%;
  }
  #section-1 {
    height: 40em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper {
    padding-top: 7.3em;
  }
}
@media only screen and (max-width: 1366px) {
  body {
    font-size: 16px;
  }
  .main-header .header-wrapper {
    width: 70%;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper {
    padding-top: 5.3em;
  }
}
@media only screen and (max-width: 1120px) {
  .main-header .header-wrapper .main-menu li {
    padding: 0.75em 1.4em;
  }
  #section-1 {
    height: 35em;
  }
  #section-1 .content-slider nav {
    bottom: -0.2em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper {
    padding-top: 7em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper .line {
    margin: 2em auto;
  }
}
@media only screen and (max-width: 1024px) {
  body {
    font-size: 14px;
  }
  .main-header .header-wrapper .main-menu li {
    padding: 0.75em 1.3em;
  }
  #section-1 .content-slider nav {
    bottom: 0;
  }
  #section-1 .content-slider nav .controls {
    width: 80%;
  }
}
@media only screen and (max-width: 860px) {
  .main-header .header-wrapper .main-menu li {
    padding: 0.75em 0.9em;
  }
  #section-1 {
    height: 29em;
  }
  #section-1 .content-slider nav {
    bottom: -1em;
  }
  #section-1 .content-slider nav .controls {
    width: 90%;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper h2 {
    font-size: 2em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper h1 {
    font-size: 4.5em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper {
    padding-top: 5em;
  }
}
@media only screen and (max-width: 768px) {
  .main-header .header-wrapper .main-menu li {
    padding: 0.75em 0.5em;
  }
  #section-1 {
    height: 27em;
  }
  #section-1 .content-slider nav .controls {
    width: 100%;
  }
  #section-1 .content-slider nav .controls label {
    width: 19%;
    font-size: 0.8em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper .line {
    margin: 1.7em auto;
  }
}
@media only screen and (max-width: 650px) {
  .main-header .header-wrapper {
    width: 95%;
  }
}
@media only screen and (max-width: 480px) {
  .main-header .header-wrapper {
    width: 97%;
  }
  .main-header .header-wrapper .main-logo {
    display: none;
  }
  .main-header .header-wrapper .main-menu li {
    padding: 0.2em 0.3em;
  }
  .main-header .header-wrapper .main-menu li:last-child,
  .main-header .header-wrapper .main-menu li:nth-child(6) {
    padding: 0.2em 0.7em;
  }
  #section-1 {
    height: 26em;
  }
  #section-1 .content-slider nav {
    bottom: -0.5em;
  }
  #section-1 .content-slider nav .controls label {
    width: 40%;
    font-size: 0.7em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper h2 {
    font-size: 1.5em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper h1 {
    font-size: 3em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper .line {
    margin: 1.7em auto;
  }
}

@media only screen and (max-width: 1990px) {
  body {
    font-size: 18px;
  }
  .main-header .header-wrapper {
    width: 65%;
  }
  #section-1 {
    height: 40em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper {
    padding-top: 7.3em;
  }
}
@media only screen and (max-width: 1680px) {
  body {
    font-size: 18px;
  }
  .main-header .header-wrapper {
    width: 65%;
  }
  #section-1 {
    height: 40em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper {
    padding-top: 7.3em;
  }
}
@media only screen and (max-width: 1366px) {
  body {
    font-size: 16px;
  }
  .main-header .header-wrapper {
    width: 70%;
  }
}
@media only screen and (max-width: 1120px) {
  .main-header .header-wrapper .main-menu li {
    padding: 0.75em 1.4em;
  }
  #section-1 {
    height: 35em;
  }
  #section-1 .content-slider nav {
    bottom: -0.2em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper {
    padding-top: 7em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper .line {
    margin: 2em auto;
  }
}
@media only screen and (max-width: 1024px) {
  body {
    font-size: 14px;
  }
  .main-header .header-wrapper .main-menu li {
    padding: 0.75em 1.3em;
  }
  #section-1 .content-slider nav {
    bottom: 0;
  }
  #section-1 .content-slider nav .controls {
    width: 80%;
  }
}
@media only screen and (max-width: 860px) {
  .main-header .header-wrapper .main-menu li {
    padding: 0.75em 0.9em;
  }
  #section-1 {
    height: 29em;
  }
  #section-1 .content-slider nav {
    bottom: -1em;
  }
  #section-1 .content-slider nav .controls {
    width: 90%;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper h2 {
    font-size: 2em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper h1 {
    font-size: 4.5em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper {
    padding-top: 5em;
  }
}
@media only screen and (max-width: 768px) {
  .main-header .header-wrapper .main-menu li {
    padding: 0.75em 0.5em;
  }
  #section-1 {
    height: 27em;
  }
  #section-1 .content-slider nav .controls {
    width: 100%;
  }
  #section-1 .content-slider nav .controls label {
    width: 19%;
    font-size: 0.8em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper .line {
    margin: 1.7em auto;
  }
}
@media only screen and (max-width: 650px) {
  .main-header .header-wrapper {
    width: 95%;
  }
}
@media only screen and (max-width: 480px) {
  .main-header .header-wrapper {
    width: 97%;
  }
  .main-header .header-wrapper .main-logo {
    display: none;
  }
  .main-header .header-wrapper .main-menu li {
    padding: 0.2em 0.3em;
  }
  .main-header .header-wrapper .main-menu li:last-child,
  .main-header .header-wrapper .main-menu li:nth-child(6) {
    padding: 0.2em 0.7em;
  }
  #section-1 {
    height: 26em;
  }
  #section-1 .content-slider nav {
    bottom: -0.5em;
  }
  #section-1 .content-slider nav .controls label {
    width: 40%;
    font-size: 0.7em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper h2 {
    font-size: 1.5em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper h1 {
    font-size: 3em;
  }
  #section-1 .content-slider .slider .banner .banner-inner-wrapper .line {
    margin: 1.7em auto;
  }
}

/* nav {

  padding: 30px;
} */

nav a {
  font-weight: bold;
  color: #ffffff;
}

nav a.router-link-exact-active {
  color: #ffffff;
}

.overlay {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.hamburger-btn {
  position: fixed;
  margin: 15px 0;
  right: 280px;
  bottom: 60px;
  cursor: pointer;
  width: 58px;
  height: 58px;

  z-index: 9998;

  border-radius: 200em;
}

.hamburger-btn i {
  font-size: 26px;
}

.hamburger-btn.toggled {
  right: 60px;
}

video {
  /* width: 100%; */
  max-width: 700px;
  max-height: 700px;
  object-fit: cover !important;
}
</style>
