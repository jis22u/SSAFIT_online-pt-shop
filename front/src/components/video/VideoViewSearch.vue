<template>
  <div>
    <h3>원하는 온라인 PT를 검색해보세요</h3>
    <br />
    <video-view-search-input></video-view-search-input>
    <video-view-search-result></video-view-search-result>
  </div>
</template>

<script>
import VideoViewSearchInput from "./VideoViewSearchInput.vue";
import VideoViewSearchResult from "./VideoViewSearchResult.vue";
export default {
  name: "VideoViewSearch",
  components: {
    VideoViewSearchInput,
    VideoViewSearchResult,
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
h3 {
  font-family: "Do Hyeon", sans-serif;
  text-align: center;
  font-size: 250%;
}
</style>
