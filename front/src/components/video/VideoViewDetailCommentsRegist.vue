<template>
  <div>
    <div class="commentbox-app" style="font-size: 0.8em; margin-left: -25%">
      <div class="container">
        <h1 class="heading">commentbox</h1>
        <div class="clearfix">
          <div id="comment-form">
            <h4>{{ myUser.nickname }}</h4>
            <div class="flex-box">
              <textarea
                id="comment-input"
                class="comment-input"
                v-model="curComment"
                placeholder="Thoughts"
                cols="30"
                rows="3" />
              <button class="comment-btn" @click="registComment">Post</button>
            </div>
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
  name: "ViewDetailCommentsRegist",
  data() {
    return {
      curComment: "",
    };
  },
  methods: {
    registComment() {
      axios
        .post(`commentApi/`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.video.v_id,
            content: this.curComment,
          },
        })
        .then(() => {
          axios
            .get(`commentApi/`, { params: { v_id: this.video.v_id } })
            .then((res) => this.$store.commit("SET_COMMENTS", res.data));
        })
        .then(() => (this.curComment = ""));
    },
  },
  computed: {
    ...mapState(["API_URL", "myUser", "video", "comments"]),
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.clearfix::after {
  content: "";
  display: table;
  clear: both;
}

textarea::placeholder {
  padding-top: 25px;
  font-size: 2em;
}

.commentbox-app {
  font-family: "Montserrat", sans-serif;
  color: #4a4a4a;
  font-size: 45px;
}
.commentbox-app .container {
  display: block;
  max-width: 1120px;
  margin-left: 25%;
  padding: 0 10px;
}
.commentbox-app .heading {
  color: black;
  border-left: 3px solid black;
  line-height: 1;
  padding-left: 12px;
}

.flex-box {
  display: flex;
}

.commentbox-app .comment-input,
.commentbox-app .comment-btn {
  /* float: left; */
  /* display: inline-block; */
  height: 110px;
}
.commentbox-app .comment-input {
  appearance: none;
  border: 0;
  border-left: 3px solid transparent;
  background-color: #eee;
  padding: 10px 10px 10px 50px;
  transition: 0.65s ease;
  width: 1200px;
}
.commentbox-app .comment-input:focus {
  border-color: orange;
  background-color: #fff;
}
.commentbox-app .comment-btn {
  appearance: none;
  background-color: #fff;
  font-size: 30px;
  text-decoration: none;
  color: orange;
  padding: 10px 32px;
  line-height: 1.5;
  transition: 0.65s ease;
}
.commentbox-app .comment-btn,
.commentbox-app .comment-btn:focus,
.commentbox-app .comment-btn:active {
  border: 0;
}
.commentbox-app .comment-btn:hover {
  background-color: orange;
  color: #fff;
}
.commentbox-app .comment-btn:hover + .comment-input {
  background-color: #fff;
  border-color: orange;
}
</style>
