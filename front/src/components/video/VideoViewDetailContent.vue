<template>
  <div class="total">
    <div class="container11">
      <div>
        <p class="parent">{{ video.title }}</p>
        <p class="child">BY {{ video.t_name }}</p>
      </div>
      <div class="icon">
        <div>
          <p>
            <i class="bi bi-eye" style="color: black"></i>
            {{ video.view_cnt }}
          </p>

          <p>
            <FavoriteIcon :v_id="video.v_id" />
          </p>
        </div>
      </div>
    </div>
    <div class="img-box">
      <img
        :src="`${IMG_URL}${video.v_id}.png`"
        frameborder="0"
        width="500px"
        height="300px" />
    </div>
    <div class="container22">
      <div class="plist">
        <p class="st1">카테고리 : {{ video.category }}</p>
        <p class="nd2">가격 : {{ video.price }}</p>
        <p class="rd3">등록일자 : {{ video.reg_date }}</p>
      </div>
      <div class="click-icon">
        <CartIcon :video="video" />
        <br />
        <!-- </button> -->
        <FavoriteIcon :v_id="video.v_id" />
      </div>
    </div>
    <br />
    <video-view-detail-content-intro
      :video="video"></video-view-detail-content-intro>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";
import VideoViewDetailContentIntro from "./VideoViewDetailContentIntro.vue";
import CartIcon from "../common/CartIcon.vue";
import FavoriteIcon from "../common/FavoriteIcon.vue";

export default {
  components: {
    VideoViewDetailContentIntro,
    CartIcon,
    FavoriteIcon,
  },
  data() {
    return {
      IMG_URL: this.$store.state.IMG_URL,
      category: this.video.category,
      childvideo: {},
    };
  },
  computed: {
    ...mapState([
      "video",
      "myUser",
      "videoFavoriteUsers",
      "IMG_URL",
      "myPurchaseList",
    ]),
  },
  watch: {
    // videoFavoriteUsers() {},
    video() {
      this.childvideo = this.video;
    },
  },
  methods: {
    addCart(video) {
      this.$store.commit("ADD_CART", video);
    },
    checkIfFavorite() {
      for (let user of this.videoFavoriteUsers) {
        if (user.u_id === this.myUser.u_id) return true;
      }
      return false;
    },
    isPurchased(v_id) {
      const arr = this.myPurchaseList;

      for (let index = 0; index < arr.length; index++) {
        const element = arr[index];
        if (element.v_id === v_id) {
          return true;
        }
      }

      return false;
    },
    favorite() {
      axios
        .post(`favoriteApi/favoriteUp`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.video.v_id,
          },
        })
        .then(() => this.$store.commit("FAVORITE"));
    },
    unfavorite() {
      axios
        .post(`favoriteApi/favoriteDown`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.video.v_id,
          },
        })
        .then(() => this.$store.commit("UNFAVORITE"));
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

p {
  font-family: "Do Hyeon", sans-serif;
  color: black;
}

.total {
  margin-top: 2%;
}

.container11 {
  display: flex;
  justify-content: center;
}

.parent {
  font-size: 40px;
}
.child {
  font-size: 25px;
  text-decoration-line: underline;
}
.icon {
  padding-left: 3%;
}
.img-box {
  padding-top: 3%;
  text-align: center;
}

.container22 {
  display: flex;
  justify-content: center;
}

.plist {
  margin-right: 15%;
}

.st1,
.nd2,
.rd3 {
  font-size: 20px;
}

.click-icon {
  font-size: x-large;
  cursor: pointer;
}
</style>
