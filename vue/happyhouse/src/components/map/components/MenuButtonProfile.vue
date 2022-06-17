<template>
  <div>
    <div v-show="isLogin">
      <img id="profile" @click="modalOpen()" src="@/assets/info.png" />
      프로필 보기
    </div>
    <b-modal
      id="modal-lg"
      size="lg"
      title="PROFILE"
      v-model="modalShow"
      @ok="handleOk"
      >아이디
      <b-form-input
        id="name-input"
        :disabled="true"
        v-model="user.userId"
        required
      ></b-form-input>
      <br />
      비밀번호
      <b-form-input
        id="name-input"
        :disabled="change == true"
        v-model="user.userPwd"
        required
      ></b-form-input>
      이름
      <br />
      <b-form-input
        id="name-input"
        :disabled="change == true"
        v-model="user.userName"
        required
      ></b-form-input>
      주소
      <br />
      <b-form-input
        id="name-input"
        :disabled="change == true"
        v-model="user.userAddr"
        required
      ></b-form-input>
      전화번호
      <br />
      <b-form-input
        id="name-input"
        :disabled="change == true"
        v-model="user.userTel"
        required
      ></b-form-input>
      <br />
      <b-button variant="info" @click="change = !change">변경</b-button>
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { updateUser } from "@/api/member.js";
// import { apiInstance } from "@/api/index";
const memberStore = "memberStore";
// const api = apiInstance();
export default {
  data() {
    return {
      user: {
        uid: 0,
        userId: null,
        userPwd: null,
        userName: null,
        userAddr: null,
        userTel: null,
        role: null,
        joinDate: null,
      },
      modalShow: false,
      change: true,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userUpdate"]),
    handleOk() {
      this.userUpdate(this.user);
      // alert(JSON.stringify(this.user));
      // alert(JSON.stringify(this.userInfo));
      updateUser(
        this.userInfo,
        () => {
          alert("프로필 수정이 완료되었습니다.");
        },
        () => {
          alert("프로필 수정에 실패했습니다.");
        }
      );
    },
    modalOpen() {
      this.modalShow = !this.modalShow;
      if (this.userInfo.uid != null) {
        this.user = this.userInfo;
      }
    },
  },
  async created() {
    // await api
    //   .get(`/userinfo/select/${this.userInfo.uid}`)
    //   .then(({ data }) => {
    //     this.user = data;
    //   })
    //   .catch();
  },
};
</script>

<style>
#profile {
  width: 34px;
  height: 34px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
}
</style>
