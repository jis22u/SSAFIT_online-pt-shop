<template>
  <div>
    <section id="section-1" ref="mainPage">
      <div class="content-slider">
        <input
          type="radio"
          id="banner1"
          class="sec-1-input"
          name="banner"
          checked
        />
        <input type="radio" id="banner2" class="sec-1-input" name="banner" />
        <input type="radio" id="banner3" class="sec-1-input" name="banner" />
        <input type="radio" id="banner4" class="sec-1-input" name="banner" />
        <div class="slider">
          <div id="top-banner-1" class="banner">
            <div class="banner-inner-wrapper">
              <h2>exercise anytime, anywhere</h2>
              <h1>Welcome<br />to SSAFIT</h1>
              <div class="line"></div>
              <div class="learn-more-button">
                <a href="#section-2">Learn More</a>
              </div>
            </div>
          </div>
          <div id="top-banner-2" class="banner">
            <div class="banner-inner-wrapper">
              <h2>What We Do</h2>
              <h1>Great<br />SSAFIT</h1>
              <div class="line"></div>
              <div class="learn-more-button">
                <a href="#section-4">Learn More</a>
              </div>
            </div>
          </div>
          <div id="top-banner-3" class="banner">
            <div class="banner-inner-wrapper">
              <h2>Here We Are</h2>
              <h1>We Are<br />SSAFIT</h1>
              <div class="line"></div>
              <div class="learn-more-button">
                <a href="#section-6">Learn More</a>
              </div>
            </div>
          </div>
          <div id="top-banner-4" class="banner">
            <div class="banner-inner-wrapper">
              <h2>Our Promotion</h2>
              <h1>Welcome<br />to SSAFIT</h1>
              <div class="line"></div>
              <div class="learn-more-button">
                <a href="#main-footer">Learn More</a>
              </div>
            </div>
          </div>
        </div>
        <nav>
          <div class="controls">
            <label for="banner1"
              ><span class="progressbar"
                ><span class="progressbar-fill"></span></span
              ><span>01</span> Intro</label
            >
            <label for="banner2"
              ><span class="progressbar"
                ><span class="progressbar-fill"></span></span
              ><span>02</span> Work</label
            >
            <label for="banner3"
              ><span class="progressbar"
                ><span class="progressbar-fill"></span></span
              ><span>03</span> About</label
            >
            <label for="banner4"
              ><span class="progressbar"
                ><span class="progressbar-fill"></span></span
              ><span>04</span> Promotion</label
            >
          </div>
        </nav>
      </div>
    </section>
    <br />
    <main-page-content></main-page-content>
    <main-page-promotion></main-page-promotion>
  </div>
</template>

<script>
import MainPageContent from "./HomeViewMainPageContent.vue";
import MainPagePromotion from "./HomeViewMainPagePromotion.vue";

export default {
  name: "MainPage",
  components: {
    MainPageContent,
    MainPagePromotion,
  },
};
</script>

<style scoped>
#section-1 {
  height: 50em;
  color: #fff;
  background-color: #0a0a0a;
}
#section-1 .content-slider {
  position: relative;
  width: 100%;
  height: 100%;
}
#section-1 .content-slider input {
  display: none;
}
#section-1 .content-slider .slider {
  position: relative;
  width: inherit;
  height: inherit;
  overflow: hidden;
}
#section-1 .content-slider .slider .banner {
  position: absolute;
  top: 0;
  left: 0;
  opacity: 0;
  z-index: 0;
  width: inherit;
  height: inherit;
  text-align: center;
  background-repeat: no-repeat;
  background-position: 50% 70%;
  transition: all 0.5s ease;
}

