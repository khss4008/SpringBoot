<template>
  <div>
    <div v-show="isLogin">
      <img
        id="withdrawal"
        @click="modalShow = !modalShow"
        src="@/assets/withdrawal.png"
      />
      회원 탈퇴
    </div>
    <b-modal
      id="modal-lg"
      size="lg"
      title="회원 탈퇴"
      v-model="modalShow"
      @ok="handleOk"
      >정말 회원 탈퇴 하시겠습니까?
    </b-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
import { deleteUser } from "@/api/member.js";
export default {
  data() {
    return {
      modalShow: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo", "isLogin"]),
  },
  methods: {
    handleOk() {
      deleteUser(this.userInfo.uid);
      location.reload();
    },
  },
};
</script>

<style>
#withdrawal {
  width: 34px;
  height: 34px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
}
</style>
