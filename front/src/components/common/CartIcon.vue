<template>
  <span>
    <i v-if="isPurchased()" class="bi bi-cart-check"></i>
    <i v-else @click.stop="addCart()" class="bi bi-cart-plus"></i>
  </span>
</template>

<script>
export default {
  name: "CartIcon",
  props: ["video"],
  computed: {
    myPurchaseList() {
      return this.$store.getters.myPurchaseList;
    },
  },
  methods: {
    isPurchased() {
      const arr = this.myPurchaseList;

      for (let index = 0; index < arr.length; index++) {
        const element = arr[index];
        if (element.v_id === this.video.v_id) {
          return true;
        }
      }

      return false;
    },
    //카트에 물건추가하기
    addCart() {
      if (!sessionStorage.getItem("access-token")) {
        alert("로그인이 필요한 서비스입니다!");
        return;
      }
      this.$store.commit("ADD_CART", this.video);
    },
  },
};
</script>

<style></style>
