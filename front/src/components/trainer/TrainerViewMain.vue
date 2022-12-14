<template>
  <div>
    <div class="title"><i class="bi bi-gem"></i> SSAFIT의 코치</div>
    <div class="container">
      <div v-for="trainer in trainers" :key="trainer.u_id">
        <trainer-view-main-row
          :IMG_URL="IMG_URL"
          :trainer="trainer"></trainer-view-main-row>
      </div>
    </div>
  </div>
</template>

<script>
import TrainerViewMainRow from "./TrainerViewMainRow.vue";
import axios from "@/util/http-common.js";
// import { mapState } from "vuex";

export default {
  name: "TrainerViewMain",
  components: {
    TrainerViewMainRow,
  },
  // 얘도 가능....
  // computed: {
  //   ...mapState(["IMG_URL"]),
  // },
  data() {
    return {
      trainers: [],
      IMG_URL: this.$store.state.IMG_URL,
    };
  },
  mounted() {
    axios({
      url: `trainerApi/`,
      method: "GET",
      params: {
        t_name: "",
      },
    })
      .then((res) => {
        console.log(res.data);
        this.trainers = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

.title {
  font-family: "Do Hyeon", sans-serif;
  font-size: 2em;
  margin-left: 10%;
  margin-bottom: 5%;
}
.container {
  display: flex;
  flex-flow: row wrap;
  justify-content: space-between;

  flex-wrap: wrap;
  margin: 3rem;
}

.container > div {
  width: 20rem;
}
</style>
