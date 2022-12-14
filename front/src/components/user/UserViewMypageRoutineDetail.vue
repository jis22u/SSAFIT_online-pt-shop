<template>
  <div v-if="this.detailList.length">
    <h1>{{ this.detailList[0].r_name }}</h1>
    <div class="playlist-box">
      <!-- 백그라운드로 해보기 -->
      <div class="img">
        <div
          class="play-box"
          :style="{
            'background-image':
              'url(' + IMG_URL + detailList[my_idx].v_id + '.png)',
          }">
          <button @click="goPlay"><i class="bi bi-play-fill"></i></button>
        </div>
      </div>
      <div>
        <ul class="center">
          <li
            ref="list"
            v-for="(detail, idx) in detailList"
            :class="{ played: idx == my_idx }"
            :key="idx"
            @click="roll(idx)">
            <img :src="`${IMG_URL}${detail.v_id}.png`" />
            <!-- {{ detail }} -->
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";
export default {
  name: "UserViewMypageRoutineDetail",
  data() {
    return {
      detailList: [],
      VID_URL: this.$store.state.VID_URL,
      IMG_URL: this.$store.state.IMG_URL,
      nowSee: this.$store.state.nowPlayingIdx,
      my_idx: 0,
    };
  },
  computed: {
    nowPlayingIdx() {
      const idx = this.$store.getters.nowPlayingIdx;

      return idx;
    },
  },
  props: ["r_id"],
  watch: {
    nowPlayingIdx(newVal) {
      if (
        !this.$store.state.nowRoutineList.length ||
        this.$store.state.nowRoutineList[0].r_id == this.r_id
      )
        this.my_idx = newVal;
    },
  },
  methods: {
    getRoutineDetail() {
      return axios({
        url: "routineApi/" + this.r_id,
        method: "GET",
      });
    },
    goPlay() {
      this.$store.commit("SET_PLAYING_IDX", this.my_idx);
      this.$store.commit("SET_NOW_ROUTINE_LIST", this.detailList);
    },
    roll(idx) {
      if (
        !this.$store.state.nowRoutineList.length ||
        this.$store.state.nowRoutineList[0].r_id == this.r_id
      ) {
        if (!this.$store.state.nowRoutineList.length) {
          this.my_idx = idx;
        } else if (idx == this.nowPlayingIdx) {
          return;
        } else if (this.$store.state.nowRoutineList[0].r_id == this.r_id) {
          if (confirm("현재 보고있는 루틴을 변경하시겠습니까?"))
            this.$store.commit("SET_PLAYING_IDX", idx);
        } else {
          alert("현재 보고있는 루틴의 영상이 아닙니다");
        }
      } else {
        this.my_idx = idx;
      }
    },
  },
  async created() {
    const res = await this.getRoutineDetail();
    this.detailList = res.data;
    console.log(res.data);
    if (!this.$store.state.nowRoutineList.length) {
      this.$store.commit("SET_NOW_ROUTINE_LIST", this.detailList);
    } else if (
      this.$store.state.nowRoutineList[0].r_id != this.r_id &&
      confirm("현재 실행되고 있는 루틴이 있습니다.\n변경하시겠습니까?")
    ) {
      this.$store.commit("SET_NOW_ROUTINE_LIST", []);
      this.$store.commit("SET_PLAYING_IDX", 0);
      this.$store.commit("SET_NOW_ROUTINE_LIST", this.detailList);
    } else {
      console.log("아무일도 안일어남");
    }

    if (
      !this.$store.state.nowRoutineList.length ||
      this.$store.state.nowRoutineList[0].r_id == this.r_id
    ) {
      this.my_idx = this.nowPlayingIdx;
    }
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
  padding: 2rem;
}

.playlist-box {
  display: flex;
  flex-direction: row;

  justify-content: center;
  width: 100%;

  margin-top: 30px;
}

.center {
  width: 20em;
  padding: 0;
  margin: 2em;
  border: 10px solid #fff;
  box-shadow: 0px 2px 3px #eaeaea;

  overflow: auto;
  max-height: 500px;
}

li {
  background: #f7f7f7;
  color: #ddd;
  list-style-type: none;
  padding: 1em;
  margin: 0;
  transition: background-color 0.5s;
}

ul.center li.played {
  color: #fff;
  background: rgba(27, 85, 171, 0.84);
}

li:hover {
  color: #fff;
  background: rgb(119, 172, 253, 0.84);
}

li img {
  width: 90%;
}

.img > .play-box {
  display: block;
  width: 500px;
  height: 500px;

  margin: 2em;

  position: relative;
  background-size: cover;
  background-position: center;
}

.img > .play-box button {
  display: block;
  opacity: 0.3;

  margin: 0 auto;

  font-size: 5rem;

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
