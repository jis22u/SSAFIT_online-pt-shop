<template>
  <div class="backimg">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  created() {
    // if (!sessionStorage.getItem("access-token")) {
    //   alert("로그아웃 되었습니다 - 로그인 페이지로 이동합니다.");
    //   this.$router.push("/login");
    // }
  },
};
</script>

<style scoped>
.backimg {
  /* background-image: url("@/assets/images/dumbbell.jpg");
  filter: blur(10px);
  -webkit-filter: blur(1px);
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  filter: grayscale(100%); 
  position: relative;
  z-index: 2; */
}
</style>
