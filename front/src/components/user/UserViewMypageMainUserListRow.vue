<template>
  <li class="list-item">
    <div>
      <img class="list-item-image" :src="`${IMG_URL}${user.profile}`" />
    </div>
    <div class="list-item-content">
      <h4>{{ user.nickname }}</h4>
      <p>@{{ user.u_id }}</p>
    </div>
    <div class="favor">
      <i
        :style="{ color: color }"
        @click="followToggle"
        class="bi bi-person-plus-fill"></i>
    </div>
  </li>
</template>
<script>
import axios from "@/util/http-common.js";
export default {
  name: "UserViewMypageUserListRow",
  data() {
    return {
      color: "red",
      IMG_URL: this.$store.state.IMG_URL,
    };
  },
  watch: {
    isFollow() {
      this.checkFollow();
    },
  },
  props: {
    user: Object,
    u_id: String,
    isFollow: Boolean,
  },
  methods: {
    followToggle() {
      console.log("ㄱㄱ");
      axios
        .post(`followApi/toggle`, null, {
          params: {
            from_user: this.u_id,
            to_user: this.user.u_id,
          },
        })
        .then(() => {
          this.$emit("toggle-man");
        });
    },
    checkFollow() {
      if (this.isFollow) {
        this.color = "red";
      } else {
        this.color = "green";
      }
    },
  },
  created() {
    this.checkFollow();
  },
};
</script>

<style scoped>
.list-item {
  display: flex !important;
  flex-direction: row;

  width: 100%;
  margin: 10px;
  padding-bottom: 5px;
  padding-top: 5px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}

/* .list-item > div:first-child {
  width: 20%;
}
.list-item > div:nth-child(2) {
  width: 30%;
} */
.list-item > div:last-child {
  /* width: 20%; */
  border-bottom: none;
}
.list-item-image {
  border-radius: 50%;
  width: 64px;
}
.list-item-content {
  margin-left: 20px;
  margin-right: 20px;
}
.list-item-content h4,
.list-item-content p {
  margin: 0;
}
.list-item-content h4 {
  margin-top: 10px;
  font-size: 15px;
}
.list-item-content p {
  margin-top: 5px;
  font-size: 10px;
  font-weight: normal;
  color: #aaa;
}

.favor {
  font-size: 36px;
}

.notRed {
  color: green;
}
</style>
