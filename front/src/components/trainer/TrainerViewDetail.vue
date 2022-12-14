<template>
  <div>
    <!-- <the-test></the-test> -->
    <trainer-view-detail-content
      :trainer="trainer"></trainer-view-detail-content>
    <trainer-view-detail-youtube
      :trainer="trainer"></trainer-view-detail-youtube>
  </div>
</template>

<script>
// import TheTest from "./TheTest.vue";
import TrainerViewDetailContent from "./TrainerViewDetailContent.vue";
import TrainerViewDetailYoutube from "./TrainerViewDetailYoutube.vue";
import axios from "@/util/http-common.js";
import { mapState } from "vuex";

export default {
  components: {
    // TheTest,
    TrainerViewDetailContent,
    TrainerViewDetailYoutube,
  },
  data() {
    return {
      trainer: {},
    };
  },
  created() {
    let t_name = this.t_name;
    axios({
      url: `trainerApi/trainerOne/`,
      method: "GET",
      params: {
        t_name: t_name,
      },
    })
      .then((res) => {
        // console.log(res.data);
        if (res.data) this.trainer = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  computed: {
    ...mapState(["trainerMajor"]),
  },
  props: ["t_name"],
};
</script>

<style scoped></style>
