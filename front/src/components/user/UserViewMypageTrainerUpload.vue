<template>
  <div class="video-upload">
    <div class="video-preview">
      <video ref="video" id="video" @loadedmetadata="onLoadVideo" controls>
        <!-- <source ref="videoSource" :src="videoSrc" /> -->
      </video>
      <canvas
        id="thumb_canvas"
        width="1600"
        height="1280"
        ref="canvas"
      ></canvas>
    </div>
    <div class="form-wrapper">
      <h2>동영상 등록</h2>
      <div class="lg-form">
        <input type="text" v-model="title" placeholder="제목" />
        <input type="number" v-model="price" placeholder="가격" max="9876543" />
        <select v-model="category">
          <option v-for="(item, idx) in categoryList" :key="idx">
            {{ item }}
          </option>
        </select>
        <input
          ref="videoFile"
          @change="changeInput"
          id="file-input"
          type="file"
          accept="video/*"
        />
        <button @click="makeThumbnail" :disabled="!thumnailBtn">
          썸네일 만들기
        </button>
        <button @click="registVideo" :disabled="!registBtn">등록</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";
export default {
  name: "UserViewMypageTrainerUpload",
  data() {
    return {
      videoSrc: "",
      title: "",
      price: "",
      thumnailBtn: false,
      registBtn: false,
      category: "전신",
      categoryList: [
        "전신",
        "가슴",
        "상체",
        "어깨",
        "등",
        "하체",
        "복근",
        "이두",
        "삼두",
        "팔뚝",
      ],
    };
  },
  watch: {
    price() {
      if (this.price > 9876543) this.price = 9876543;
    },
  },
  props: { t_name: String },
  methods: {
    onLoadVideo() {
      this.thumnailBtn = true;

      const video = this.$refs.video;
      console.log(video.duration);
    },
    makeThumbnail() {
      const video = this.$refs.video;
      const canvas = this.$refs.canvas;

      console.log(video.width);
      console.log(video.height);

      canvas
        .getContext("2d")
        .drawImage(video, 0, 0, canvas.width, canvas.height);

      this.registBtn = true;
    },
    changeInput() {
      this.thumnailBtn = false;
      this.registBtn = false;

      const files = this.$refs.videoFile.files;
      const video = this.$refs.video;
      // const videoSource = this.$refs.videoSource;

      if (!files.length) return;

      video.src = URL.createObjectURL(this.$refs.videoFile.files[0]);

      // const reader = new FileReader();
      // console.log(reader);
      // const me = this;

      // reader.onload = (e) => {
      //   console.log(e);
      //   me.videoSrc = e.target.result;

      //   video.load();
      //   console.log(video.duration);

      //   video.play();
      // };

      // reader.onprogress = (e) => {
      //   console.log("progress: ", Math.round((e.loaded * 100) / e.total));
      // };

      // reader.readAsDataURL(files[0]);
    },
    registVideo() {
      if (!this.title || !this.price) {
        alert("입력되지 않은 항목이 있습니다");
        return;
      }

      const videoData = new FormData();
      const canvas = this.$refs.canvas;

      console.log(this.t_name);
      videoData.append("title", this.title);
      videoData.append("t_name", this.t_name);
      videoData.append("category", this.category);
      videoData.append("price", this.price);

      // console.log(this.$refs.videoFile);

      videoData.append("file", this.$refs.videoFile.files[0]);

      let blobBin = atob(canvas.toDataURL("image/png").split(",")[1]);
      let arr = [];
      for (let i = 0; i < blobBin.length; i++) {
        arr.push(blobBin.charCodeAt(i));
      }
      let file = new Blob([new Uint8Array(arr)], { type: "image/png" });

      let date = new Date();
      file.lastModifiedDate = date;
      file.lastModified = date.getTime();
      file.name = this.title;
      file = new File(
        [file, `${this.t_name}_${this.title}.png`],
        `${this.t_name}_${this.title}.png`
      );

      videoData.append("imgFile", file);

      console.log(videoData);
      console.log(file);

      axios({
        url: "videoApi/",
        data: videoData,
        method: "POST",
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
        .then((res) => {
          console.log(res.data);
          alert("성공적으로 등록되었습니다");
          this.$router.push({ name: "UserViewMypageTrainerMain" });
        })
        .catch((err) => console.log(err));
    },
  },
  created() {
    console.log(this.$refs.videoFile);
    if (!this.t_name) {
      alert("세션이 만료되었습니다 ");
      this.$router.push({ name: "UserViewMypageTrainerMain" });
    }
  },
};
</script>

<style scoped>
video {
  border: 1px solid black;
  display: block;
}

.video-upload {
  display: flex;
  flex-direction: row;
  padding: 7em 3em;
}

.video-preview {
  width: 60%;
  padding: 5em;
}

.video-preview video {
  width: 100%;
  height: auto;
}
.video-preview canvas {
  width: 100%;
  height: auto;
}

/** 폼 */

.ok {
  background-color: green;
}
.form-wrapper {
  width: 40%;
}

.form-wrapper > h2 {
  margin-bottom: 1em;
}

.lg-form {
  width: 90%;
  margin: auto;
  padding: 2em;
  overflow: -moz-hidden-unscrollable;
}

h2 {
  margin-top: 2em;
  margin-bottom: 3em;
}
.lg-form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}

.lg-form select {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}

.lg-form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4caf50;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #ffffff;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.lg-form button:hover,
.lg-form button:active,
.lg-form button:focus {
  background: #43a047;
}

.lg-form button[disabled] {
  cursor: default;
  background: #666;
}

.last-btn {
  margin-bottom: 10em;
}

/** 라디오 */

.radio-box {
  font-family: "Roboto", sans-serif;
  outline: 0;
  height: 38px;
  margin: 0 0 15px;
  background: #f2f2f2;

  padding-top: 1em;
  text-align: center;
}

.radio-box label {
  margin-right: 20px;
}

[type="radio"]:checked,
[type="radio"]:not(:checked) {
  position: absolute;
  left: -9999px;
}
[type="radio"]:checked + label,
[type="radio"]:not(:checked) + label {
  position: relative;
  padding-left: 28px;
  cursor: pointer;
  line-height: 20px;
  display: inline-block;
  color: #666;
}
[type="radio"]:checked + label:before,
[type="radio"]:not(:checked) + label:before {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 18px;
  height: 18px;
  border: 1px solid #4caf50;
  border-radius: 100%;
  background: #fff;
}
[type="radio"]:checked + label:after,
[type="radio"]:not(:checked) + label:after {
  content: "";
  width: 12px;
  height: 12px;
  background: #4caf50;
  position: absolute;
  top: 4px;
  left: 4px;
  border-radius: 100%;
  -webkit-transition: all 0.2s ease;
  transition: all 0.2s ease;
}
[type="radio"]:not(:checked) + label:after {
  opacity: 0;
  -webkit-transform: scale(0);
  transform: scale(0);
}
[type="radio"]:checked + label:after {
  opacity: 1;
  -webkit-transform: scale(1);
  transform: scale(1);
}
</style>
