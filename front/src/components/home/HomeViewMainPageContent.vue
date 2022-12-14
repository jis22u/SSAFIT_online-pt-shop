<template>
  <div>
    <span class="material-symbols-outlined">
      <img src="@/assets/images/mainicon.png" />SSAFIT</span
    >
    <div class="container">
      <main-page-content-one></main-page-content-one>
      <div class="text">언제 어디서든 <br />운동이 하고 싶다면,</div>
    </div>
    <div class="container">
      <div class="text">내가 원하는 수업으로<br />골라 듣고 싶다면,</div>
      <main-page-content-two></main-page-content-two>
    </div>
  </div>
</template>

<script scoped>
import MainPageContentOne from "./HomeViewMainPageContentOne.vue";
import MainPageContentTwo from "./HomeViewMainPageContentTwo.vue";
export default {
  name: "MainPageContent",
  components: {
    MainPageContentOne,
    MainPageContentTwo,
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap");
.container {
  display: flex;
  flex-direction: row wrap;
  justify-content: space-evenly;
}
.text {
  padding-top: 150px;
  font-family: "Black Han Sans", sans-serif;
  font-size: 40px;
}
.material-symbols-outlined {
  padding-top: 40px;
  padding-bottom: 50px;
  padding-left: 45%;
}
</style>
