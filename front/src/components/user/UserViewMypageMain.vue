<template>
  <div>
    <div class="wrapper">
      <div class="profile-card js-profile-card">
        <div class="profile-card__img">
          <img
            @click="changeImg"
            :src="`${IMG_URL}${myUser.profile}`"
            alt=""
            for="profile-input"
            class="big-profile" />

          <input
            id="profile-input"
            ref="profileImg"
            class="hidden-input"
            @change="updateUserProfile"
            type="file" />
        </div>

        <div class="profile-card__cnt js-profile-cnt">
          <div class="profile-card__name">{{ myUser.nickname }}</div>
          <div class="profile-card-inf">
            <div class="profile-card-inf__item">
              <div
                class="profile-card-inf__title"
                @click="changeUserList(followers)">
                {{ followers.length }}
              </div>
              <div class="profile-card-inf__txt">Followers</div>
            </div>

            <div class="profile-card-inf__item">
              <div
                class="profile-card-inf__title"
                @click="changeUserList(follows)">
                {{ follows.length }}
              </div>
              <div class="profile-card-inf__txt"></div>
              Following
            </div>
          </div>

          <div class="profile-card-ctr">
            <button
              class="profile-card__button button--blue js-message-btn"
              @click="goUserUpdate">
              수정하기
            </button>
            <button
              v-if="myUser.t_name"
              class="profile-card__button button--blue js-message-btn"
              @click="goOtherLocation('UserViewMypageTrainerMain')">
              트레이너 정보
            </button>
            <button
              v-else
              class="profile-card__button button--gray"
              @click="goOtherLocation('UserViewMypageTrainerRegist')">
              트레이너 등록
            </button>
          </div>
        </div>

        <div>
          <span class="profile-stat-count">
            <input
              class="user-search"
              v-model="searchNickname"
              type="text"
              @input="selectUserList"
              placeholder="유저검색" />
            <ul class="user-list">
              <!-- <li v-if="selectedUserList.length == 0">
                리스트가 없습니다
              </li> -->
              <UserViewMypageMainUserListRow
                v-for="(user, idx) in selectedUserList"
                :key="idx"
                :user="user"
                :u_id="myUser.u_id"
                :isFollow="checkIfFollow(user.u_id)"
                @toggle-man="getFollowList">
              </UserViewMypageMainUserListRow>
            </ul>
          </span>
        </div>
      </div>
    </div>
    <UserViewMypageMainRoutineList />
    <UserViewMypageMainFavorite :favoriteVideos="myFavoriteList" />
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";
import UserViewMypageMainUserListRow from "@/components/user/UserViewMypageMainUserListRow.vue";
import UserViewMypageMainRoutineList from "@/components/user/UserViewMypageMainRoutineList.vue";
import UserViewMypageMainFavorite from "./UserViewMypageMainFavorite.vue";

