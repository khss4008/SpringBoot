<template>
  <div>
    <div v-show="!isLogin">
      <img
        id="login"
        @click="modalShow = !modalShow"
        src="@/assets/button-user.png"
      />
      로그인
    </div>

    <div v-show="isLogin">
      <img id="logout" @click="logout2()" src="@/assets/logout.png" />
      로그 아웃
    </div>

    <b-modal
      id="modal-lg"
      size="lg"
      title="Login"
      v-model="modalShow"
      @ok="handleOk"
      >아이디
      <b-form-input
        id="name-input"
        v-model="user.userId"
        required
      ></b-form-input>
      <br />
      비밀번호
      <b-form-input
        id="name-input"
        type="password"
        v-model="user.userPwd"
        required
      ></b-form-input>
      <br />
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "LoginButton",
  data() {
    return {
      user: {
        userId: null,
        userPwd: null,
      },
      modalShow: false,
    };
  },

  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },

  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "logout"]),
    async handleOk() {
      // alert("ok를 눌렀슴" + this.user.userId + " " + this.user.userPwd);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // alert(token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // this.$router.push({ name: "map" });
      } else {
        alert("로그인에 실패했습니다.");
        sessionStorage.clear();
      }
    },
    logout2() {
      this.$store.dispatch("memberStore/logout2");
      alert("로그아웃 되었습니다.");
    },
  },
};
</script>
<style scoped>
#login {
  width: 34px;
  height: 34px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
}
#logout {
  width: 34px;
  height: 34px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
}

#user:hover {
  cursor: pointer;
}
</style>