/* exercise anytime, anywhere */
#section-1 .content-slider .slider .banner .banner-inner-wrapper {
  height: 100%;

  padding-top: 200px;
  background-color: #0a0a0a;
  background-image: url("@/assets/images/mainpageBanner.jpg");
  background-repeat: no-repeat;
  background-position: 2% bottom;
  box-sizing: border-box;
}
#section-1 .content-slider .slider .banner .banner-inner-wrapper h2 {
  padding-bottom: 0.3em;
  font-family: "Kaushan Script", cursive;
  font-weight: 400;
  font-size: 2.5em;
  text-transform: none;
}
#section-1 .content-slider .slider .banner .banner-inner-wrapper h1 {
  font-size: 6em;
  line-height: 95%;
}
#section-1 .content-slider .slider .banner .banner-inner-wrapper .line {
  display: block;
  width: 4em;
  height: 0.1875em;
  margin: 2.5em auto;
  background: #fff;
}
#section-1
  .content-slider
  .slider
  .banner
  .banner-inner-wrapper
  .learn-more-button {
  padding-bottom: 5em;
  z-index: 15 !important;
}
#section-1
  .content-slider
  .slider
  .banner
  .banner-inner-wrapper
  .learn-more-button
  a {
  padding: 0.5em 2em;
  text-align: center;
  font-family: Montserrat, sans-serif;
  font-size: 0.875em;
  color: #fff;
  text-transform: uppercase;
  border: 0.1875em solid #fff;
}
#section-1
  .content-slider
  .slider
  .banner
  .banner-inner-wrapper
  .learn-more-button
  a:hover {
  color: #fadf8b;
  border-color: #fadf8b;
  transition: 0.3s;
}
#section-1 .content-slider .slider #top-banner-1 {
  /* background: url("https://checkboxes-demo.webdevs.co.ua/images/mogo/banner-1.png") */
  /* no-repeat center center; */
  background-size: cover;
}
#section-1 .content-slider .slider #top-banner-2 {
  /* background: url("https://checkboxes-demo.webdevs.co.ua/images/mogo/banner-2.png")
    no-repeat center center; */
  background-size: cover;
}
#section-1 .content-slider .slider #top-banner-3 {
  /* background: url("https://checkboxes-demo.webdevs.co.ua/images/mogo/banner-3.png")
    no-repeat center center; */
  background-size: cover;
}
#section-1 .content-slider .slider #top-banner-4 {
  /* background: url("https://checkboxes-demo.webdevs.co.ua/images/mogo/banner-4.png")
    no-repeat center center; */
  background-size: cover;
}
#section-1 .content-slider nav {
  position: absolute;
  bottom: 3em;
  width: 100%;
  z-index: 10;
  text-align: center;
}
#section-1 .content-slider nav .controls {
  display: block;
  width: 70%;
  margin: 0 auto;
  font-family: Montserrat, sans-serif;
  color: #fff;
}
#section-1 .content-slider nav .controls label {
  position: relative;
  display: inline-block;
  width: 20%;
  height: 3.1em;
  overflow: hidden;
  margin: 0 1em;
  padding-top: 1em;
  text-align: left;
  text-transform: uppercase;
  font-family: Montserrat, sans-serif;
  font-size: 1em;
  color: white;
  font-weight: 400;
  cursor: pointer;
  transition: all 0.3s;
}
#section-1 .content-slider nav .controls label .progressbar {
  position: absolute;
  top: 0;
  left: 0;
  height: 3px;
  width: 100%;
  background: #f6eac5;
  z-index: 100;
}
#section-1 .content-slider nav .controls label .progressbar .progressbar-fill {
  position: inherit;
  width: inherit;
  height: inherit;
  margin-left: -100%;
  background: #fadf8b;
}
#section-1 .content-slider nav .controls label span {
  font-size: 1.4em;
  font-weight: 700;
}
#section-1 .content-slider nav .controls label:hover {
  color: #fadf8b;
}
#section-1 .content-slider #banner1:checked ~ .slider #top-banner-1,
#section-1 .content-slider #banner2:checked ~ .slider #top-banner-2,
#section-1 .content-slider #banner3:checked ~ .slider #top-banner-3,
#section-1 .content-slider #banner4:checked ~ .slider #top-banner-4 {
  opacity: 1;
  z-index: 1;
}
#section-1 .content-slider #banner1:checked ~ nav label:nth-of-type(1),
#section-1 .content-slider #banner2:checked ~ nav label:nth-of-type(2),
#section-1 .content-slider #banner3:checked ~ nav label:nth-of-type(3),
#section-1 .content-slider #banner4:checked ~ nav label:nth-of-type(4) {
  cursor: default;
  color: #fadf8b;
  transition: all 0.5s;
}
#section-1
  .content-slider
  #banner1:checked
  ~ nav
  label:nth-of-type(1)
  .progressbar,
#section-1
  .content-slider
  #banner2:checked
  ~ nav
  label:nth-of-type(2)
  .progressbar,
#section-1
  .content-slider
  #banner3:checked
  ~ nav
  label:nth-of-type(3)
  .progressbar,
#section-1
  .content-slider
  #banner4:checked
  ~ nav
  label:nth-of-type(4)
  .progressbar {
  background: #fff;
}
#section-1
  .content-slider
  #banner1:checked
  ~ nav
  label:nth-of-type(1)
  .progressbar-fill,
#section-1
  .content-slider
  #banner2:checked
  ~ nav
  label:nth-of-type(2)
  .progressbar-fill,
#section-1
  .content-slider
  #banner3:checked
  ~ nav
  label:nth-of-type(3)
  .progressbar-fill,
#section-1
  .content-slider
  #banner4:checked
  ~ nav
  label:nth-of-type(4)
  .progressbar-fill {
  animation: progressBarFill 5s linear;
}
/* Animations */
@keyframes progressBarFill {
  from {
    margin-left: -100%;
  }
  to {
    margin-left: 0;
  }
}

/* Media Queries */
</style>
