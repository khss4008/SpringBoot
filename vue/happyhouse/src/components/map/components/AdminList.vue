<template>
  <div id="user-list">
    <b-table
      id="user-table"
      hover
      borderless
      small
      head-variant="dark"
      table-variant="dark"
      :fields="fields"
      :items="userList"
    >
      <template v-slot:cell(update)="data">
        <button class="btn btn-success" @click="modalOpen(data.item)">
          <i class="fas fa-trash-alt"></i>
        </button>
      </template>
      <template v-slot:cell(delete)="data">
        <button class="btn btn-danger" @click="deleteData(data.item)">
          <i class="fas fa-trash-alt"></i>
        </button>
      </template>
    </b-table>
    <!-- updateModal -->
    <div v-if="modalShow">
      <b-modal
        id="updateModal"
        ref="updateModal"
        :header-bg-variant="headerBgVariant"
        :header-text-variant="headerTextVariant"
        :body-bg-variant="bodyBgVariant"
        :body-text-variant="bodyTextVariant"
        :footer-bg-variant="footerBgVariant"
        :footer-text-variant="footerTextVariant"
        size="sm"
        title="PROFILE"
        v-model="modalShow"
        @ok="OkButtonClick"
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
        <template #modal-footer>
          <div class="w-100">
            <p class="float-left">Modal Footer Content</p>
            <b-button
              variant="primary"
              size="sm"
              class="float-center"
              @click="$bvModal.hide('updateModal')"
            >
              Cancel
            </b-button>
            <b-button
              variant="primary"
              size="sm"
              class="float-right"
              @click="OkButtonClick()"
            >
              OK
            </b-button>
          </div>
        </template>
      </b-modal>
    </div>
  </div>
</template>

<script>
import { selectAll, deleteUser } from "@/api/member.js";
import { mapActions } from "vuex";
const memberStore = "memberStore";
import { updateUser } from "@/api/member.js";
// const memberStore = "memberStore";
export default {
  methods: {
    ...mapActions(memberStore, ["userUpdate"]),

    OkButtonClick() {
      // this.userUpdate(this.user);
      // alert(this.user);
      updateUser(
        this.user,
        (response) => {
          alert("프로필 수정이 완료되었습니다.");
          this.userList = response.data;
        },
        () => {
          alert("프로필 수정에 실패했습니다.");
        }
      );
      this.$refs["updateModal"].hide();
    },

    deleteData(item) {
      // alert(JSON.stringify(item));
      deleteUser(
        item.uid,
        (response) => {
          alert("유저를 삭제했습니다.");
          this.userList = response.data;
        },
        () => {
          alert("유저를 삭제하는 데 실패했습니다.");
        }
      );
    },
    modalOpen(data) {
      // alert(JSON.stringify(data));
      this.user = data;
      // alert(JSON.stringify(this.user));
      this.modalShow = !this.modalShow;
      // alert(this.modalShow);
      // alert(this.modalShow);
    },
  },
  mounted() {
    selectAll(
      (response) => {
        // alert("유저 리스트를 가져왔습니다.");
        this.userList = response.data;
      },
      () => {
        alert("유저 리스트 불러오는 데 실패했습니다.");
      }
    );
  },
  data() {
    return {
      headerBgVariant: "dark",
      headerTextVariant: "dark",
      bodyBgVariant: "dark",
      bodyTextVariant: "dark",
      footerBgVariant: "dark",
      footerTextVariant: "dark",
      fields: [
        {
          key: "uid",
          label: "번호",
        },
        {
          key: "userId",
          label: "유저 ID",
        },
        {
          key: "userName",
          label: "유저 이름",
        },
        {
          key: "userAddr",
          label: "주소",
        },
        {
          key: "userTel",
          label: "전화번호",
        },
        {
          key: "update",
          label: "수정",
        },
        {
          key: "delete",
          label: "삭제",
        },
      ],
      userList: [],
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
};
</script>

<style></style>
