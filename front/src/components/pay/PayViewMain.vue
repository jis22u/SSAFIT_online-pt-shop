<template>
  <div class="font">
    <div class="title">
      <i class="bi bi-credit-card-2-back"></i> &nbsp;
      <div>결제 페이지</div>
    </div>

    <div class="cartlist">장바구니 내역</div>
    <div class="list">
      <div class="detail">
        <div>온라인 PT</div>
        <div>가격</div>
      </div>
      <div v-for="(item, idx) in buyList" :key="idx" class="detail">
        <div>{{ item.title }}</div>
        <div>{{ item.price }}</div>
      </div>
    </div>
    <button @click="requestPay" class="pay-btn">결제하기</button>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";

var IMP = window.IMP;
IMP.init(process.env.VUE_APP_IMP);

// const imp =
// const API_KEY = process.env.VUE_APP_API_KEY;
// const API_SECRET = process.env.VUE_APP_API_SECRET;

export default {
  name: "PayViewMain",
  computed: {
    myUser() {
      return this.$store.getters.myUser;
    },
  },
  // 라우터 등록 시 'props: true' 처리해두면 라우터의 params를 props로 받을 수 있다.
  props: {
    buyList: Array,
    sum: Number,
  },
  methods: {
    guid() {
      const s4 = () => {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
      };

      return (
        s4() +
        s4() +
        "-" +
        s4() +
        "-" +
        s4() +
        "-" +
        s4() +
        "-" +
        s4() +
        s4() +
        s4()
      );
    },
    requestPay() {
      // 결제API 사용하려고 만듦
      let uuid = this.myUser.u_id + this.guid();

      IMP.request_pay(
        {
          // param
          pg: "html5_inicis.INIpayTest",
          pay_method: "card",
          merchant_uid: uuid,
          name: `${this.buyList[0].title} 외 ${this.buyList.length - 1}`,
          amount: 100,
          // this.sum,
          buyer_email: this.myUser.email,
          buyer_name: this.myUser.name,
          buyer_tel: this.myUser.phone_no,
        },
        (rsp) => {
          // callback
          if (rsp.success) {
            // 결제 성공 시 로직,
            console.log("결제 성공하였습니다.", rsp);

            let v_ids = [];
            for (let i = 0; i < this.buyList.length; i++) {
              v_ids.push(this.buyList[i].v_id);
            }

            //결제처리
            axios({
              url: "buyApi/",
              method: "POST",
              data: v_ids,
              params: {
                u_id: this.myUser.u_id,
                price_sum: this.sum,
              },
            });

            localStorage.removeItem("myCart");
            this.$store.commit("SET_MY_CART", []);
            this.moveMyPage();
          } else {
            // 결제 실패 시 로직,
            alert("결제에 실패하였습니다. 결제 정보를 다시 입력해주세요.");
            console.log(rsp.error_msg);
          }
        }
      );
    },
    moveMyPage() {
      this.$router.push({
        name: "UserViewMypageMain",
        params: { myUser: this.myUser },
      });
    },
    // removeCart(item) {
    //   console.log(item);
    //   this.$store.commit("REMOVE_CART", item);
    // },
  },
  mounted() {
    if (!this.buyList || this.buyList.length == 0) {
      alert("장바구니가 비었습니다.");
      this.$router.push({ name: "MainPageView" });
    }
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Hahmlet&display=swap");
.title {
  display: flex;
  padding-left: 5%;
  font-size: 2em;
}
div {
  font-family: "Hahmlet", serif;
}
.list {
  box-shadow: 7px 4px 10px 3px gray;
  /* border: 1px solid black; */
  width: 60%;
  margin-left: 15%;
  padding: 2%;
  font-size: 1.2em;
}
.detail {
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid gray;
  padding-bottom: 3%;
  padding-top: 1.5%;
}
.cartlist {
  margin-left: 42%;
  font-family: "Hahmlet", serif;
  margin-top: 5%;
  margin-bottom: 1%;
  font-size: 1.5em;
}
.pay-btn {
  margin-top: 2%;
  margin-left: 74%;
  cursor: pointer;
  padding: 0.7%;
  font-size: 1em;
  transition-duration: 0.4s;
  background-color: white;
  color: black;
  border: 2px solid black;
  font-family: "Hahmlet", serif;
  border-radius: 20%;
}

.pay-btn:hover {
  background-color: black;
  color: white;
}
</style>