export default {
  name: "UserViewMypageMain",
  computed: {
    ...mapState(["myUser", "IMG_URL", "myFavoriteList"]),
  },
  components: {
    UserViewMypageMainUserListRow,
    UserViewMypageMainRoutineList,
    UserViewMypageMainFavorite,
  },
  watch: {
    myUser() {
      // console.log(this.myUser);
      this.getFollowList();
      // this.getFavoriteList();
    },
  },
  data() {
    return {
      followers: [],
      follows: [],
      searchNickname: "",
      selectedUserList: [],
      // favoriteVideos: [],
      timerId: -1,
    };
  },
  methods: {
    selectUserList() {
      const keyword = this.searchNickname;
      if (keyword)
        axios({
          url: "userApi/",
          params: {
            nickname: keyword,
          },
          method: "GET",
        }).then((res) => {
          if (res.data) this.changeUserList(res.data);
        });
    },
    checkIfFollow(u_id) {
      for (let f of this.follows) {
        if (f.u_id === u_id) return true;
      }
      return false;
    },
    deleteUser() {
      if (confirm("진짜로 할거임?")) {
        axios
          .delete(`userApi/delete`, { params: { id: this.myUser.u_id } })
          .then(() => {
            this.$store.commit("CLEAR_ALL");
            // 세션정보 지우기 : 로그아웃
            sessionStorage.removeItem("access-token");
          })
          .then(() => this.$router.push({ name: "MainPageView" }));
      }
    },
    getFollowList() {
      console.log("실행한닷");

      const me = this;
      console.log(this.myUser);
      axios({
        url: "followApi/",
        params: { from_user: this.myUser.u_id },
        method: "GET",
      }).then((res) => {
        me.follows = res.data;
      });

      axios({
        url: "followApi/",
        params: { to_user: this.myUser.u_id },
        method: "GET",
      }).then((res) => {
        me.followers = res.data;
      });
    },
    goUserUpdate() {
      this.$router.push({ name: "UserViewMypageUpdate" });
    },
    goOtherLocation(name) {
      console.log(name, "으로 간다");
      this.$router.push({ name: name });
    },
    changeImg() {
      this.$refs.profileImg.click();
    },
    updateUserProfile() {
      const data = new FormData();
      console.log(this.$refs.profileImg.files[0]);
      data.append("profile", this.$refs.profileImg.files[0]);
      data.append("u_id", sessionStorage.getItem("myId"));
      console.log(data);

      axios({
        url: "userApi/profile",
        data: data,
        method: "PUT",
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
        .then((res) => {
          this.$store.commit("CHANGE_PROFILE", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    changeUserList(array) {
      if (this.timerId != -1) {
        clearTimeout(this.timerId);
      }
      this.selectedUserList = array;
      this.timerId = setTimeout(() => {
        this.selectedUserList = [];
      }, 3000);
    },
  },
  created() {
    if (!sessionStorage.getItem("access-token")) {
      // alert("어딜 감히!!!");
      // this.$router.push({ name: "MainPageView" });
      return;
    } else if (!this.myUser.id && sessionStorage.getItem("myId")) {
      console.log("하이", this.myUser);
      this.$store.dispatch("getUserById", sessionStorage.getItem("myId"));
    }

    this.getFollowList();
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Quicksand:400,500,700&subset=latin-ext");

/* Profile Section */

.profile {
  padding: 5rem 0;
}

.profile::after {
  content: "";
  display: block;
  clear: both;
}

.profile-image {
  float: left;
  width: calc(50% - 1rem);
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-right: 0rem;
}

.profile-image img {
  border-radius: 50%;
}

.profile-user-settings,
.profile-stats,
.profile-bio {
  float: left;
  width: calc(50% - 2rem);
}

.profile-stats {
  margin-top: 2.3rem;
}

.profile-stats li {
  display: inline-block;
  font-size: 1.6rem;
  line-height: 1.5;
  margin-right: 4rem;
  cursor: pointer;
}

.profile-real-name,
.profile-stat-count,
.profile-edit-btn {
  font-weight: 600;
}

/** 내가 만든부분임 */

.user-search {
  font-size: 1.4rem;
  line-height: 1.8;
  border: 0.1rem solid #dbdbdb;
  border-radius: 0.3rem;
  padding: 0 2.4rem;
  margin-left: 11rem;
  font-family: "Quicksand", sans-serif;
  text-align: center;
}

.user-list {
  font-family: "Quicksand", sans-serif;
  background: #fff;
  border-radius: 2px;
  list-style: none;
  padding: 10px 20px;
  /* width: 100%; */
  margin-left: 12rem;

  position: absolute;
  z-index: 8999;

  max-height: 500px;
  overflow: auto;
  overflow-x: hidden;
}

.hidden-input {
  width: 0;
}

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
  min-height: 550px;
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
  margin-top: -55px;
  text-align: center;
  padding: 0 20px;
  /* padding-bottom: 40px; */
  transition: all 0.3s;
}
.profile-card__name {
  font-weight: 700;
  font-size: 40px;
  color: #ffa600;
  /* margin-bottom: 15px; */
}
.profile-card__txt {
  font-size: 18px;
  font-weight: 500;
  color: #324e63;
  /* margin-bottom: 15px; */
}
.profile-card__txt strong {
  font-weight: 700;
}
.profile-card-loc {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20px;
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
  /* margin-top: 35px; */
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
</style>
