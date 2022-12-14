<template>
  <div class="lg-form">
    <h2>회원가입</h2>
    <input
      type="text"
      v-model="user.u_id"
      @blur="checkIdDuplicate"
      placeholder="아이디"
      :class="{ ok: idIsOk === true, nope: idIsOk === false }"
      required
    />

    <!-- <div :class="{ ok: isOk }">
        {{ duplicateMsg }}
      </div> -->
    <input type="password" v-model="user.pw" placeholder="비밀번호" required />
    <input type="text" v-model="user.name" placeholder="이름" required />

    <input
      type="email"
      :class="{ ok: emailIsOk === true, nope: emailIsOk === false }"
      v-model="user.email"
      @blur="checkEmailDuplicate"
      placeholder="이메일"
      required
    />

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
      @blur="checkPhoneDuplicate"
      :class="{ ok: phone_noIsOk === true, nope: phone_noIsOk === false }"
      v-model="user.phone_no"
      HEAD
      placeholder="전화번호 : 예) 01012345678"
    />
    <input
      type="text"
      @blur="checkNicknameDuplicate"
      :class="{ ok: nicknameIsOk === true, nope: nicknameIsOk === false }"
      v-model="user.nickname"
      placeholder="닉네임"
    />

    <div class="text pupose-box">
      <h5>관심영역</h5>
      <button
        class="purpose-btn"
        :class="{ selectedBtn: checkPurpose(p.p_id) != -1 }"
        v-for="(p, idx) in purposeList"
        @click.prevent="addPurpose(p.p_id)"
        :key="idx"
      >
        {{ p.p_name }}
      </button>
    </div>

    <div class="text">
      <h5>개인정보 이용 동의서</h5>
      <p>제 1조 1항</p>
      <p>사실 법 잘 몰라!</p>
      <p>제 1조 2항</p>
      <p>빈칸 채우기 용 구절</p>
      <p>이 사이트는 사리사욕을 위해 사용됨</p>
      <p>어딘가에는 사용되지 않을까요?</p>
      <p>헉</p>
    </div>

    <input width="30px" type="checkbox" v-model="doYouAgree" />
    <span>위의 내용에 동의함.</span>

    <button class="last-btn" @click="registUser">회원가입</button>
    <!-- <input type="button" @click="toLogin" value="로그인" /> -->
  </div>
</template>

<script>
import axios from "@/util/http-common.js";

export default {
  name: "JoinForm",
  data() {
    return {
      user: {
        u_id: "",
        pw: "",
        name: "",
        email: "",
        gender: "M",
        phone_no: "",
        nickname: "",
      },
      idIsOk: 0,
      emailIsOk: 0,
      nicknameIsOk: 0,
      phone_noIsOk: 0,
      doYouAgree: false,
      purposeList: [],
      myPurpose: [],
    };
  },
  methods: {
    addPurpose(p_id) {
      const idx = this.checkPurpose(p_id);
      // console.log(this.myPurpose);
      // console.log(Array.from(this.myPurpose));
      if (idx == -1) {
        this.myPurpose.push(p_id);
      } else {
        this.myPurpose.splice(idx, 1);
      }
    },
    checkPurpose(p_id) {
      const idx = this.myPurpose.findIndex((e) => e == p_id);

      return idx;
    },
    registUser() {
      if (
        this.idIsOk &&
        this.emailIsOk &&
        this.nicknameIsOk &&
        this.phone_noIsOk &&
        this.doYouAgree &&
        this.user.pw &&
        this.user.name
      ) {
        const me = this;
        axios({
          url: "userApi/regist",
          method: "POST",
          data: this.user,
        })
          // .post(`userApi/regist`, null, { params: this.user })
          .then(() => {
            this.myPurpose.forEach((element) => {
              axios({
                method: "POST",
                url: "userApi/purpose",
                params: {
                  p_id: element,
                  u_id: this.user.u_id,
                },
              })
                .then((res) => {
                  console.log(res.data);
                })
                .catch((err) => console.log(err));
            });

            me.$emit("success");
          })
          .catch((err) => {
            console.log(err.response.data);
          });
      } else {
        alert("안되는게 있어!");
      }
    },
    checkIdDuplicate() {
      if (this.user.u_id === "") {
        this.idIsOk = false;
        return;
      }

      axios({
        url: "userApi/check",
        method: "GET",
        params: { u_id: this.user.u_id },
      }).then((res) => {
        console.log(res);
        if (res.data === "fail") {
          // alert("사용중인 아이디입니다.");
          this.idIsOk = false;
        } else {
          // alert("사용 가능한 아이디입니다.");
          this.idIsOk = true;
        }
      });
    },

    checkEmailDuplicate() {
      if (this.user.email === "") {
        this.emailIsOk = false;
        return;
      }

      axios({
        url: "userApi/email",
        method: "GET",
        params: { email: this.user.email },
      }).then((res) => {
        console.log(res);
        if (res.data) {
          this.emailIsOk = true;
        } else {
          this.emailIsOk = false;
        }
      });
    },

    checkNicknameDuplicate() {
      if (this.user.nickname === "") {
        this.nicknameIsOk = false;
        return;
      }

      axios({
        url: "userApi/nickname",
        method: "GET",
        params: { nickname: this.user.nickname },
      }).then((res) => {
        console.log(res);
        if (res.data) {
          this.nicknameIsOk = true;
        } else {
          this.nicknameIsOk = false;
        }
      });
    },

    checkPhoneDuplicate() {
      if (this.user.phone_no === "") {
        this.phone_noIsOk = false;
        return;
      }

      axios({
        url: "userApi/phone_no",
        method: "GET",
        params: { phone_no: this.user.phone_no },
      }).then((res) => {
        console.log(res);
        if (res.data) {
          this.phone_noIsOk = true;
        } else {
          this.phone_noIsOk = false;
        }
      });
    },
    // toLogin() {
    //   this.$router.push({ name: "LoginView" });
    // },
  },
  created() {
    if (sessionStorage.getItem("access-token")) {
      alert("너 이미 로그인 중이잖아");
      this.$router.push({ name: "HomeView" });
    }

    axios({
      url: "userApi/purpose",
      method: "GET",
    })
      .then((res) => {
        if (res.data.length) this.purposeList = res.data;
      })
      .catch((err) => console.log(err));
  },
  // computed: {
  //   duplicateMsg() {
  //     return this.isOk ? "중복확인 완료" : "중복확인";
  //   },
  // },
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
  display: inline;
  padding: 1em;
  margin: 1em;

  /* width: auto; */

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
