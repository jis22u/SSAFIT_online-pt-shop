<template>
  <div class="trainer_content">
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
                class="big-profile"
              />

              <input
                id="profile-input"
                ref="profileImg"
                class="hidden-input"
                @change="updateUserProfile"
                type="file"
              />
            </div>

            <div class="profile-user-settings">
              <h1 class="profile-user-name">{{ myUser.t_name }}</h1>
              <div class="many-buttons">
                <router-link
                  class="btn profile-edit-btn"
                  :to="{
                    name: 'UserViewMypageTrainerUpload',
                    params: { t_name: this.myUser.t_name },
                  }"
                >
                  영상등록
                </router-link>
                <router-link
                  class="btn profile-edit-btn"
                  :to="{ name: 'UserViewMypageMain' }"
                >
                  내 페이지
                </router-link>

                <button
                  class="btn profile-settings-btn"
                  aria-label="profile settings"
                >
                  <i class="fas fa-cog" aria-hidden="true"></i>
                </button>
              </div>
            </div>

            <div class="profile-stats">
              <ul>
                <li>
                  정산금액
                  <span class="profile-stat-count"> {{ totalIncome }}</span>
                </li>
              </ul>
              <li>
                <div>
                  <span class="profile-stat-count"> </span>
                </div>
              </li>
            </div>
          </div>
          <!-- End of profile section -->
        </div>
        <!-- End of container -->
      </div>
    </div>
    <UserViewMypageTrainerMainVideos :videos="videos" />
  </div>
</template>

<script>
import axios from "@/util/http-common.js";
import UserViewMypageTrainerMainVideos from "@/components/user/UserViewMypageTrainerMainVideos.vue";
export default {
  name: "UserViewMypageTrainerMain",
  data() {
    return {
      IMG_URL: this.$store.state.IMG_URL,
      videos: [],
      incomes: [],
      totalIncome: 0,
    };
  },
  components: {
    UserViewMypageTrainerMainVideos,
  },
  computed: {
    myUser() {
      return this.$store.getters.myUser;
    },
  },
  methods: {
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
    registVideo() {
      console.log("ㅎㅎ");
    },
    getMyVideos() {
      axios({
        method: "GET",
        params: {
          t_name: this.myUser.t_name,
        },
        url: "trainerApi/video",
      })
        .then((res) => {
          console.log(res.data);
          if (res.data.length) this.videos = res.data;
        })
        .catch((err) => console.log(err));
    },
    getMyIncome() {
      axios({
        method: "GET",
        params: {
          t_name: this.myUser.t_name,
        },
        url: "trainerApi/income",
      })
        .then((res) => {
          console.log(this.myUser.t_name);
          console.log(res.data);
          this.incomes = res.data;

          let total = 0;
          for (let i = 0; i < this.incomes.length; i++) {
            total += this.incomes[i].price;
          }
          this.totalIncome = total;
        })
        .catch((err) => console.log(err));
    },
  },
  created() {
    this.getMyIncome();
    this.getMyVideos();
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
