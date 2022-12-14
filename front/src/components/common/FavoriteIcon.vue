<template>
  <span>
    <i
      v-if="isFavorited()"
      @click.stop="unfavorite"
      class="bi bi-heart-fill"
      style="color: red">
    </i>
    <i v-else @click.stop="favorite" class="bi bi-heart"></i>
    {{ favorite_cnt }}
  </span>
</template>

<script>
import axios from "@/util/http-common.js";
export default {
  name: "FavoriteIcon",
  data() {
    return {
      favorite_cnt: 0,
    };
  },
  methods: {
    isFavorited() {
      const arr = this.myFavoriteList;

      for (let index = 0; index < arr.length; index++) {
        const element = arr[index];
        if (element.v_id === this.v_id) {
          return true;
        }
      }

      return false;
    },
    favorite() {
      if (!sessionStorage.getItem("access-token")) {
        alert("로그인이 필요한 서비스입니다!");
        return;
      }
      axios
        .post(`favoriteApi/favoriteUp`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.v_id,
          },
        })
        .then(() => {
          this.$store.dispatch("getFavoriteList", this.myUser.u_id);
        });
    },
    unfavorite() {
      if (!sessionStorage.getItem("access-token")) {
        alert("로그인이 필요한 서비스입니다!");
      }
      axios
        .post(`favoriteApi/favoriteDown`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.v_id,
          },
        })
        .then(() => {
          this.$store.dispatch("getFavoriteList", this.myUser.u_id);
        });
    },
    getFavoriteUser() {
      axios
        .get(`favoriteApi/favoritePeople`, {
          params: {
            v_id: this.v_id,
          },
        })
        .then((res) => {
          if (!res.data) {
            this.favorite_cnt = 0;
          } else {
            this.favorite_cnt = res.data.length;
          }
        });
    },
  },
  watch: {
    myFavoriteList() {
      this.getFavoriteUser();
    },
    v_id() {
      this.getFavoriteUser();
    },
  },
  computed: {
    myFavoriteList() {
      return this.$store.getters.myFavoriteList;
    },
    myUser() {
      return this.$store.getters.myUser;
    },
  },
  props: ["v_id"],
  created() {
    this.getFavoriteUser();
  },
};
</script>

<style></style>
