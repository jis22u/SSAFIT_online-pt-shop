<template>
  <div>
    <div class="container">
      <i class="bi bi-arrow-return-right"></i>
      <div class="writer">{{ comment.nickname }}</div>
      <div class="clearfix">
        <textarea
          class="comment"
          cols="30"
          rows="3"
          style="border: none"
          v-model="tmpContent"
          :readonly="isReadonly"></textarea>
      </div>
      <div class="here">
        <div class="date">{{ comment.reg_date }}</div>
        <div v-if="myUser.u_id === comment.u_id" class="if">
          <button class="btn" @click="updateComment">수정</button>&nbsp;
          <button class="btn" @click="deleteComment">삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "ViewDetailCommentsRow",
  props: {
    comment: Object,
  },
  data() {
    return {
      tmpContent: "",
      isReadonly: true,
    };
  },
  methods: {
    updateComment() {
      if (this.isReadonly) {
        this.isReadonly = false;
        return;
      }
      this.isReadonly = true;
      axios
        .put(`commentApi/`, null, {
          params: {
            c_id: this.comment.c_id,
            content: this.tmpContent,
          },
        })
        .then(() => {
          console.log(this.video);
          axios
            .get(`commentApi/`, { params: { v_id: this.video.v_id } })
            .then((res) => this.$store.commit("SET_COMMENTS", res.data));
        });
    },
    deleteComment() {
      axios
        .delete(`commentApi/${this.comment.c_id}`)
        .then(() => this.$store.commit("DELETE_COMMENT", this.comment.c_id));
    },
  },
  computed: {
    ...mapState(["myUser", "video"]),
  },
  created() {
    this.tmpContent = this.comment.content;
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}
.container {
  display: flex;
  justify-content: center;
  padding-right: 125px;
  margin-top: 10px;
}

.writer {
  font-family: "Montserrat", sans-serif;
  font-size: 25px;
  margin-right: 20px;
}

.comment {
  font-family: "Montserrat", sans-serif;
  height: 90px;
  appearance: none;
  border: none;
  background-color: #eee;
  padding: 10px 10px 10px 50px;
  transition: 0.65s ease;
  width: 900px;
  font-size: 25px;
}

/* .comment-input:focus {
  border-color: orange;
  background-color: #fff;
} */

textarea[readonly] {
  border: 1px solid orange;
}

.here .if .btn {
  appearance: none;
  background-color: #fff;
  font-size: 15px;
  text-decoration: none;
  color: orange;
  padding: 10px 20px;
  line-height: 1.5;
  transition: 0.65s ease;
  margin-left: 10px;
}

.here .if .btn,
.here .if .btn:focus,
.here .if .btn:active {
  border: 0;
}

.here .if .btn:hover {
  background-color: orange;
  color: #fff;
}

.date {
  font-family: "Montserrat", sans-serif;
  font-size: 20px;
}
</style>
