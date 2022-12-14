import Vue from "vue";
import Vuex from "vuex";
import axios from "@/util/http-common.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    IMG_URL: "http://localhost:9999/images/",
    VID_URL: "http://localhost:9999/videos/",
    isLogin: sessionStorage.getItem("access-token"),
    curUser: {},

    myUser: {},
    myUserFollowers: [],
    myPurchaseList: [],
    myFavoriteList: [],

    videos: [],
    video: {},
    videoFavoriteUsers: [],
    comments: [],
    comment: {},
    cartList: [],

    trainer: {},

    nowRoutineList: [],
    nowPlayingIdx: 0,

    detailIMG: [
      "fordetail00",
      "fordetail11",
      "fordetail22",
      "fordetail33",
      "fordetail44",
      "fordetail55",
      "fordetail66",
      "fordetail77",
      "fordetail88",
      "fordetail99",
    ],
  },
  getters: {
    myUser(state) {
      return state.myUser;
    },
    myCart(state) {
      return state.cartList;
    },
    nowRoutineList(state) {
      return state.nowRoutineList;
    },
    nowPlayingIdx(state) {
      return state.nowPlayingIdx;
    },
    myPurchaseList(state) {
      return state.myPurchaseList;
    },
    myFavoriteList(state) {
      return state.myFavoriteList;
    },
  },
  mutations: {
    SET_TRAINER(state, payload) {
      state.trainer = payload;
    },
    SET_CUR_USER(state, payload) {
      state.curUser = payload;
    },
    SET_MY_USER(state, payload) {
      console.log(payload);
      state.myUser = payload;
    },
    SET_MY_USER_FOLLOWERS(state, payload) {
      state.myUserFollowers = payload;
    },
    // [APP.vue] 페이지 첫 로딩 시 로컬스토리지에 저장되어 있는 카트리스트로 다시 세팅해주기
    SET_MY_CART(state, cartList) {
      state.cartList = cartList;
    },

    // 장바구니는 로컬스토리와 vuex에만 저장. DB에 저장하진 않음
    ADD_CART(state, video) {
      const curVid = video.v_id;

      for (let i = 0; i < state.cartList.length; i++) {
        if (curVid === state.cartList[i].v_id) {
          alert("장바구니에 이미 추가되었습니다.");
          return;
        }
      }
      state.cartList.push(video);
      localStorage.setItem("myCart", JSON.stringify(state.cartList));
    },
    //  1개만 지우기
    REMOVE_CART(state, cart) {
      const curVid = cart.v_id;
      for (let i = 0; i < state.cartList.length; i++) {
        if (curVid === state.cartList[i].v_id) {
          state.cartList.splice(i, 1);
          break;
        }
      }
      localStorage.setItem("myCart", JSON.stringify(state.cartList));
    },
    ADD_FOLLOWER(state) {
      state.myUserFollowers.push(state.curUser);
    },
    CHANGE_PROFILE(state, profile) {
      state.myUser.profile = profile;
    },
    DELETE_FOLLOWER(state) {
      state.myUserFollowers = state.myUserFollowers.filter(
        (f) => f.u_id !== state.curUser.u_id
      );
    },
    CLEAR_ALL(state) {
      state.curUser = {};
      state.myUser = {};
      state.myUserFollowers = [];
      state.videos = [];
      state.video = {};
      state.comments = [];

      state.myPurchaseList = [];
      state.myFavoriteList = [];

      state.nowRoutineList = [];
      state.nowPlayingIdx = 0;
    },
    SORT_VIDEOS(state, payload) {
      switch (payload) {
        // case "제목":
        //   console.log(payload);
        //   state.videos.sort((a, b) => a.title.localeCompare(b.title));
        //   break;
        case "조회수":
          state.videos.sort((b, a) => a.view_cnt - b.view_cnt);
          break;
        case "등록일":
          state.videos.sort((b, a) => a.reg_date.localeCompare(b.reg_date));
          break;
        case "좋아요수":
          state.videos.sort((b, a) => a.favorite_cnt - b.favorite_cnt);
          break;
        case "가격높":
          state.videos.sort((b, a) => a.price - b.price);
          break;
        case "가격낮":
          state.videos.sort((b, a) => b.price - a.price);
          break;
      }
    },
    FAVORITE(state) {
      state.videoFavoriteUsers.push(state.myUser);
    },
    UNFAVORITE(state) {
      state.videoFavoriteUsers = state.videoFavoriteUsers.filter(
        (user) => user.u_id !== state.myUser.u_id
      );
    },
    SET_COMMENTS(state, payload) {
      state.comments = payload;
    },
    DELETE_COMMENT(state, payload) {
      state.comments = state.comments.filter((c) => c.c_id !== payload);
    },
    SET_VIDEOS(state, payload) {
      state.videos = payload;
    },
    SET_VIDEO(state, payload) {
      state.video = payload;
    },
    SET_VIDEO_FAVORITE_USERS(state, payload) {
      state.videoFavoriteUsers = payload;
    },
    CHECK_LOGIN(state) {
      state.isLogin = sessionStorage.getItem("access-token");
    },
    SET_NOW_ROUTINE_LIST(state, list) {
      state.nowRoutineList = list;
    },
    ADD_PLAYING_IDX(state) {
      state.nowPlayingIdx = state.nowPlayingIdx + 1;
    },
    SET_PLAYING_IDX(state, number) {
      state.nowPlayingIdx = number;
    },
    SET_FAVORITE_LIST(state, list) {
      state.myFavoriteList = list;
    },
    SET_BUY_LIST(state, list) {
      state.myPurchaseList = list;
    },
  },
  actions: {
    getUserById(context, id) {
      axios({
        method: "GET",
        url: "userApi/detail",
        params: { id },
      }).then((res) => {
        console.log(res);
        context.commit("SET_MY_USER", res.data);
      });
    },
    getFavoriteList(context, u_id) {
      axios({
        url: "favoriteApi/",
        params: { u_id: u_id },
        method: "GET",
      }).then((res) => {
        if (res.data) context.commit("SET_FAVORITE_LIST", res.data);
      });
    },
    getBuyList(context, u_id) {
      axios({
        url: "userApi/buyList",
        params: {
          u_id: u_id,
        },
        method: "GET",
      })
        .then((res) => {
          if (res.data.length) context.commit("SET_BUY_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  modules: {},
});
