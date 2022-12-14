<template>
  <div>
    <iframe
      :src="`https://www.youtube.com/embed/${video.id.videoId}`"
      frameborder="0"
      width="500px"
      height="300px"></iframe>
    <p>채널명 : {{ video.snippet.channelTitle }}</p>
    <p>제목 : {{ video.snippet.title }}</p>
  </div>
</template>

<script>
export default {
  name: "TrainerViewDetailYoutubeRow",
  props: {
    video: Object,
  },
};
</script>

<style scoped>
.row {
  width: 300px;
  height: 350px;
}
</style>
