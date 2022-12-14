<template>
  <div>
    <div class="wrapper">
      <div class="profile-card js-profile-card">
        <div class="profile-card__img">
          <img 
          :src="`${IMG_URL}${trainer.profile}`" />
        </div>

        <div class="profile-card__cnt js-profile-cnt">
          <div class="profile-card__name">{{ trainer.t_name }}</div>
          <div
            class="profile-card__txt"
            v-for="major in majors"
            :key="major.id">
            <span :major="major">{{ major }}</span>
          </div>

          <div class="profile-card-inf">
            <div class="profile-card-inf__item">
              <div class="profile-card-inf__title">{{ followers.length }}</div>
              <div class="profile-card-inf__txt">Followers</div>
            </div>

            <div class="profile-card-inf__item">
              <div class="profile-card-inf__title">{{follows.length}}</div>
              <div class="profile-card-inf__txt">Following</div>
            </div>

            <div class="profile-card-inf__item">
              <div class="profile-card-inf__title">{{ trainer.career }}</div>
              <div class="profile-card-inf__txt">Career</div>
            </div>

            <!-- <div class="profile-card-inf__item">
              <div class="profile-card-inf__title">85</div>
              <div class="profile-card-inf__txt">Favorite</div>
            </div> -->
          </div>
          <div class="profile-card-social">
            <a
              :href="`https://www.youtube.com/${trainer.youtube}`"
              class="profile-card-social__item link"
              :class="{nope : !trainer.youtube}"
              target="_blank">
              <span class="icon-font">
                <i class="bi bi-youtube"></i>
              </span>
            </a>
            <a
              :href="`https://www.instagram.com/${trainer.insta}`"
              class="profile-card-social__item instagram"
              :class="{nope : !trainer.insta}"
              target="_blank">
              <span class="icon-font">
                <i class="bi bi-instagram"></i>
              </span>
            </a>
          </div>

          <div class="profile-card-ctr">
            <!-- <div v-if="checkIfFavorite()">
              <button
                class="profile-card__button button--blue js-message-btn"
                @click="unfavorite">
                Like
              </button>
            </div>
            <div v-else>
              <button
                class="profile-card__button button--gray"
                @click="favorite">
                Like
              </button>
            </div> -->

            <button @click="followToggle" class="profile-card__button button--orange">Follow</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "TrainerViewDetailContent",
  data() {
    return {
      majors: [],
      followers: [],
      follows: [],
    };
  },
  props:{
    trainer : Object,
  },
  computed: {
    ...mapState(["myUser", "IMG_URL"]),
  },
  methods: {
    // axios의 비동기 방식 때문에 getMajors()를 created로 처리하면 원하는 결과값이 안나온다.
    // watch로 처리하기
    getMajors() {
      axios({
        url: `trainerApi/major`,
        method: "GET",
        params: {
          u_id: this.trainer.u_id,
        },
      })
        .then((res) => {
          console.log(this.trainer.t_name);
          console.log(res.data);
          this.majors = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getFollowers(){
      axios({
        url: "followApi/",
        params: { to_user: this.trainer.u_id },
        method: "GET",
      }).then((res) => {
        this.followers = res.data;
      });
    },
    getFollowCnt() {
      axios({
        url: "followApi/",
        params: { from_user: this.trainer.u_id },
        method: "GET",
      }).then((res) => {
        this.follows = res.data;
      });

      this.getFollowers();
    },
    followToggle() {

      axios
        .post(`followApi/toggle`, null, {
          params: {
            from_user: this.myUser.u_id,
            to_user: this.trainer.u_id,
          },
        })
        .then(() => {
          // 색바꾸기?
          // this.$emit("toggle-man");
          this.getFollowers();
        });
    },
  },
  watch: {
    videoFavoriteUsers() {},
    trainer() {
      this.getMajors();
      this.getFollowCnt();
    },
  },
  created() {
    console.log(this.trainer.t_name);
    this.getMajors();
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Quicksand:400,500,700&subset=latin-ext");
html {
  position: relative;
  overflow-x: hidden !important;
}

* {
  box-sizing: border-box;
}

body {
  font-family: "Quicksand", sans-serif;
  color: #324e63;
}

a,
a:hover {
  text-decoration: none;
}

.icon {
  display: inline-block;
  width: 1em;
  height: 1em;
  stroke-width: 0;
  stroke: currentColor;
  fill: currentColor;
}

.wrapper {
  width: 100%;
  width: 100%;
  height: auto;
  min-height: 100vh;
  padding: 50px 20px;
  padding-top: 10%;
  display: flex;
  display: flex;
}
@media screen and (max-width: 768px) {
  .wrapper {
    height: auto;
    min-height: 100vh;
    padding-top: 100px;
  }
}

.profile-card {
  width: 100%;
  min-height: 460px;
  margin: auto;
  box-shadow: 0px 8px 60px -10px rgba(13, 28, 39, 0.6);
  background: #fff;
  border-radius: 12px;
  max-width: 700px;
  position: relative;
}
.profile-card.active .profile-card__cnt {
  /* filter: blur(6px); */
}
.profile-card.active .profile-card-message,
.profile-card.active .profile-card__overlay {
  /* opacity: 1; */
  pointer-events: auto;
  transition-delay: 0.1s;
}
.profile-card.active .profile-card-form {
  transform: none;
  transition-delay: 0.1s;
}
.profile-card__img {
  width: 250px;
  height: 250px;
  margin-left: auto;
  margin-right: auto;
  transform: translateY(-50%);
  border-radius: 50%;
  overflow: hidden;
  position: relative;
  z-index: 4;
  box-shadow: 0px 5px 50px 0px #fadf8b, 0px 0px 0px 7px #ffd452;
}
@media screen and (max-width: 576px) {
  .profile-card__img {
    width: 120px;
    height: 120px;
  }
}
.profile-card__img img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 50%;
}
.profile-card__cnt {
  margin-top: -35px;
  text-align: center;
  padding: 0 20px;
  padding-bottom: 40px;
  transition: all 0.3s;
}
.profile-card__name {
  font-weight: 700;
  font-size: 24px;
  color: #ffa600;
  margin-bottom: 15px;
}
.profile-card__txt {
  font-size: 18px;
  font-weight: 500;
  color: #324e63;
  margin-bottom: 15px;
}
.profile-card__txt strong {
  font-weight: 700;
}
.profile-card-loc {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 18px;
  font-weight: 600;
}
.profile-card-loc__icon {
  display: inline-flex;
  font-size: 27px;
  margin-right: 10px;
}
.profile-card-inf {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  align-items: flex-start;
  margin-top: 35px;
}
.profile-card-inf__item {
  padding: 10px 35px;
  min-width: 150px;
}
@media screen and (max-width: 768px) {
  .profile-card-inf__item {
    padding: 10px 20px;
    min-width: 120px;
  }
}
.profile-card-inf__title {
  font-weight: 700;
  font-size: 27px;
  color: #324e63;
}
.profile-card-inf__txt {
  font-weight: 500;
  margin-top: 7px;
}
.profile-card-social {
  margin-top: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
}
.profile-card-social__item {
  display: inline-flex;
  width: 55px;
  height: 55px;
  margin: 15px;
  border-radius: 50%;
  align-items: center;
  justify-content: center;
  color: #fff;
  background: #405de6;
  box-shadow: 0px 7px 30px rgba(43, 98, 169, 0.5);
  position: relative;
  font-size: 21px;
  flex-shrink: 0;
  transition: all 0.3s;
}
@media screen and (max-width: 768px) {
  .profile-card-social__item {
    width: 50px;
    height: 50px;
    margin: 10px;
  }
}
@media screen and (min-width: 768px) {
  .profile-card-social__item:hover {
    transform: scale(1.2);
  }
}
.profile-card-social__item.instagram {
  background: linear-gradient(
    45deg,
    #405de6,
    #5851db,
    #833ab4,
    #c13584,
    #e1306c,
    #fd1d1d
  );
  box-shadow: 0px 4px 30px rgba(120, 64, 190, 0.6);
}
.profile-card-social__item.link {
  background: linear-gradient(45deg, #d5135a, #f05924);
  box-shadow: 0px 4px 30px rgba(223, 45, 70, 0.6);
}
.profile-card-social .icon-font {
  display: inline-flex;
}
.profile-card-ctr {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 40px;
}
@media screen and (max-width: 576px) {
  .profile-card-ctr {
    flex-wrap: wrap;
  }
}
.profile-card__button {
  background: none;
  border: none;
  font-family: "Quicksand", sans-serif;
  font-weight: 700;
  font-size: 19px;
  margin: 15px 35px;
  padding: 15px 40px;
  min-width: 201px;
  border-radius: 50px;
  min-height: 55px;
  color: #fff;
  cursor: pointer;
  backface-visibility: hidden;
  transition: all 0.3s;
}
@media screen and (max-width: 768px) {
  .profile-card__button {
    min-width: 170px;
    margin: 15px 25px;
  }
}
@media screen and (max-width: 576px) {
  .profile-card__button {
    min-width: inherit;
    margin: 0;
    margin-bottom: 16px;
    width: 100%;
    max-width: 300px;
  }
  .profile-card__button:last-child {
    margin-bottom: 0;
  }
}
.profile-card__button:focus {
  outline: none !important;
}
@media screen and (min-width: 768px) {
  .profile-card__button:hover {
    transform: translateY(-5px);
  }
}
.profile-card__button:first-child {
  margin-left: 0;
}
.profile-card__button:last-child {
  margin-right: 0;
}
.profile-card__button.button--blue {
  background: linear-gradient(45deg, #f2b91d, #f0c655);
  box-shadow: 0px 4px 30px rgba(212, 199, 19, 0.4);
}
.profile-card__button.button--blue:hover {
  box-shadow: 0px 7px 30px rgba(212, 199, 19, 0.7);
}
.profile-card__button.button--orange {
  background: linear-gradient(45deg, #d5135a, #f05924);
  box-shadow: 0px 4px 30px rgba(223, 45, 70, 0.35);
}
.profile-card__button.button--orange:hover {
  box-shadow: 0px 7px 30px rgba(223, 45, 70, 0.75);
}
.profile-card__button.button--gray {
  box-shadow: none;
  background: #dcdcdc;
  color: #142029;
}
.profile-card-message {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  padding-top: 130px;
  padding-bottom: 100px;
  opacity: 0;
  pointer-events: none;
  transition: all 0.3s;
}

@media screen and (max-width: 768px) {
  .profile-card-form {
    max-width: 90%;
    height: auto;
  }
}
@media screen and (max-width: 576px) {
  .profile-card-form {
    padding: 20px;
  }
}
.profile-card-form__bottom {
  justify-content: space-between;
  display: flex;
}
@media screen and (max-width: 576px) {
  .profile-card-form__bottom {
    flex-wrap: wrap;
  }
}

.profile-card__overlay {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  pointer-events: none;
  opacity: 0;
  background: rgba(22, 33, 72, 0.35);
  border-radius: 12px;
  transition: all 0.3s;
}

.nope{
  background: gray !important;
}
</style>
