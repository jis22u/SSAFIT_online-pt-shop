<template>
  <div id="mypage">
    <router-view></router-view>
  </div>
</template>
<!-- <h3 v-if="curUser.u_id === myUser.u_id">마이 페이지</h3>
    <h3 v-else>유저 메인페이지</h3>
    <table>
      <tr>
        <th>아이디</th>
        <td>{{ curUser.u_id }}</td>
      </tr>
      <tr>
        <th>성명</th>
        <td>{{ curUser.name }}</td>
      </tr>
      <tr>
        <th>닉네임</th>
        <td>{{ curUser.nickname }}</td>
      </tr>
      <tr>
        <th>이메일</th>
        <td>{{ curUser.email }}</td>
      </tr>
      <tr>
        <th>성별</th>
        <td>{{ curUser.gender }}</td>
      </tr>
      <tr>
        <th>연락처</th>
        <td>{{ curUser.phone_no | parsePhoneNo }}</td>
      </tr>
    </table>

    <router-link
      v-if="curUser.u_id === myUser.u_id"
      :to="{ name: 'UserViewUpdate' }"
      >회원정보 수정</router-link
    >
    <button v-else-if="checkIfFollow()" @click="unfollow">팔로우 취소</button>
    <button v-else @click="follow">팔로우</button>
    <hr />
    <button v-if="curUser.u_id === myUser.u_id" @click="deleteUser">
      회원탈퇴
    </button> -->

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "UserViewMypage",
  computed: {
    ...mapState(["curUser", "myUser", "myUserFollowers"]),
  },
  filters: {
    parsePhoneNo(phone_no) {
      if (!phone_no) {
        return;
      } else if (phone_no.length == 10) {
        return (
          phone_no.substring(0, 3) +
          "-" +
          phone_no.substring(3, 6) +
          "-" +
          phone_no.substring(6)
        );
      } else if (phone_no.length == 11) {
        return (
          phone_no.substring(0, 3) +
          "-" +
          phone_no.substring(3, 7) +
          "-" +
          phone_no.substring(7)
        );
      }
    },
  },
  methods: {
    follow() {
      axios
        .post(`followApi/`, null, {
          params: {
            from_user: this.myUser.u_id,
            to_user: this.curUser.u_id,
          },
        })
        .then(() => this.$store.commit("ADD_FOLLOWER"));
    },
    unfollow() {
      axios
        .delete(`followApi/`, {
          params: {
            from_user: this.myUser.u_id,
            to_user: this.curUser.u_id,
          },
        })
        .then(() => this.$store.commit("DELETE_FOLLOWER"));
    },
    checkIfFollow() {
      for (let f of this.myUserFollowers) {
        if (f.u_id === this.curUser.u_id) return true;
      }
      return false;
    },
    deleteUser() {
      axios
        .delete(`userApi/delete`, { params: { id: this.myUser.u_id } })
        .then(() => {
          this.$store.commit("CLEAR_ALL");
          // 세션정보 지우기 : 로그아웃
          sessionStorage.removeItem("access-token");
        })
        .then(() => this.$router.push("/"));
    },
  },
};
</script>

<style></style>
