<template>
  <div>
    <b-table
      id="user-table"
      hover
      borderless
      small
      head-variant="dark"
      table-variant="dark"
      :fields="fields"
      :items="lifeList"
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
    <!-- Modal -->
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
        title="DATA"
        v-model="modalShow"
        @ok="OkButtonClick"
        >번호
        <b-form-input
          id="name-input"
          :disabled="true"
          v-model="lifeData.uid"
          required
        ></b-form-input>
        <br />
        회원번호
        <b-form-input
          id="name-input"
          :disabled="change == true"
          v-model="lifeData.lid"
          required
        ></b-form-input>
        별명
        <br />
        <b-form-input
          id="name-input"
          :disabled="change == true"
          v-model="lifeData.locationName"
          required
        ></b-form-input>
        위도
        <br />
        <b-form-input
          id="name-input"
          :disabled="change == true"
          v-model="lifeData.lat"
          required
        ></b-form-input>
        경도
        <br />
        <b-form-input
          id="name-input"
          :disabled="change == true"
          v-model="lifeData.lng"
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
import {
  getFavoriteList,
  deleteFavorite,
  updateFavorite,
} from "@/api/favorite.js";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  computed: {
    ...mapState(memberStore, ["userInfo", "userlife"]),
  },
  mounted() {
    getFavoriteList(
      this.userInfo.uid,
      (response) => {
        // alert("유저 리스트를 가져왔습니다.");
        this.lifeList = response.data;
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
          key: "lid",
          label: "번호",
        },
        {
          key: "locationName",
          label: "별명",
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
      lifeList: [],
      lifeData: {
        lid: 0,
        uid: null,
        locationName: null,
        lat: null,
        lng: null,
      },
      modalShow: false,
      change: true,
    };
  },
  methods: {
    ...mapActions(memberStore, ["lifeUpdate"]),
    OkButtonClick() {
      // this.userUpdate(this.user);
      // alert(this.user);
      updateFavorite(
        this.lifeData,
        () => {
          alert("데이터 수정이 완료되었습니다.");
          getFavoriteList(
            this.userInfo.uid,
            (response) => {
              // alert("유저 리스트를 가져왔습니다.");
              this.lifeList = response.data;
              this.lifeUpdate(this.lifeList);
            },
            () => {
              alert("데이터 리스트 불러오는 데 실패했습니다.");
            }
          );
        },
        () => {
          alert("데이터 수정에 실패했습니다.");
        }
      );
      this.$refs["updateModal"].hide();
    },
    modalOpen(data) {
      this.lifeData = data;
      this.modalShow = !this.modalShow;
    },
    deleteData(item) {
      // alert(JSON.stringify(item));
      deleteFavorite(
        item.lid,
        () => {
          alert("데이터를 삭제했습니다.");
          getFavoriteList(
            this.userInfo.uid,
            (response) => {
              // alert("유저 리스트를 가져왔습니다.");
              this.lifeList = response.data;
              this.lifeUpdate(this.lifeList);
            },
            () => {
              alert("데이터 리스트 불러오는 데 실패했습니다.");
            }
          );
          //여기다가 삭제하고 나서 값을 state에 업데이트 해줘야 함
          // this.lifeList = response.data;
        },
        () => {
          alert("데이터를 삭제하는 데 실패했습니다.");
        }
      );
    },
  },
};
</script>

<style></style>
