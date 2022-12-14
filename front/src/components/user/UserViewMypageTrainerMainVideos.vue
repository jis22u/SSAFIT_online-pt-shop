<template>
  <div id="my-video-list">
    <h2>내가 올린 비디오 리스트</h2>
    <div class="box" v-if="videos.length">
      <CarouselComponent :list="videos"></CarouselComponent>
    </div>
    <div class="box" v-else>내가 올린 영상이 없습니다!</div>
  </div>
</template>

<script>
import CarouselComponent from "../common/CarouselComponent.vue";

export default {
  name: "UserViewMypageTrainerMainVideos",
  props: {
    videos: Array,
  },
  components: {
    CarouselComponent,
  },
};
</script>

<style scoped>
h2 {
  text-align: center;
  padding: 1rem;
}

#my-video-list {
  padding: 2em;
  margin-top: 40px;
}
.box {
  height: 500px;
  text-align: center;
}
</style>
