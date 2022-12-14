import axios from "axios";
// import router from "@/router/index.js";

const instance = axios.create({
  baseURL: "http://localhost:9999/api/",
  headers: {
    "Content-Type": "application/json",
    "access-token": sessionStorage.getItem("access-token"),
  },
});

// axios 요청 할때마다 sessionStorage에 access-token 있는지 확인
// instance.interceptors.request.use((config) => {
//   if (!sessionStorage.getItem("access-token")) {
//     alert("로그아웃 되었습니다 - 로그인 페이지로 이동합니다.");
//     router.push("/login");
//   }
//   return config;
// });

export default instance;
