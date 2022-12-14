<template>
  <div class="options">
    <div
      class="option"
      :class="{ active: selected == 0 }"
      @click="morp(0, -1)"
      :style="{ 'background-color': 'black' }">
      <div class="shadow"></div>
      <div class="label">
        <div class="icon">
          <i class="fas fa-walking"></i>
        </div>
        <div class="info">
          <div class="main">리스트 만들기 +</div>
        </div>
      </div>
    </div>
    <div
      v-for="(routine, idx) in routines"
      :key="idx"
      class="option"
      :class="{ active: selected == idx + 1 }"
      @click="morp(idx + 1, routine.r_id)"
      :style="{
        'background-image': 'url(' + IMG_URL + routine.v_id + '.png)',
      }">
      <div class="shadow"></div>
      <div class="label">
        <div class="icon">
          <i class="fas fa-walking"></i>
        </div>
        <div class="info">
          <div class="main">{{ routine.r_name }}</div>
          <div class="sub">
            영상 순서를 조절하여 나만의 운동 루틴을 만들어보세요
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserViewMypageMainRoutineListMain",
  data() {
    return {
      selected: 0,
      IMG_URL: this.$store.state.IMG_URL,
    };
  },
  methods: {
    morp(idx, r_id) {
      if (this.selected == idx) {
        if (r_id != -1) this.goDetail(r_id);
        else {
          this.$emit("showMaker");
        }
      }

      this.selected = idx;
    },
    goDetail(r_id) {
      console.log(r_id);
      this.$router.push({
        name: "UserViewMypageRoutineDetail",
        params: {
          r_id: r_id,
        },
      });
    },
  },
  props: {
    routines: Array,
  },
};
</script>

<style scoped>
body {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  height: 100vh;
  font-family: "Roboto", sans-serif;
  transition: 0.25s;
}
body .credit {
  position: absolute;
  bottom: 20px;
  left: 20px;
  color: inherit;
}
body .options {
  display: flex;
  flex-direction: row;
  align-items: stretch;
  overflow: hidden;
  min-width: 600px;
  max-width: 900px;
  width: calc(100% - 100px);
  height: 400px;
  padding: 2em;
}
@media screen and (max-width: 718px) {
  body .options {
    min-width: 520px;
  }
  body .options .option:nth-child(5) {
    display: none;
  }
}
@media screen and (max-width: 638px) {
  body .options {
    min-width: 440px;
  }
  body .options .option:nth-child(4) {
    display: none;
  }
}
@media screen and (max-width: 558px) {
  body .options {
    min-width: 360px;
  }
  body .options .option:nth-child(3) {
    display: none;
  }
}
@media screen and (max-width: 478px) {
  body .options {
    min-width: 280px;
  }
  body .options .option:nth-child(2) {
    display: none;
  }
}
body .options .option {
  position: relative;
  overflow: hidden;
  min-width: 60px;
  margin: 10px;
  background: var(--optionBackground, var(--defaultBackground, #e6e9ed));
  background-size: cover;
  background-position: center;
  cursor: pointer;
  transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
}
body .options .option:nth-child(1) {
  --defaultBackground: #ed5565;
}
body .options .option:nth-child(2) {
  --defaultBackground: #fc6e51;
}
body .options .option:nth-child(3) {
  --defaultBackground: #ffce54;
}
body .options .option:nth-child(4) {
  --defaultBackground: #2ecc71;
}
body .options .option:nth-child(5) {
  --defaultBackground: #5d9cec;
}
body .options .option:nth-child(6) {
  --defaultBackground: #ac92ec;
}
body .options .option.active {
  flex-grow: 10000;
  transform: scale(1);
  max-width: 600px;
  margin: 0px;
  border-radius: 40px;
  background-size: auto 100%;
  background-position: center;
}
body .options .option.active .shadow {
  box-shadow: inset 0 -120px 120px -120px black,
    inset 0 -120px 120px -100px black;
}
body .options .option.active .label {
  bottom: 20px;
  left: 20px;
}
body .options .option.active .label .info > div {
  left: 0px;
  opacity: 1;
}
body .options .option:not(.active) {
  flex-grow: 1;
  border-radius: 30px;
}
body .options .option:not(.active) .shadow {
  bottom: -40px;
  box-shadow: inset 0 -120px 0px -120px black, inset 0 -120px 0px -100px black;
}
body .options .option:not(.active) .label {
  bottom: 10px;
  left: 10px;
}
body .options .option:not(.active) .label .info > div {
  left: 20px;
  opacity: 0;
}
body .options .option .shadow {
  position: absolute;
  bottom: 0px;
  left: 0px;
  right: 0px;
  height: 120px;
  transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
}
body .options .option .label {
  display: flex;
  position: absolute;
  right: 0px;
  height: 40px;
  transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
}
body .options .option .label .icon {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  min-width: 40px;
  max-width: 40px;
  height: 40px;
  border-radius: 100%;
  background-color: white;
  color: var(--defaultBackground);
}
body .options .option .label .info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 10px;
  color: white;
  white-space: pre;
}
body .options .option .label .info > div {
  position: relative;
  transition: 0.5s cubic-bezier(0.05, 0.61, 0.41, 0.95), opacity 0.5s ease-out;
}
body .options .option .label .info .main {
  font-weight: bold;
  font-size: 1.2rem;
}
body .options .option .label .info .sub {
  transition-delay: 0.1s;
  font-size: 0.7em;
}
</style>
