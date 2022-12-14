<template>
  <div class="modal">
    <button class="close" @click="closeModal">x</button>
    <LoginForm
      v-if="selected === 'LOGIN'"
      @success="closeModal"
      @want-regist="$emit('want-regist')"
    ></LoginForm>
    <JoinForm v-if="selected === 'JOIN'" @success="closeModal"></JoinForm>
  </div>
</template>

<script>
import LoginForm from "./LoginForm.vue";
import JoinForm from "./JoinForm.vue";
export default {
  name: "TheModal",
  components: {
    LoginForm,
    JoinForm,
  },
  data() {
    return {
      // selected: "LOGIN",
    };
  },
  methods: {
    closeModal() {
      this.$emit("close-modal");
    },
  },
  props: {
    selected: String,
  },
};
</script>

<style scoped>
.close {
  position: absolute;
  top: 10px;
  right: 10px;
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 50%;
  max-width: 600px;
  min-height: 50%;
  max-height: 600px;

  overflow: auto;

  z-index: 9999;
  margin: auto;
  padding: 20px 30px;
  background-color: #fff;
  border: 1px solid black;
}
</style>
