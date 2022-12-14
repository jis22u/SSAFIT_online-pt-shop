<template>
  <div>
    <div class="row" @click="moveDetail(comp.v_id)">
      <!-- :style="{ background: 'url(' + IMG_URL + comp.v_id + '.png)' }" -->
      <img :src="`${IMG_URL}${comp.v_id}.png`" width="300px" height="250" />
      <div class="detail-box">
        <!-- <p>{{ comp.t_name }}</p> -->
        <p>
          {{ comp.title }}
        </p>
        <!-- <p>{{ comp.price }}</p> -->
        <!-- <p>{{ comp.category }}</p> -->

        <!-- <span><i @click="addCart(comp)" class="bi bi-cart"></i></span> |
      <span><i class="bi bi-eye"></i> {{ comp.view_cnt }}</span>
      |
      <span
        ><i class="bi bi-heart-fill" style="color: red"> </i
        >{{ comp.favorite_cnt }}</span
      >
      |
      <span> <i class="bi bi-heart"></i> {{ comp.favorite_cnt }} </span> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CarouselComponentCard",
  props: { comp: Object },
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
  created() {
    console.log(this.comp);
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
.row {
  height: 300px;
}
.detail-box p {
  text-align: center;
  color: black;
  font-size: 15px;
  font-family: "Do Hyeon", sans-serif;
  text-overflow: ellipsis;
  overflow: hidden;
  width: 300px;
  white-space: nowrap;
}
</style>
