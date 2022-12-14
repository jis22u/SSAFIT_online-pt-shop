<template>
  <div class="card-list">
    <div class="card" @click="moveDetail">
      <img
        class="card-image"
        :src="`${IMG_URL}${video.v_id}.png`"
        width="100%" />
      <!-- style="display: inline-block" /> -->
      <!-- <div style="display: inline-block"> -->
      <div class="card-description">
        <p>{{ video.t_name }}</p>
        <p
          style="
            font-size: 1.3em;
            text-overflow: ellipsis;
            overflow: hidden;
            width: 380px;
            white-space: nowrap;
          ">
          {{ video.title }}
        </p>
        <div class="container">
          <div>
            <p style="text-align: left; color: gray">
              {{ video.category }}
            </p>
            <p style="text-align: left; color: gray">{{ video.reg_date }}</p>
          </div>
          <div>
            <p style="font-size: 1.4em">{{ video.price }}</p>
          </div>
        </div>
        <span><i class="bi bi-eye"></i> {{ video.view_cnt }}</span>
        |
        <FavoriteIcon :v_id="video.v_id" />
        |
        <CartIcon :video="video" />
      </div>
    </div>
  </div>
</template>

<script>
import CartIcon from "../common/CartIcon.vue";
import FavoriteIcon from "../common/FavoriteIcon.vue";

export default {
  name: "VideoViewSearchResultRow",
  props: {
    video: Object,
  },
  components: {
    CartIcon,
    FavoriteIcon,
  },
  data() {
    return {
      IMG_URL: this.$store.state.IMG_URL,
    };
  },
  methods: {
    moveDetail() {
      this.$router.push({
        name: "VideoViewDetail",
        params: { id: this.video.v_id },
      });
    },
  },
};

// 코드펜
// window.addEventListener("load", function () {
//   setTimeout(lazyLoad, 1000);
// });

// function lazyLoad() {
//   var card_images = document.querySelectorAll(".card-image");

//   // loop over each card image
//   card_images.forEach(function (card_image) {
//     var image_url = card_image.getAttribute("data-image-full");
//     var content_image = card_image.querySelector("img");

//     // change the src of the content image to load the new high res photo
//     content_image.src = image_url;

//     // listen for load event when the new photo is finished loading
//     content_image.addEventListener("load", function () {
//       // swap out the visible background image with the new fully downloaded photo
//       card_image.style.backgroundImage = "url(" + image_url + ")";
//       // add a class to remove the blur filter to smoothly transition the image change
//       card_image.className = card_image.className + " is-loaded";
//     });
//   });
// }
</script>

<style scoped>
.container {
  display: flex;
  justify-content: space-between;
}
p {
  color: black;
}
div {
  cursor: pointer;
}

.card {
  width: 450px;
  height: 480px;
}

/* Lazy Load Styles */
.card-image {
  display: block;
  min-height: 20rem; /* layout hack */
  background: #fff center center no-repeat;
  background-size: cover;
  /* filter: blur(3px); blur the lowres image */
}

.card-image > img {
  display: block;
  width: 100%;
  opacity: 0; /* visually hide the img element */
}

.card-image.is-loaded {
  filter: none; /* remove the blur on fullres image */
  transition: filter 1s;
}

/* Layout Styles */
html,
body {
  width: 100%;
  height: 100%;
  margin: 0;
  font-size: 16px;
  font-family: sans-serif;
}

.card-list {
  display: block;
  margin: 1rem auto;
  padding: 0;
  font-size: 0;
  text-align: center;
  list-style: none;
}

.card {
  display: inline-block;
  width: 450px;
  max-width: 25rem;
  margin: 1rem;
  font-size: 1rem;
  text-decoration: none;
  overflow: hidden;
  box-shadow: 0 0 2rem -1rem rgba(0, 0, 0, 0.5);
  transition: transform 0.1s ease-in-out, box-shadow 0.1s;
}

.card:hover {
  transform: translateY(-0.5rem) scale(1.0125);
  box-shadow: 0 0.5em 3rem -1rem rgba(0, 0, 0, 0.5);
}

.card-description {
  display: block;
  padding: 1em 1em;
  color: black;
  text-decoration: none;
}

.card-description > h2 {
  margin: 0 0 0.5em;
}

.card-description > p {
  margin: 0;
}
</style>
