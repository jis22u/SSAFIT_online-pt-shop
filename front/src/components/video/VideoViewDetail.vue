<template>
  <div class="backimg">
    <div class="crop">
      <img class="top-img" :src="getSrc()" />
    </div>
    <br />
    <video-view-detail-content class="box1"></video-view-detail-content>

    <video-view-detail-promotion></video-view-detail-promotion>
    <video-view-detail-comments></video-view-detail-comments>
  </div>
</template>

<script>
import VideoViewDetailContent from "./VideoViewDetailContent.vue";
import VideoViewDetailComments from "./VideoViewDetailComments.vue";
import VideoViewDetailPromotion from "./VideoViewDetailPromotion.vue";
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  data() {
    return {
      imgs: this.$store.state.detailIMG,
      imgURL: "",
    };
  },
  components: {
    VideoViewDetailContent,
    VideoViewDetailPromotion,
    VideoViewDetailComments,
  },
  created() {
    this.getSrc();
    let id = this.$route.params.id;
    axios
      .get(`videoApi/video/${id}`)
      .then((res) => this.$store.commit("SET_VIDEO", res.data))
      .then(() => {
        axios
          .get(`commentApi/`, { params: { v_id: this.video.v_id } })
          .then((res) => this.$store.commit("SET_COMMENTS", res.data));
      })
      .then(() => {
        axios
          .get(`favoriteApi/favoritePeople`, {
            params: {
              v_id: this.video.v_id,
            },
          })
          .then((res) => {
            console.log(res.data);
            if (!res.data) {
              this.$store.commit("SET_VIDEO_FAVORITE_USERS", []);
            } else {
              this.$store.commit("SET_VIDEO_FAVORITE_USERS", res.data);
            }
          });
      });
  },
  computed: {
    ...mapState(["video", "comments", "videoFavoriteUsers"]),
  },

  methods: {
    getSrc() {
      const i = Math.floor(Math.random() * 10);
      console.log(this.imgs[i]);
      this.imgURL = this.imgs[i];
      return require("@/assets/images/" + this.imgURL + ".jpg");
    },
  },
};
</script>

<style scoped>
.box1 {
  margin-bottom: 10em;
}
.crop {
  position: relative;
  width: 100%;
  height: 500px;
  overflow: hidden;
}
.top-img {
  opacity: 0.8;
  margin: -180px 0px 0px -10px;
  /* position: absolute;
  top: 0;
  left: 0;
  width: 1000px;
  height: 200px; */
}
</style>
