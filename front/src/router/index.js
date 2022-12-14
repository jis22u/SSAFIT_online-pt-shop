import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    path: "/home",
    // name: "HomeView",
    component: HomeView,
    children: [
      {
        path: "/home",
        name: "MainPageView",
        component: () => import("@/components/home/HomeViewMainPage.vue"),
      },
    ],
  },
  {
    path: "/user",
    // name: "UserView",
    component: () => import("@/views/UserView.vue"),
    children: [
      {
        path: "/user",
        // name: "UserViewMypage",
        component: () => import("@/components/user/UserViewMypage.vue"),
        children: [
          {
            path: "/user",
            name: "UserViewMypageMain",
            component: () => import("@/components/user/UserViewMypageMain.vue"),
          },
          {
            path: "/user/update",
            name: "UserViewMypageUpdate",
            component: () =>
              import("@/components/user/UserViewMypageUpdate.vue"),
          },
          {
            path: "/user/registTrainer",
            name: "UserViewMypageTrainerRegist",
            component: () =>
              import("@/components/user/UserViewMypageTrainerRegist.vue"),
          },
          {
            path: "/user/trainerMain",
            name: "UserViewMypageTrainerMain",
            component: () =>
              import("@/components/user/UserViewMypageTrainerMain.vue"),
          },
          {
            path: "/user/upload",
            name: "UserViewMypageTrainerUpload",
            component: () =>
              import("@/components/user/UserViewMypageTrainerUpload.vue"),

            // params로 넘어오는 데이터를 상속받아서 계속 사용하겠다
            // this.$route.으로 데이터에 접근하지 않아도 됨
            props: true,
          },
          {
            path: "/user/routine/:r_id",
            name: "UserViewMypageRoutineDetail",
            component: () =>
              import("@/components/user/UserViewMypageRoutineDetail.vue"),
            props: true,
          },
        ],
      },
    ],
  },
  {
    path: "/video",
    // name: "VideoView",
    component: () => import("@/views/VideoView.vue"),
    children: [
      {
        path: "",
        name: "VideoViewSearch",
        component: () => import("@/components/video/VideoViewSearch.vue"),
      },
      {
        path: "",
        name: "VideoViewDetail",
        component: () => import("@/components/video/VideoViewDetail.vue"),
      },
    ],
  },
  {
    path: "/pay",
    // name: "PayView",

    component: () => import("@/views/PayView.vue"),
    children: [
      {
        path: "",
        name: "PayViewMain",
        component: () => import("@/components/pay/PayViewMain.vue"),
        props: true,
      },
    ],
  },

  {
    path: "/trainer",
    // name: "TrainerView",
    component: () => import("@/views/TrainerView.vue"),
    children: [
      {
        path: "",
        name: "TrainerViewMain",
        component: () => import("@/components/trainer/TrainerViewMain.vue"),
      },
      {
        path: "/detail/:t_name",
        name: "TrainerViewDetail",
        component: () => import("@/components/trainer/TrainerViewDetail.vue"),
        props: true,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
