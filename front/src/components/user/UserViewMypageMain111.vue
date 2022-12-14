<template>
  <div id="mypage-main">
    <div id="user-info">
      <!-- 프로필 -->
      <div>
        <div class="container">
          <div class="profile">
            <div class="profile-image">
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

            <div class="profile-user-settings">
              <h1 class="profile-user-name">{{ myUser.nickname }}</h1>
              <div class="many-buttons">
                <button class="btn profile-edit-btn" @click="goUserUpdate">
                  수정하기
                </button>
                <button
                  v-if="myUser.t_name"
                  class="btn profile-edit-btn"
                  @click="goOtherLocation('UserViewMypageTrainerMain')">
                  트레이너 정보
                </button>
                <button
                  v-else
                  class="btn profile-edit-btn"
                  @click="goOtherLocation('UserViewMypageTrainerRegist')">
                  트레이너 등록
                </button>
                <button
                  class="btn profile-settings-btn"
                  aria-label="profile settings">
                  <i class="fas fa-cog" aria-hidden="true"></i>
                </button>
              </div>
            </div>

            <div class="profile-stats">
              <ul class="follow-stats">
                <li @click="changeUserList(followers)">
                  <span class="profile-stat-count">
                    {{ followers.length }}
                  </span>
                  followers
                </li>
                <li @click="changeUserList(follows)">
                  <span class="profile-stat-count">
                    {{ follows.length }}
                  </span>
                  following
                </li>
              </ul>
              <li>
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
              </li>
            </div>
          </div>
          <!-- End of profile section -->
        </div>
        <!-- End of container -->
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
          console.log(res);
          if (res.status == 200) {
            this.changeUserList(res.data);
          }
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
    // getFavoriteList() {
    //   axios({
    //     url: "favoriteApi/",
    //     params: { u_id: this.myUser.u_id },
    //     method: "GET",
    //   }).then((res) => {
    //     if (res.data) this.favoriteVideos = res.data;
    //   });
    // },
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
      }, 4000);
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
img {
  display: block;
  margin-top: 4em;
}

h1 {
  text-align: center;
  display: block;
  width: 100%;
}

#user-info {
}

.big-profile {
  margin: 4em;
  width: 12em;
  height: 12em;
  border-radius: 50%;
}

.container {
  max-width: 60.5rem;
  margin: 4em auto;
  padding: 0 1rem;
  border-radius: 2em;
  box-shadow: 5px 5px gray;
}

.btn {
  display: inline-block;
  font: inherit;
  background: none;
  border: none;
  color: inherit;
  padding: 0;
  cursor: pointer;
}

.btn:focus {
  outline: 0.5rem auto #4d90fe;
}

.visually-hidden {
  position: absolute !important;
  height: 1px;
  width: 1px;
  overflow: hidden;
  clip: rect(1px, 1px, 1px, 1px);
}

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

.profile-user-settings {
  margin-top: 1.1rem;
}

.profile-user-name {
  display: inline-block;
  font-size: 3.2rem;
  font-weight: 300;
}

.profile-edit-btn {
  font-size: 1.2rem;
  line-height: 1.8;
  border: 0.1rem solid #dbdbdb;
  border-radius: 0.3rem;
  padding: 0 2.4rem;
  margin-left: 2rem;
}

.profile-settings-btn {
  font-size: 2rem;
  margin-left: 1rem;
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

.profile-stats li:last-of-type {
  margin-right: 0;
}

.profile-bio {
  font-size: 1.6rem;
  font-weight: 400;
  line-height: 1.5;
  margin-top: 2.3rem;
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
  margin-left: 3rem;

  text-align: center;
}

.user-list {
  background: #fff;
  border-radius: 2px;
  list-style: none;
  padding: 10px 20px;
  /* width: 100%; */
  margin-left: 3rem;

  position: absolute;
  z-index: 8999;

  max-height: 500px;
  overflow: auto;
  overflow-x: hidden;
}

.hidden-input {
  width: 0;
}

.many-buttons {
  display: flex;
  flex-direction: row;

  margin-top: 20px;
}

.follow-stats {
  display: flex;
  flex-direction: row;
  justify-content: space-around;

  margin-bottom: 2em;
}
</style>
