import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";

Vue.config.productionTip = false;

// Default Header 설정
axios.defaults.headers.common["HEADER_AUTH"] =
  sessionStorage.getItem("access-token");

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
