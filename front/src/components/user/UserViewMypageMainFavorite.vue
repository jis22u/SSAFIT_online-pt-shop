<template>
  <div id="favorite-list">
    <h2>FAVORITE LIST</h2>
    <div class="box" v-if="favoriteVideos.length">
      <!-- <div v-for="(video, idx) in favoriteVideos" :key="idx">
        {{ video }}
      </div> -->
      <CarouselComponent :list="favoriteVideos" />
    </div>
    <div class="box" v-else>선호하는 영상이 없습니다!</div>
  </div>
</template>

<script>
import CarouselComponent from "../common/CarouselComponent.vue";

export default {
  name: "UserViewMypageMainFavorite",
  props: {
    favoriteVideos: Array,
  },
  components: {
    CarouselComponent,
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
h2 {
  font-family: "Montserrat", sans-serif;
  text-align: center;
  padding: 1rem;
  font-size: 3.5em;
}
#favorite-list {
  padding: 2em;
  margin-top: 40px;
}
.box {
  height: 500px;
  margin-top: 10%;
  text-align: center;
}
</style>
