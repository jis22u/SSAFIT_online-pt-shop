<template>
  <div>
    <div class="container">
      <div class="item" v-for="video in curVideos" :key="video.id">
        <video-view-search-result-row :video="video">
        </video-view-search-result-row>

        <!-- <span><i @click="addCart(video)" class="bi bi-cart"></i></span>
        <span><i class="bi bi-heart-fill" style="color: black"> </i></span> -->
      </div>
    </div>
    <!-- <select id="sort" v-model="sortCriteria">
      <option>----</option>
      <option>제목</option>
      <option>조회수</option>
      <option>등록일</option>
      <option>좋아요수</option>
    </select> -->

    <div class="sort">
      <!-- <button @click="sortCriteria = '제목'">제목</button> -->
      <button @click="sortCriteria = '조회수'">조회수</button>
      <button @click="sortCriteria = '등록일'">등록일</button>
      <button @click="sortCriteria = '좋아요수'">좋아요수</button>
      <button @click="sortCriteria = '가격높'">가격높은순</button>
      <button @click="sortCriteria = '가격낮'">가격낮은순</button><br />
      <input type="number" v-model="curPage" />
    </div>
    <!-- pagination -->
  </div>
</template>

<script>
import VideoViewSearchResultRow from "@/components/video/VideoViewSearchResultRow.vue";
import { mapState } from "vuex";

export default {
  name: "VideoViewSearchResult",
  computed: {
    // store에서 내 페이지로 videos 가져오기
    // 내 페이지에서 store로 videos 내보내기
    ...mapState(["videos"]),
  },
  components: {
    VideoViewSearchResultRow,
  },
  data() {
    return {
      sortCriteria: "",
      curPage: 1,
      curVideos: [],
      PAGE_SIZE: 9,
    };
  },
  methods: {
    //카트에 물건추가하기
    addCart(video) {
      console.log(video);
      this.$store.commit("ADD_CART", video);
    },
  },
  watch: {
    sortCriteria(newVal) {
      this.$store.commit("SORT_VIDEOS", newVal);
    },
    curPage(newVal) {
      this.curVideos = this.videos.slice(
        (newVal - 1) * this.PAGE_SIZE,
        newVal * this.PAGE_SIZE
      );
    },
    videos() {
      this.curPage = 1;
      this.curVideos = this.videos.slice(
        (this.curPage - 1) * this.PAGE_SIZE,
        this.curPage * this.PAGE_SIZE
      );
    },
  },
  created() {
    this.curVideos = this.videos.slice(
      (this.curPage - 1) * this.PAGE_SIZE,
      this.curPage * this.PAGE_SIZE
    );
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-flow: row wrap;
  justify-content: space-evenly;
}

.item {
  /* flex-grow: 1; */
  margin: 20px;
  /* border: 1px solid gray; */
}
button {
  font-size: 20px;
  cursor: pointer;
  background-color: white;
  color: black;
  border: 1px solid gray;
  border-radius: 5px;
  transition-duration: 0.4s;
  padding: 10px 30px;
  margin: 4px 2px;
}

button:hover {
  background-color: black;
  color: white;
  border: none;
}
.sort {
  padding: 100px;
  text-align: center;
}
</style>
