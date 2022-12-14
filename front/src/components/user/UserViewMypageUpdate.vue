<template>
  <div class="lg-form">
    <h3>유저 수정페이지</h3>
    <input
      type="text"
      v-model="user.u_id"
      placeholder="아이디"
      readonly
      required
    />
    <input type="password" v-model="user.pw" placeholder="비밀번호" required />
    <input type="text" v-model="user.name" placeholder="이름" required />

    <input type="email" v-model="user.email" placeholder="이메일" required />

    <div class="radio-box">
      <input
        type="radio"
        id="M"
        v-model="user.gender"
        name="gender"
        value="M"
        checked
      />
      <label for="M">남</label>
      <input
        type="radio"
        id="F"
        v-model="user.gender"
        name="gender"
        value="F"
      />
      <label for="F">여</label>
    </div>
    <input
      type="tel"
      v-model="user.phone_no"
      placeholder="전화번호 : 예) 01012345678"
    />
    <input type="text" v-model="user.nickname" placeholder="닉네임" />

    <div class="text pupose-box">
      <h5>관심영역</h5>
      <button
        class="purpose-btn"
        :class="{ selectedBtn: checkPurpose(p.p_id) != -1 }"
        v-for="(p, idx) in purposeList"
        @click.prevent="addPurpose(p)"
        :key="idx"
      >
        {{ p.p_name }}
      </button>
    </div>

    <button @click="updateUser" class="last-btn">회원정보수정</button>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "UserViewUpdate",
  data() {
    return {
      user: {},
      purposeList: [],
      myPurpose: [],
    };
  },
  computed: {
    ...mapState(["myUser"]),
  },
  async created() {
    if (!this.myUser.u_id) {
      alert("어딜!");
      this.$router.push({ name: "MainPageView" });
    }

    this.user = { ...this.myUser };

    const res = await this.getPurpose();
    const res2 = await this.getMyPurpose();

    if (res.data.length) this.purposeList = res.data;
    if (res2.data.length) this.myPurpose = res2.data;
  },
  watch: {
    myUser(newVal) {
      // console.log(newVal);
      this.user = { ...newVal };
      this.setPurpose();
    },
  },
  methods: {
    async setPurpose() {
      const res = await this.getPurpose();
      const res2 = await this.getMyPurpose();
      if (res.data.length) this.purposeList = res.data;
      if (res2.data.length) this.myPurpose = res2.data;
    },
    updateUser() {
      console.log(this.user);
      axios
        .put(`userApi/update`, this.user)
        .then(() => {
          this.$store.commit("SET_MY_USER", this.user);
          // this.$store.commit("SET_CUR_USER", this.user);
        })
        .then(() => this.$router.push({ name: "UserViewMypageMain" }));
    },
    addPurpose(p) {
      const idx = this.checkPurpose(p.p_id);
      if (idx == -1) {
        this.myPurpose.push(p);
      } else {
        this.myPurpose.splice(idx, 1);
      }

      axios({
        method: "POST",
        url: "userApi/purpose",
        params: {
          p_id: p.p_id,
          u_id: this.user.u_id,
        },
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => console.log(err));
    },
    checkPurpose(p_id) {
      const idx = this.myPurpose.findIndex((e) => e.p_id === p_id);

      return idx;
    },
    getPurpose() {
      return axios({
        url: "userApi/purpose",
        method: "GET",
      });
    },
    getMyPurpose() {
      return axios({
        url: "userApi/purpose",
        method: "GET",
        params: { u_id: this.user.u_id },
      });
    },
  },
};
</script>

<style scoped>
.ok {
  background-color: rgba(0, 255, 0, 0.788) !important;
}

.nope {
  background-color: rgba(228, 110, 110, 0.685) !important;
}

.lg-form {
  width: 60%;

  margin: auto;
  padding: 2em;
  overflow: auto;
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
.lg-form .text {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;

  height: 100px;
  overflow: auto;
  text-align: left;
}

.lg-form .text h5 {
  text-align: center;
}

.lg-form input[type="checkbox"] {
  width: 30px;
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
.last-btn {
  margin-bottom: 10em;
}

.pupose-box {
  padding: 1em;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
}

.purpose-btn {
  display: inline-block;
  padding: 1em;
  margin: 1em;

  max-width: 26%;

  border-radius: 2em;
}

/** 라디오 */

.radio-box {
  font-family: "Roboto", sans-serif;
  outline: 0;
  height: 38px;
  margin: 0 0 15px;
  background: #f2f2f2;

  padding-top: 1em;
  text-align: center;
}

.radio-box label {
  margin-right: 20px;
}

[type="radio"]:checked,
[type="radio"]:not(:checked) {
  position: absolute;
  left: -9999px;
}
[type="radio"]:checked + label,
[type="radio"]:not(:checked) + label {
  position: relative;
  padding-left: 28px;
  cursor: pointer;
  line-height: 20px;
  display: inline-block;
  color: #666;
}
[type="radio"]:checked + label:before,
[type="radio"]:not(:checked) + label:before {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 18px;
  height: 18px;
  border: 1px solid #4caf50;
  border-radius: 100%;
  background: #fff;
}
[type="radio"]:checked + label:after,
[type="radio"]:not(:checked) + label:after {
  content: "";
  width: 12px;
  height: 12px;
  background: #4caf50;
  position: absolute;
  top: 4px;
  left: 4px;
  border-radius: 100%;
  -webkit-transition: all 0.2s ease;
  transition: all 0.2s ease;
}
[type="radio"]:not(:checked) + label:after {
  opacity: 0;
  -webkit-transform: scale(0);
  transform: scale(0);
}
[type="radio"]:checked + label:after {
  opacity: 1;
  -webkit-transform: scale(1);
  transform: scale(1);
}

.selectedBtn {
  background-color: rgba(13, 50, 214, 0.459) !important;
}
</style>
