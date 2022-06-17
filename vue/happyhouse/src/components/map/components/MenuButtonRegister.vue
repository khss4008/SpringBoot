<template>
  <div>
    <div v-show="!isLogin">
      <img
        id="regist"
        v-show="!isLogin"
        @click="modalShow = !modalShow"
        src="@/assets/regist.png"
      />
      회원 가입
    </div>
    <b-modal
      id="modal-lg"
      size="lg"
      title="Regist"
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
        v-model="user.userPwd"
        required
      ></b-form-input>
      이름
      <br />
      <b-form-input
        id="name-input"
        v-model="user.userName"
        required
      ></b-form-input>
      주소
      <br />
      <b-form-input
        id="name-input"
        v-model="user.userAddr"
        required
      ></b-form-input>
      전화번호
      <br />
      <b-form-input
        id="name-input"
        v-model="user.userTel"
        required
      ></b-form-input>
      <br />
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { regist } from "@/api/member.js";
const memberStore = "memberStore";
// import { regist } from "@/api/member";
export default {
  data() {
    return {
      user: {
        uid: null,
        userId: null,
        userPwd: null,
        userName: null,
        userAddr: null,
        userTel: null,
        joinDate: null,
      },
      modalShow: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["registConfirm"]),
    async handleOk() {
      //   alert(this.user.userId + " " + this.user.userPwd);
      //   alert(JSON.stringify(this.user));
      //   await this.registConfirm(this.user);
      regist(
        this.user,
        () => {
          alert("회원가입이 완료되었습니다.");
        },
        () => {
          alert("이미 사용중인 아이디입니다.");
        }
      );
    },
  },
};
</script>

<style>
#regist {
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
  width: 34px;
  height: 34px;
}
</style>
