<template>
  <div class="total">
    <div v-if="curUser.u_id">
      <!-- 그냥 curUser 를 찍으면 obsever가 찍히므로 작동이 제대로 되지 않는다 -->
      <promotion-row-three :purposeVideos="purposeVideos"></promotion-row-three>
      <promotion-row-four :trainerVideos="trainerVideos"></promotion-row-four>
    </div>
    <promotion-row-one></promotion-row-one>
    <promotion-row-two></promotion-row-two>
  </div>
</template>

<script>
import PromotionRowOne from "./HomeViewMainPagePromotionRowOne.vue";
import PromotionRowTwo from "./HomeViewMainPagePromotionRowTwo.vue";
import PromotionRowThree from "./HomeViewMainPagePromotionRowThree.vue";
import PromotionRowFour from "./HomeViewMainPagePromotionRowFour.vue";
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "MainPagePromotion",
  components: {
    PromotionRowOne,
    PromotionRowTwo,
    PromotionRowThree,
    PromotionRowFour,
  },
  data() {
    return {
      curUser: {},
      trainerVideos: [],
      purposeVideos: [],
      userId: sessionStorage.getItem("myId"),
    };
  },
  // myUser의 변화를 감시
  computed: {
    ...mapState(["myUser"]),
  },
  watch: {
    // myUser가 바뀌는 것을 감시하고 있다가
    // myUser가 바뀌면
    myUser() {
      // 아래의 것들을 수행한다.
      this.userId = sessionStorage.getItem("myId");
      this.curUser = this.myUser;
      this.getTrainerVideos();
      this.getPurposeVideos();
    },
  },
  methods: {
    getTrainerVideos() {
      console.log(this.userId);
      axios({
        url: `trainerApi/${this.userId}`,
        method: "GET",
      })
        .then((res) => {
          console.log(res.data);
          if (res.data) this.trainerVideos = res.data;
        })
        .catch((err) => {
          err;
        });
    },
    getPurposeVideos() {
      axios({
        url: `videoApi/purposeVideo/${this.userId}`,
        method: "GET",
      })
        .then((res) => {
          console.log(res.data);
          if (res.data !== "") this.purposeVideos = res.data;
        })
        .catch((err) => {
          err;
        });
    },
  },
};
</script>

<style scoped>
.total {
  padding-top: 200px;
}
</style>
