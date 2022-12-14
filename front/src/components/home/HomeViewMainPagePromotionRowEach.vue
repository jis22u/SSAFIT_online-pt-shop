<template>
  <div class="row" @click="moveDetail">
    <img :src="`${IMG_URL}${video.v_id}.png`" width="300px" height="250" />
    <div>
      <p>{{ video.t_name }}</p>
      <p class="special-p">
        {{ video.title }}
      </p>
      <p>{{ video.price }}</p>
      <p>{{ video.category }}</p>
      <div class="icon-box">
        <CartIcon :video="video" />
        |
        <span><i class="bi bi-eye"></i> {{ video.view_cnt }}</span>
        |
        <FavoriteIcon :v_id="video.v_id" />
      </div>
    </div>
  </div>
</template>

<script>
import CartIcon from "../common/CartIcon.vue";
import FavoriteIcon from "../common/FavoriteIcon.vue";

export default {
  name: "PromotionRowEach",
  props: {
    video: Object,
  },
  data() {
    return {
      IMG_URL: this.$store.state.IMG_URL,
    };
  },
  components: {
    CartIcon,
    FavoriteIcon,
  },
  computed: {
    // myPurchaseList() {
    //   return this.$store.getters.myPurchaseList;
    // },
    // myFavoriteList() {
    //   return this.$store.getters.myFavoriteList;
    // },
  },
  methods: {
    // //카트에 물건추가하기
    // addCart(video) {
    //   console.log(video);
    //   this.$store.commit("ADD_CART", video);
    // },
    moveDetail() {
      this.$router.push({
        name: "VideoViewDetail",
        params: { id: this.video.v_id },
      });
    },
    // isFavorited(v_id) {
    //   const arr = this.myFavoriteList;

    //   for (let index = 0; index < arr.length; index++) {
    //     const element = arr[index];
    //     if (element.v_id === v_id) {
    //       return true;
    //     }
    //   }

    //   return false;
    // },
    // isPurchased(v_id) {
    //   const arr = this.myPurchaseList;

    //   for (let index = 0; index < arr.length; index++) {
    //     const element = arr[index];
    //     if (element.v_id === v_id) {
    //       return true;
    //     }
    //   }

    //   return false;
    // },
  },
};
</script>

<style scoped>
.row {
  width: 300px;
  height: 400px;
}

.special-p {
  text-overflow: ellipsis;
  overflow: hidden;
  width: 300px;
  white-space: nowrap;
  font-weight: bold;
  font-size: 1em;
}
p {
  color: black;
}
.icon-box {
  text-align: right;
}
</style>
