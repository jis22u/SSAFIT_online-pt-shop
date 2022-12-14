<template>
  <div class="lg-form">
    <h2>트레이너 되기</h2>

    <input type="text" placeholder="트레이너 명" v-model="t_name" />
    <input type="text" placeholder="경력 (년)" v-model="career" />

    <!-- 유튜브 인스타 가능하게 하기 -->

    <UserViewMypageTrainerRegistEditor
      @get-content="t_content = $event"
    ></UserViewMypageTrainerRegistEditor>
    <button @click="registTrainer">트레이너 등록</button>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";
import UserViewMypageTrainerRegistEditor from "@/components/user/UserViewMypageTrainerRegistEditor.vue";

export default {
  name: "UserViewMypageTrainerRegist",
  data() {
    return {
      tmpUser: {},
      t_name: "",
      career: "",
      t_content: "",
    };
  },
  components: {
    UserViewMypageTrainerRegistEditor,
  },
  computed: {
    ...mapState(["myUser"]),
  },

  watch: {
    myUser(newVal) {
      // console.log(newVal);
      this.tmpUser = { ...newVal };
    },
    t_content() {
      console.log(this.t_content);
    },
  },
  methods: {
    updateUser() {
      console.log(this.tmpUser);
      // this.tmpUser.phone_no = this.tmpUser.phone_no.split("-").join("");
      axios
        .put(`userApi/update`, null, { params: this.tmpUser })
        .then(() => {
          this.$store.commit("SET_MY_USER", this.tmpUser);
          this.$store.commit("SET_CUR_USER", this.tmpUser);
        })
        .then(() => this.$router.push({ name: "MainPageView" }));
    },
    registTrainer() {
      const user = {
        u_id: this.myUser.u_id,
        t_name: this.t_name,
        career: this.career,
        t_content: this.t_content,
      };

      axios({
        url: "trainerApi/",
        method: "POST",
        data: user,
      })
        .then((res) => {
          console.log(res.data);
          this.$router.push({ name: "UserViewMypageMain" });
        })
        .catch((err) => console.log(err));
    },
  },
  created() {
    if (!this.myUser.u_id) {
      alert("어딜!");
      this.$router.push({ name: "MainPageView" });
    }
    this.tmpUser = { ...this.myUser };
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
  display: inline;
  padding: 1em;
  margin: 1em;

  width: auto;

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
