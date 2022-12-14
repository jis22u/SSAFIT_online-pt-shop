<template>
  <div class="routine-list-container">
    <div id="routine-list">
      <h2>ROUTINE LIST</h2>
      <div class="my">나만의 운동루틴대로 리스트를 만들어보세요</div>
      <UserViewMypageMainRoutineListMain
        :routines="myRoutines"
        @showMaker="showMaker" />
    </div>
    <!-- 루틴 리스트 등록 폼 -->
    <div v-if="isShowMaker" id="routine-list-maker">
      <h2>
        <input type="text" v-model="r_name" />
        <button @click="showMakerClose">
          <i class="bi bi-x-lg"></i> close
        </button>
      </h2>
      <div class="maker-content">
        <div class="buyed-video-box">
          <div
            @click="addRoutine(video)"
            class="video-box"
            v-for="(video, idx) in videos"
            :key="idx">
            <div>
              <img class="video-img" :src="`${IMG_URL}${video.v_id}.png`" />
            </div>
            <div class="video-content">
              <h3>{{ video.title }}</h3>
              <h5>{{ video.category ? video.category : "카테고리 없음" }}</h5>
            </div>
          </div>
        </div>

        <div class="order-list">
          <span v-if="makingRoutine.length == 0"> 리스트를 넣어주세요 </span>
          <ul v-else>
            <li v-for="(video, idx) in makingRoutine" :key="idx">
              <span>{{ video.title }}</span>
              <span class="li-btn">
                <button @click="goUp(video, idx)">
                  <i class="bi bi-dash-lg"></i>
                </button>
                <button @click="goDown(video, idx)">
                  <i class="bi bi-plus-lg"></i>
                </button>
                <button @click="removeRoutine(idx)">
                  <i class="bi bi-x-lg"></i>
                </button>
              </span>
            </li>
          </ul>
          <div class="regist-btn">
            <button @click="registRoutine">등록하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";
import UserViewMypageMainRoutineListMain from "./UserViewMypageMainRoutineListMain.vue";

export default {
  name: "UserViewMypageMainRoutineList",
  data() {
    return {
      IMG_URL: this.$store.state.IMG_URL,
      isShowMaker: false,
      myRoutines: [],
      videos: [],
      makingRoutine: [],
      cnt: 1,
      r_name: "MY ROUTINE LIST",
    };
  },
  components: {
    UserViewMypageMainRoutineListMain,
  },
  computed: {
    myUser() {
      console.log("유저등장");
      return this.$store.getters.myUser;
    },
  },
  watch: {
    myUser({ u_id }) {
      this.getRoutineList();
      this.getVideos(u_id);
    },
  },
  props: {
    user: Object,
  },
  methods: {
    showMaker() {
      this.isShowMaker = true;
    },
    showMakerClose() {
      this.makingRoutine = [];
      this.cnt = 1;
      this.isShowMaker = false;
    },
    getVideos(u_id) {
      axios({
        url: "userApi/buyList",
        params: {
          u_id: u_id,
        },
        method: "GET",
      })
        .then((res) => {
          this.videos = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addRoutine(video) {
      const route = {
        v_id: video.v_id,
        li: this.cnt++,
        title: video.title,
      };
      this.makingRoutine.push(route);
    },
    registRoutine() {
      const routine = this.makingRoutine.slice();
      console.log(routine);

      axios({
        url: "routineApi/",
        method: "POST",
        // 객체 배열(루틴 리스트)
        data: routine,
        params: {
          r_name: this.r_name,
          u_id: this.myUser.u_id,
        },
      })
        .then((res) => {
          console.log(res, "성공");
          this.getRoutineList();
          this.showMakerClose();
        })
        .catch((err) => console.log(err));
    },
    getRoutineList() {
      axios({
        url: "routineApi/",
        method: "GET",
        params: {
          u_id: this.myUser.u_id,
        },
      })
        .then((res) => {
          console.log(`리스트 받아옴`, res.data);
          this.myRoutines = res.data;
        })
        .catch((err) => console.log(err));
    },

    goUp(video, idx) {
      console.log("올라간다");

      if (idx == 0) return;

      video.li--;
      this.makingRoutine[idx - 1].li++;

      this.changeOrder(idx, -1);
    },
    goDown(video, idx) {
      console.log("내려간다");

      if (idx == this.makingRoutine.length - 1) return;

      video.li++;
      this.makingRoutine[idx + 1].li--;

      this.changeOrder(idx, 1);
    },
    changeOrder(now, move) {
      const newPosition = now + move;

      if (newPosition < 0 || newPosition >= this.makingRoutine.length) return;

      const tmp = JSON.parse(JSON.stringify(this.makingRoutine));
      const target = tmp.splice(now, 1)[0];

      tmp.splice(newPosition, 0, target);
      this.makingRoutine = tmp;
    },
    removeRoutine(idx) {
      this.makingRoutine.splice(idx, 1);
    },
  },
  created() {
    this.getVideos(this.myUser.u_id);
    this.getRoutineList();
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
.my {
  font-family: "Do Hyeon", sans-serif;
  font-weight: lighter;
  font-style: italic;
  text-align: center;
  color: rgb(81, 81, 81);
}
h2 {
  font-family: "Montserrat", sans-serif;
  text-align: center;
  padding: 1rem;
  font-size: 3.5em;
}

h2 > input {
  font-family: "Montserrat", sans-serif;
  font-size: 0.4em;
  border: 0px;
  border-bottom: 1px solid black;
  /* border-right: 1px solid black; */
  padding: 0.3em;

  text-align: center;
}

h2 > button {
  width: 32px;
  height: 32px;

  border: 0px;

  font-weight: bold;
  background: inherit;

  border-radius: 1em;
  cursor: pointer;
}

.routine-list-container {
  width: 100%;
  padding: 2em;
  padding-top: 5rem;
  margin-top: 50px;
}
.routine-list {
  display: flex;
  direction: row;
  width: 100%;
  padding: 3em;
}

#routine-list-maker {
  margin: 0 auto;
  margin-top: 5rem;

  width: 90%;
  min-height: 7em;
}

.maker-content {
  display: flex;
  direction: row;
  border: 2px solid black;
  border-radius: 2em;
}

.buyed-video-box {
  border-right: 1px solid black;
  width: 65%;
  padding: 2em;

  display: flex;
  flex-direction: row;
  flex-wrap: wrap;

  overflow-y: auto;

  max-height: 600px;
}

.video-box {
  margin: 1em;
  padding: 1em;
  width: 33%;
  border: 1.5px solid black;

  display: flex;
  flex-direction: column;

  cursor: pointer;
}

.video-img {
  width: 100%;
}

.video-content {
  display: flex;
  flex-direction: column;

  padding-left: 1rem;
}

.video-content h3 {
  margin-top: 1rem;
  margin-bottom: 1rem;
}

.order-list {
  padding: 1rem;
  width: 35%;
  padding-bottom: 10%;
  /* border: 1px solid black; */
  margin: 2em;

  position: relative;
}

.order-list li {
  margin-top: 10px;
  overflow: hidden;
}

.regist-btn {
  position: absolute;
  bottom: 1em;
  width: 80%;
}

.regist-btn > button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: black;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #ffffff;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;

  border-radius: 2em;
}

.create-btn {
  background: grey;
  cursor: pointer;
}

.li-btn {
  float: right;
  /* margin-right: 1em; */
}

.li-btn > button {
  width: 18px;
  height: 18px;

  background: #fff;
}
</style>
