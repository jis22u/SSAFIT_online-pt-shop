<template>
  <div :class="{ wrapper: true, toggled: isToggled }">
    <div class="sidebar-wrapper">
      <ul class="sidebar-nav">
        <li class="sidebar-brand">나의 장바구니</li>

        <div class="list">
          <li v-for="(cart, idx) in cartList" :key="idx">
            <a href="#" @click="removeCart(cart)"
              >{{ cart.title }} | {{ cart.price }}원
            </a>
          </li>
        </div>
        <hr />
        <div class="pay-footer">
          <li class="totalPrice">총 가격 : {{ sum }}</li>
          <li
            :class="{ btn: true, disabled: cartList.length }"
            @click="goPay(cartList)">
            결제하러가기
          </li>
        </div>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "TheSidevar",
  data() {
    return {
      sum: 0,
    };
  },
  methods: {
    goPay(list) {
      if (!sessionStorage.getItem("access-token")) {
        alert("로그인이 필요한 서비스입니다!");
        return;
      }
      this.$router.push({
        name: "PayViewMain",
        // 라우터 등록 시 'props: true' 처리해두면 라우터의 params를 props로 받을 수 있다.
        params: { buyList: list, sum: this.sum },
      });
    },
    calcSum(array) {
      let sum = 0;
      for (let i = 0; i < array.length; i++) sum += array[i].price;

      this.sum = sum;
    },
    removeCart(cart) {
      console.log(cart);
      this.$store.commit("REMOVE_CART", cart);
    },
  },
  computed: {
    cartList() {
      // mapState로 바로 얻어와도 되나??
      return this.$store.getters.myCart;
    },
  },
  watch: {
    // newList는 뭐지
    cartList(newList) {
      this.calcSum(newList);
    },
  },
  props: {
    isToggled: Boolean,
  },
  created() {
    this.calcSum(this.cartList);
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Hahmlet&display=swap");

li {
  color: white;
  font-family: "Hahmlet", serif;
}
left-hamberger {
  margin: 12px 10px;
}
.btn {
  cursor: pointer;
}
.sortable {
  color: #428bca;
  cursor: pointer;
}
.disabled-btn {
  opacity: 0.4;
}
.sidebar-brand {
  color: white;
  font-weight: bold;
  background-color: black;
}
.search-fld-cont {
  max-width: 650px;
  margin: 0 auto;
}
.cust-table-cont {
  width: 100%;
  overflow-x: auto;
}
.cust-table-cont .table-responsive {
  width: 1190px;
}
.cust-table {
  table-layout: fixed;
}
.cust-table thead,
.cust-table tbody {
  display: block;
}
.cust-table tbody {
  max-height: 620px;
  overflow-y: auto;
}

.wrapper {
  padding-right: 0;
  -webkit-transition: all 0.5s ease;
  -moz-transition: all 0.5s ease;
  -o-transition: all 0.5s ease;
  transition: all 0.5s ease;
}

.wrapper.toggled {
  padding-right: 250px;
}

.sidebar-wrapper {
  /* background-image: url("@/assets/images/mycart22.jpg"); */
  color: white;
}
.sidebar-wrapper {
  /* background-image: url("@/assets/images/mycart33.jpg"); */
  background-repeat: no-repeat;
  background-color: black;
  /* opacity: 0.7; */
  z-index: 1000;
  position: fixed;
  right: 250px;
  width: 0;
  height: 100%;
  margin-right: -250px;
  overflow-y: auto;
  /* background: white; */
  -webkit-transition: all 0.5s ease;
  -moz-transition: all 0.5s ease;
  -o-transition: all 0.5s ease;
  transition: all 0.5s ease;
}

.wrapper.toggled .sidebar-wrapper {
  width: 350px;
}

.page-content-wrapper {
  width: 100%;
  position: absolute;
  padding: 15px;
}

.wrapper.toggled .page-content-wrapper {
  position: absolute;
  margin-right: -250px;
}

/* Sidebar Styles */

.sidebar-nav {
  background-color: black;
  position: fixed;
  top: 0;
  width: 250px;
  margin: 0;
  padding: 0;
  list-style: none;
}
.sidebar-icon {
  display: inline;
  margin-right: 10px;
}
.sidebar-nav li {
  text-indent: 3px;
  padding-left: 10px;
  line-height: 40px;
}

.sidebar-nav li a {
  display: block;
  text-decoration: none;
  color: white;
}

.sidebar-nav li a:hover,
.sidebar-nav li .active {
  text-decoration: none;
  /* color: black; */
  font-size: 1em;
  /* background: white; */
}

.sidebar-nav li a:active,
.sidebar-nav li a:focus {
  text-decoration: none;
}

.sidebar-nav > .sidebar-brand {
  height: 65px;
  font-size: 18px;
  line-height: 60px;
}

.sidebar-nav > .sidebar-brand a {
  color: #8b8b8b;
}

.sidebar-nav > .sidebar-brand a:hover {
  color: #fff;
  background: none;
}

@media (min-width: 768px) {
  .wrapper {
    padding-left: 250px;
  }

  .wrapper.toggled {
    padding-left: 0;
  }

  .sidebar-wrapper {
    width: 250px;
  }

  .wrapper.toggled .sidebar-wrapper {
    width: 0;
  }

  .page-content-wrapper {
    padding: 20px;
    position: relative;
  }

  .wrapper.toggled .page-content-wrapper {
    position: relative;
    margin-right: 0;
  }
}

.profile-pic {
  position: absolute;
  margin: 15px 0;
  right: 15px;
  cursor: pointer;
}
.content {
  margin-bottom: 25px;
  padding: 0 16px;
}
.page-title {
  font-size: 18px;
  margin: 0 auto;
  margin-top: 20px;
  margin-bottom: 25px;
  text-align: center;
}

@media (max-width: 360px) {
  .wrapper.toggled .profile-pic {
    display: none;
  }
  .wrapper.toggled .page-title {
    visibility: hidden;
  }
  .header {
    height: 72px;
  }
}
.del-icon {
  margin-right: 10px;
}
.list li {
  border-top: 1px solid white;
  font-size: 15px;
}
.sidebar-nav {
  text-align: left;
}
.totalprice {
  text-align: center;
}
.pay-footer {
}
</style>
