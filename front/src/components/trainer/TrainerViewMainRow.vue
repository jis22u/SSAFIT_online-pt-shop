<template>
  <div @click="moveDetail">
    <img :src="`${IMG_URL}${trainer.profile}`" />
    <p>{{ trainer.t_name }}</p>
    <div v-for="major in majors" :key="major.id">
      <span :major="major">{{ major }}</span>
    </div>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";

export default {
  name: "TrainerViewMainRow",
  data() {
    return {
      majors: [],
    };
  },
  props: {
    trainer: Object,
    IMG_URL: String,
  },
  mounted() {
    axios({
      url: `trainerApi/major`,
      method: "GET",
      params: {
        u_id: this.trainer.u_id,
      },
    })
      .then((res) => {
        console.log(res.data);
        this.majors = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    moveDetail() {
      this.$router.push({
        name: "TrainerViewDetail",
        params: {t_name : this.trainer.t_name},
      });
    },
  },
};
</script>

<style scoped>
div {
  cursor: pointer;
}
img {
  width: 300px;
  height: 300px;
  border-radius: 70%;
  overflow: hidden;
}
</style>
