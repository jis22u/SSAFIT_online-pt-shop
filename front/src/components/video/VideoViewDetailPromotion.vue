<template>
  <div>
    <div class="title">{{ t_name }}의 온라인 PT</div>
    <div class="container">
      <div class="card-carousel-wrapper">
        <div
          class="card-carousel--nav__left"
          @click="moveCarousel(-1)"
          :disabled="atHeadOfList"></div>
        <div class="card-carousel">
          <div class="card-carousel--overflow-container">
            <div
              class="card-carousel-cards"
              :style="{
                transform: 'translateX' + '(' + currentOffset + 'px' + ')',
              }">
              <div
                class="card-carousel--card"
                v-for="video in trainerVideos"
                :key="video.id">
                <video-view-detail-promotion-card
                  class="card-carousel--card--footer"
                  :video="video">
                </video-view-detail-promotion-card>
              </div>
            </div>
          </div>
        </div>
        <div
          class="card-carousel--nav__right"
          @click="moveCarousel(1)"
          :disabled="atEndOfList"></div>
      </div>
    </div>
  </div>
</template>

<script>
import VideoViewDetailPromotionCard from "./VideoViewDetailPromotionCard.vue";
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "VideoViewDetailPromotion",
  components: {
    VideoViewDetailPromotionCard,
  },
  data() {
    return {
      trainerVideos: [],
      t_name: "",
      v_id: "",
      IMG_URL: this.$store.state.IMG_URL,

      currentOffset: 0,
      windowSize: 3,
      paginationFactor: 450,
      items: [
        { name: "Tycoon Thai", tag: "Thai" },
        { name: "Ippudo", tag: "Japanese" },
        { name: "Milano", tag: "Pizza" },
        { name: "Tsing Tao", tag: "Chinese" },
        { name: "Frances", tag: "French" },
        { name: "Burma Superstar", tag: "Burmese" },
        { name: "Salt and Straw", tag: "Ice cream" },
        { name: "이게 없으면 안되네", tag: "Ice cream" },
        { name: "안되네 ", tag: "Ice cream" },
        { name: "End page ", tag: "Ice cream" },
        { name: "Read More ", tag: "Ice cream" },
      ],
    };
  },
  computed: {
    ...mapState(["video"]),
    // filters() {
    //   return this.trainerVideos((item) => item.v_id !== this.v_id);
    // },
    atEndOfList() {
      return (
        this.currentOffset <=
        this.paginationFactor * -1 * (this.items.length - this.windowSize)
      );
    },
    atHeadOfList() {
      return this.currentOffset === 0;
    },
  },
  watch: {
    video() {
      this.t_name = this.video.t_name;
      this.v_id = this.video.v_id;
      this.getTrainerVideos();
      console.log(">>>>>>>>>" + this.video);
    },
  },
  methods: {
    getTrainerVideos() {
      axios({
        url: `trainerApi/trainerVideos`,
        method: "GET",
        params: {
          t_name: this.t_name,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.trainerVideos = res.data.filter(
            (element) => element.v_id !== this.v_id
          );
        })
        .catch((err) => {
          console.log(err);
        });
    },
    moveCarousel(direction) {
      // Find a more elegant way to express the :style. consider using props to make it truly generic
      if (direction === 1 && !this.atEndOfList) {
        this.currentOffset -= this.paginationFactor;
      } else if (direction === -1 && !this.atHeadOfList) {
        this.currentOffset += this.paginationFactor;
      }
    },
  },
};
</script>

<style scoped>
/* .container {
  display: flex;
  flex-flow: row wrap;
  justify-content: space-evenly;
}
.item {
  border: 1px gray solid;
  width: 300px;
  height: 250px;
} */
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
body {
  background: #f8f8f8;
  color: #2c3e50;
  font-family: "Source Sans Pro", sans-serif;
  min-height: 1000px;
}

.title {
  font-size: 2em;
  padding-left: 8%;
  font-family: "Do Hyeon", sans-serif;
  font-weight: 100;
}

.container {
  margin-bottom: 200px;
  font-size: 30px;
}

.card-carousel-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 50px 0 50px;
  color: #666a73;
}

.card-carousel {
  display: flex;
  justify-content: center;
  width: 1150px;
}
.card-carousel--overflow-container {
  overflow: hidden;
}
.card-carousel--nav__left,
.card-carousel--nav__right {
  display: inline-block;
  width: 15px;
  height: 15px;
  padding: 10px;
  box-sizing: border-box;
  border-top: 2px solid #42b883;
  border-right: 2px solid #42b883;
  cursor: pointer;
  margin: 0 10px;
  transition: transform 10ms linear;
}
.card-carousel--nav__left[disabled],
.card-carousel--nav__right[disabled] {
  opacity: 0.2;
  border-color: black;
}
.card-carousel--nav__left {
  transform: rotate(-135deg);
}
.card-carousel--nav__left:active {
  transform: rotate(-135deg) scale(0.9);
}
.card-carousel--nav__right {
  transform: rotate(45deg);
}
.card-carousel--nav__right:active {
  transform: rotate(45deg) scale(0.9);
}

.card-carousel-cards {
  display: flex;
  transition: transform 150ms ease-out;
  transform: translatex(0px);
}
.card-carousel-cards .card-carousel--card {
  margin: 0 10px;
  cursor: pointer;
  box-shadow: 0 4px 15px 0 rgba(40, 44, 53, 0.06),
    0 2px 2px 0 rgba(40, 44, 53, 0.08);
  background-color: #fff;
  border-radius: 4px;
  z-index: 3;
  margin-bottom: 2px;
  /* width: calc(33.333%); */
}
.card-carousel-cards .card-carousel--card:first-child {
  margin-left: 0;
}
.card-carousel-cards .card-carousel--card:last-child {
  margin-right: 0;
}
.card-carousel-cards .card-carousel--card img {
  vertical-align: bottom;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  transition: opacity 150ms linear;
  user-select: none;
}
.card-carousel-cards .card-carousel--card img:hover {
  opacity: 0.5;
}
.card-carousel-cards .card-carousel--card--footer {
  border-top: 0;
  padding: 7px 15px;
}
.card-carousel-cards .card-carousel--card--footer p {
  padding: 3px 0;
  margin: 0;
  margin-bottom: 2px;
  font-size: 19px;
  font-weight: 500;
  color: #2c3e50;
  user-select: none;
}
.card-carousel-cards .card-carousel--card--footer p:nth-of-type(2) {
  font-size: 12px;
  font-weight: 300;
  padding: 6px;
  background: rgba(40, 44, 53, 0.06);
  display: inline-block;
  position: relative;
  margin-left: 4px;
  color: #666a73;
}
.card-carousel-cards .card-carousel--card--footer p:nth-of-type(2):before {
  content: "";
  float: left;
  position: absolute;
  top: 0;
  left: -12px;
  width: 0;
  height: 0;
  border-color: transparent rgba(40, 44, 53, 0.06) transparent transparent;
  border-style: solid;
  border-width: 12px 12px 12px 0;
}
.card-carousel-cards .card-carousel--card--footer p:nth-of-type(2):after {
  content: "";
  position: absolute;
  top: 10px;
  left: -1px;
  float: left;
  width: 4px;
  height: 4px;
  border-radius: 2px;
  background: white;
  box-shadow: 0px 0px 0px #004977;
}

h1 {
  font-size: 3.6em;
  font-weight: 100;
  text-align: center;
  margin-bottom: 0;
  color: #42b883;
}
</style>
