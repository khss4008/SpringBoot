<template>
  <div v-if="isLogin && userInfo.role === 'admin'">
    <img id="admin" @click="modalShow = !modalShow" src="@/assets/admin.png" />
    <b-modal
      id="notice-modal"
      title="USER"
      ref="notice-modal"
      v-model="modalShow"
      :header-bg-variant="bgVariant"
      :footer-bg-variant="bgVariant"
      :body-bg-variant="bgVariant"
      :footer-border-variant="bgVariant"
      :header-border-variant="bgVariant"
      :header-text-variant="textVariant"
      :body-text-variant="textVariant"
      :footer-text-variant="textVariant"
      :header-close-variant="textVariant"
      :ok-variant="buttonVariant"
      :cancel-variant="buttonVariant"
    >
      <admin-list></admin-list>
    </b-modal>
  </div>
</template>

<script>
import AdminList from "@/components/map/components/AdminList.vue";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  components: {
    AdminList,
  },
  data() {
    return {
      modalShow: false,
      bgVariant: "notice-3",
      textVariant: "notice-2",
      buttonVariant: "notice-2",
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
};
</script>

<style>
#admin {
  position: relative;
  top: 810px;
  right: 0px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
  width: 10%;
}
</style>
