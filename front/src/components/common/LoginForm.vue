<template>
  <div class="lg-form">
    <h2>로그인 페이지</h2>
    <form @submit.prevent="login">
      <!-- <label for="id">아이디 : </label>
      <input type="text" v-model="u_id" id="id" required /><br />
      <label for="pw">비밀번호 : </label
      ><input type="password" v-model="pw" id="pw" required /><br />
      <input type="submit" value="로그인" /> -->
      <input type="text" v-model="u_id" placeholder="아이디" required />
      <input type="password" v-model="pw" placeholder="비밀번호" required />
      <button>로그인</button>
      <p class="message">
        회원이 아니신가요?
        <a href="#" @click.prevent="$emit('want-regist')">회원가입하기</a>
      </p>
      <!-- <input type="button" @click="register" value="회원가입" /> -->
    </form>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";

export default {
  name: "UserViewLogin",
  data() {
    return {
      u_id: "",
      pw: "",
    };
  },
  methods: {
    login() {
      const me = this;

      axios
        .get(`userApi/login`, { params: { id: this.u_id, pw: this.pw } })
        .then((res) => {
          if (res.data.message === "fail") {
            alert("등록된 아이디가 없습니다.");
            throw new Error("등록된 아이디가 없습니다.");
          } else if (res.data.message === "wrong") {
            alert("비밀번호가 맞지 않습니다.");
            throw new Error("비밀번호가 맞지 않습니다.");
          }
          if (!res.data["access-token"]) {
            console.log(res);
            return;
          }
          // console.log(res.data);
          sessionStorage.setItem("access-token", res.data["access-token"]);
          sessionStorage.setItem("myId", this.u_id);
          this.$store.commit("CHECK_LOGIN");

          return axios.get(`userApi/detail`, {
            params: {
              id: this.u_id,
            },
          });
        })
        .then((res) => {
          this.$store.commit("SET_MY_USER", res.data);
          // this.myUser = res.data;
          me.$emit("success");
          // this.$router.push({ name: "HomeView" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    register() {
      this.$router.push({ name: "RegisterView" });
    },
  },
  created() {
    if (sessionStorage.getItem("access-token")) {
      this.$router.push({ name: "HomeView" });
    }
  },
};
</script>

<style scoped>
.lg-form {
  width: 60%;
  margin: auto;
}

h2 {
  margin-top: 2em;
  margin-bottom: 3em;
}
.lg-form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.lg-form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4caf50;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #ffffff;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.lg-form button:hover,
.lg-form button:active,
.lg-form button:focus {
  background: #43a047;
}
</style>
