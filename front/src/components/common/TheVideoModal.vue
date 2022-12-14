<template>
  <div
    ref="videoModal"
    @mousedown="activeMove"
    @mouseup="isActive = false"
    @mouseout="isActive = false"
    @mousemove="moveActiveModal"
    :class="{ 'video-modal': true, movable: isActive, 'small-dude': isSmall }">
    <div>
      <button v-if="isSmall" @click="isSmall = false" class="go-big-btn">
        <i class="bi bi-aspect-ratio"></i>
      </button>
      <button v-else @click="isSmall = true" class="go-big-btn">
        <i class="bi bi-aspect-ratio"></i>
      </button>
      <h2>
        {{ nowRoutineList[0].r_name }}
        <span>{{ nowPlayingIdx + 1 }}/{{ nowRoutineList.length }}</span>
      </h2>
      <button class="close" @click="closeModal">x</button>
    </div>
    <video
      ref="videoPlayer"
      played
      :autoplay="isPlaying"
      :key="videoSrc"
      @ended="videoEnd"
      controlsList="nodownload">
      <source :src="videoSrc" />
    </video>
    <div class="control-btns">
      <button @click="isPlaying = true"><i class="bi bi-play-fill"></i></button>
      <button @click="isPlaying = false">
        <i class="bi bi-pause-fill"></i>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "TheVideoModal",
  components: {},
  computed: {
    nowRoutineList() {
      return this.$store.getters.nowRoutineList;
    },

    nowPlayingIdx() {
      return this.$store.getters.nowPlayingIdx;
    },
  },
  data() {
    return {
      videoSrc: "",
      isPlaying: false,
      isSmall: false,

      isActive: false,
      startX: 0,
      startY: 0,
    };
  },
  watch: {
    nowPlayingIdx(newVal) {
      if (newVal < this.nowRoutineList.length) {
        this.videoSrc = `http://localhost:9999/videos/${this.nowRoutineList[newVal].v_id}`;
        this.$refs.videoPlayer.load();
      } else {
        this.videoSrc = "";
      }
    },
    nowRoutineList(newVal) {
      this.$store.commit("SET_PLAYING_IDX", 0);
      this.videoSrc = `http://localhost:9999/videos/${
        newVal[this.nowPlayingIdx].v_id
      }`;
    },
    $route(to, from) {
      console.log(to);
      if (from.name == "UserViewMypageRoutineDetail") {
        this.isSmall = true;
      }
    },
    isPlaying(newVal) {
      if (newVal) {
        this.$refs.videoPlayer.play();
      } else {
        this.$refs.videoPlayer.pause();
      }
    },
  },
  methods: {
    closeModal() {
      this.$store.commit("SET_PLAYING_IDX", 0);
      this.$store.commit("SET_NOW_ROUTINE_LIST", []);
    },
    videoEnd() {
      console.log("끝?");
      if (this.nowPlayingIdx == this.nowRoutineList.length - 1) {
        console.log("이리옴?");
        this.$store.commit("SET_NOW_ROUTINE_LIST", []);
        this.$store.commit("SET_PLAYING_IDX", 0);
        alert("루틴이 종료되었습니다");
      } else {
        console.log("다음으로!");
        // this.idx = this.idx + 1;

        this.$store.commit("ADD_PLAYING_IDX");
      }
    },
    activeMove(e) {
      this.isActive = true;
      this.startX = e.clientX;
      this.startY = e.clientY;
    },
    moveActiveModal(e) {
      if (this.isActive) {
        let lastX = this.startX - e.clientX;
        let lastY = this.startY - e.clientY;

        const modal = this.$refs.videoModal;
        modal.style.left = `${modal.offsetLeft - lastX}px`;
        modal.style.top = `${modal.offsetTop - lastY}px`;

        this.startX = e.clientX;
        this.startY = e.clientY;
      }
    },
  },
  created() {
    this.videoSrc = `http://localhost:9999/videos/${
      this.nowRoutineList[this.nowPlayingIdx].v_id
    }`;
  },
  destroyed() {},
};
</script>

<style scoped>
h2 {
  font-size: 3rem;

  text-align: center;
  padding: 1rem;
}

h2 > span {
  font-size: 2rem;
  float: right;
  margin-right: 2rem;

  font-weight: normal;
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;

  border: 0;
  font-size: 2rem;
  background: inherit;

  cursor: pointer;
}

.video-modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  max-width: 80%;
  max-width: 900px;

  min-width: 200px;
  min-height: 200px;

  z-index: 9998;
  /* margin: auto; */
  padding: 20px 30px;
  background-color: #fff;
  border: 1px solid black;

  display: flex;
  flex-direction: column;

  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

.video-modal.movable {
  position: fixed;
  background: rgba(219, 214, 214, 0.836);
  /* background: red; */
}

.video-modal.small-dude {
  width: 20%;
  max-width: 200px;
  max-height: 200px;

  height: auto;

  position: fixed;
  bottom: 0;
  right: 0;

  /* transform: translate(-50%, -50%); */
  transform: none;
  padding: 0;
}

.video-modal.small-dude .close {
  color: white;
  display: none;
  z-index: 9999;
}

.video-modal.small-dude:hover .close {
  display: inline;
}

.video-modal.small-dude h2 {
  display: none;
}

/* .video-modal.small-dude  */
.go-big-btn {
  position: absolute;
  top: 1rem;
  left: 1rem;
  z-index: 9999;

  font-size: 3rem;

  border: 0;
  background: inherit;
}

.video-modal.small-dude .go-big-btn {
  color: white;
  font-size: 2rem;
  display: none;
}

.video-modal.small-dude:hover .go-big-btn {
  display: inline;
}

.video-modal.small-dude video {
  /* width: 100%; */
  width: 200px;
  height: 200px;
}

.control-btns button {
  font-size: 2rem;
  margin: 0.3rem;
}

/* .video-modal.small-dude .control-btns{
  position: absolute;
  z-index: 9999;

  font-size: 2rem;
  background: inherit;
} */
</style>
