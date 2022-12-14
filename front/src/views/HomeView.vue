<template>
  <div class="home">
    <router-view></router-view>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";

export default {
  name: "HomeView",

  methods: {
    parseJWT(token) {
      var base64Url = token.split(".")[1];
      var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
      var jsonPayload = decodeURIComponent(
        window
          .atob(base64)
          .split("")
          .map((c) => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
          .join("")
      );
      return JSON.parse(jsonPayload);
    },
  },
  created() {
    // sessionStorage.setItem(
    //   "access-token",
    //   "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJteU1lc3NhZ2UiOiJXVEZGRkZGIEJST09PT08ifQ.Tf3M9Y8v6vzhDENFgucVt4c9KN1GQn0avqBEpMR_FRQ"
    // );
    const token = sessionStorage.getItem("access-token");
    if (token) {
      const payload = this.parseJWT(token);
      console.log(payload);
      axios
        .get(`userApi/detail`, { params: { id: payload.id } })
        .then((res) => this.$store.commit("SET_MY_USER", res.data));
    }
    // else {
    // this.$router.push({ name: "LoginView" });
    // }
  },
};
</script>
