<template>
  <div>
    <div class="category">
      초중급자분들을 위한 프로그램입니다. <br />다이어트, 건강유지 목적으로
      <br />{{ video.category }}운동을 하시려는 분들에게 적합해요.
      <!-- <div>
        <h4 v-if="category"></h4>
      </div> -->
    </div>
    <br />
    <i class="bi bi-activity" style="margin-left: 3em; font-size: 12em"></i>
    <br />
    <div class="fighting">
      <div class="fighting1">온라인 PT가 가능하냐구요?</div>
      <div class="fighting2">
        물론 가능하지만 전제 조건이 있어요. 회원님의 의지가 가장 중요하다는
        점이에요. 좋은 커리큘럼이 있어도 운동을 하지 않는다면 의미가 없지요.
        운동을 하기로 결심한 기간만큼은 내 몸에 집중해주세요 SSAFIT이 회원님을
        응원할게요!!
      </div>
    </div>
    <!-- <div class="qna">
      <div>
        <div>Q. 프로그램을 결제했는데 운동은 어떻게 시작하나요?</div>
        <div>
          mypage에서 결제한 온라인 PT들을 확인하실 수 있습니다. 나만의 루틴으로
          플레이리스트에 담아 계획을 세우고 운동을 시작할 수 있습니다.
        </div>
      </div>
      <div>
        <div>Q. 저에게 맞는 프로그램을 추천해 주실 수 있나요?</div>
        <div>
          관심있는 트레이너와 다른 회원님들을 팔로우해보세요! 회원님의 팔로잉
          활동에 따라 관심도를 파악하고 온라인 PT를 추천해드립니다. 또한
          운동목적을 설정해두시면 그에 맞는 온라인 PT들을 추천해드릴 수
          있습니다.
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
export default {
  name: "VideoViewDetailContentIntro",
  props: {
    video: Object,
  },
  data() {
    return {
      // category: "",
    };
  },
  created() {
    console.log(this.video.category);
    this.category = this.video.category;
    // console.log(this.category);
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap");
div {
  font-family: "Noto Sans KR", sans-serif;
  color: black;
  text-align: left;
}

.bi bi-activity {
}
.category {
  margin-top: 5%;
  font-size: 1.5em;
  margin-left: 15%;
  padding-left: 2%;
  border-left: solid 2.5px black;
}

.fighting1 {
  text-align: right;
  font-size: 1.5em;
  margin-right: 15%;
  padding-right: 2%;
  border-right: solid 2.5px black;
}

.fighting2 {
  margin-top: 1%;
  font-size: 1.3em;
  margin-left: 20%;
  max-width: 65%;
}
</style>
