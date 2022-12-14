<template>
  <div class="row" @click="moveDetail(video.v_id)">
    <img :src="`${IMG_URL}${video.v_id}.png`" width="300px" height="250" />
    <div class="detail-box">
      <p>{{ video.t_name }}</p>
      <p
        style="
          text-overflow: ellipsis;
          overflow: hidden;
          width: 300px;
          white-space: nowrap;
        ">
        {{ video.title }}
      </p>
      <p>{{ video.price }}</p>
      <span><i class="bi bi-eye"></i> {{ video.view_cnt }}</span> |
      <!-- <span
        ><i class="bi bi-heart-fill" style="color: red"> </i>
        {{ video.favorite_cnt }}</span
      > -->
      <FavoriteIcon :v_id="video.v_id" />
    </div>
  </div>
</template>

<script>
import FavoriteIcon from '../common/FavoriteIcon.vue';

export default {
  name: "VideoViewDetailPromotionCard",
  props: { video: Object },
  data() {
    return {
      IMG_URL: this.$store.state.IMG_URL,
    };
  },
  methods: {
    moveDetail(v_id) {
      this.$router.push({
        name: "VideoViewDetail",
        params: { id: v_id },
      });
    },
  },
  components: {
    FavoriteIcon
  },
  created() {
    console.log(this.comp);
  },
};
</script>

<style scoped>
.row {
  width: 300px;
  height: 400px;
}
.detail-box {
  font-size: 20px;
}
</style>
