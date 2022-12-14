<template>
  <div class="utube-flex">
    <!-- <div class="title">맛보기 영상</div> -->
    <div v-for="video in videos" :key="video.id.v_id">
      <trainer-view-detail-youtube-row
        :video="video"></trainer-view-detail-youtube-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import { mapState } from "vuex";
import TrainerViewDetailYoutubeRow from "./TrainerViewDetailYoutubeRow.vue";

export default {
  data() {
    return {
      videos: [],
    };
  },
  components: {
    TrainerViewDetailYoutubeRow,
  },
  computed: {
    // ...mapState(["trainer"]),
  },
  watch: {
    trainer() {
      this.searchYoutube(this.trainer.t_name);
    },
  },
  props: { trainer: Object },
  methods: {
    searchYoutube(value) {
      console.log("나 출발한다", value);

      const API_URL = `https://www.googleapis.com/youtube/v3/search`;
      const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
      axios({
        url: API_URL,
        method: "GET",
        params: {
          key: YOUTUBE_KEY,
          part: "snippet",
          q: value,
          type: "video",
          maxResults: 8,
        },
      })
        .then((res) => {
          this.videos = res.data.items;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(value);
          console.log(err);
        });
    },
  },
  created() {
    this.searchYoutube(this.trainer.t_name);
    console.log(this.trainer);
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

.utube-flex {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.title {
  font-family: "Do Hyeon", sans-serif;
  font-size: 1em;
}
</style>
