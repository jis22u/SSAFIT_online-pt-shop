<template>
  <div class="top">
    <form @submit.prevent="searchVideo" class="container">
      <div class="item">
        <input type="checkbox" id="전신" value="전신" v-model="category" />
        <label for="전신">전신</label>&nbsp;
        <input type="checkbox" id="가슴" value="가슴" v-model="category" />
        <label for="가슴">가슴</label>&nbsp;
        <input type="checkbox" id="상체" value="상체" v-model="category" />
        <label for="상체">상체</label>&nbsp;
        <input type="checkbox" id="어깨" value="어깨" v-model="category" />
        <label for="어깨">어깨</label>&nbsp;
        <input type="checkbox" id="등" value="등" v-model="category" />
        <label for="등">등</label>&nbsp;
        <input type="checkbox" id="하체" value="하체" v-model="category" />
        <label for="하체">하체</label>&nbsp;
        <input type="checkbox" id="복근" value="복근" v-model="category" />
        <label for="복근">복근</label>&nbsp;
        <input type="checkbox" id="이두" value="이두" v-model="category" />
        <label for="이두">이두</label>&nbsp;
        <input type="checkbox" id="삼두" value="삼두" v-model="category" />
        <label for="삼두">삼두</label>&nbsp;
        <input type="checkbox" id="팔뚝" value="팔뚝" v-model="category" />
        <label for="팔뚝">팔뚝</label>
      </div>
      <div class="item">
        <select v-model="range">
          <option>전체</option>
          <option>제목</option>
          <option>트레이너</option>
        </select>
      </div>
      <div class="item">
        <input type="text" v-model="keyword" />
        <button class="searchBtn">검색</button>
      </div>
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "VideoViewSearchInput",
  data() {
    return {
      range: "전체",
      category: [],
      keyword: "",
    };
  },
  methods: {
    searchVideo() {
      axios
        .get(
          // URL의 query string 구성하기
          // category=1&category=2&category=3&ranger=뭐&keyword=뭐
          // 이름이 같은게 여러개 들어오면 스프링에서는 자동으로 배열로 받음
          `videoApi/video?${this.category
            .map((n) => `category=${n}`)
            .join("&")}`,
          {
            params: {
              keyword: this.keyword,
              range: this.range,
            },
          }
        )
        .then((res) => this.$store.commit("SET_VIDEOS", res.data));
    },
  },
  created() {
    axios({
      url: `videoApi/video`,
      method: "GET",
      params: {
        range: "",
        keyword: "",
        category: [],
      },
    })
      .then((res) => {
        console.log(res.data);
        this.$store.commit("SET_VIDEOS", res.data);
      })
      .catch((err) => {
        err;
      });
  },
  computed: {
    ...mapState(["videos"]),
  },
};
</script>

<style scoped>
.top > form > div {
  width: 80%;
  margin: 0 auto;
  text-align: center;
  padding: 10px 0;
}
select {
  display: block;
  width: 50%;
  padding: 16px 20px;
  border: none;
  border-radius: 4px;
  background-color: #f1f1f1;
  margin: 0 auto;
}
/* input[type="text"]:focus {
  border: 3px solid #555;
} */
input[type="text"] {
  width: 80%;
  height: 50px;
  border: none;
  border-bottom: 2px solid black;
  background-image: url("@/assets/images/search_icon.png");
  background-position: 10px 10px;
  background-repeat: no-repeat;
  background-size: 2%;
  padding-left: 60px;
  font-size: 25px;
}
.searchBtn {
  display: none;
}
.container {
  display: flex;
  flex-direction: column;
}
input {
  cursor: pointer;
}
</style>
