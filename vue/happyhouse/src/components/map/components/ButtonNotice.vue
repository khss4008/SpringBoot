<template>
  <div>
    <img
      id="notice-button"
      @click="modalShow = !modalShow"
      src="@/assets/button-notice.png"
    />
    <b-modal
      id="notice-modal"
      title="Notice"
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
      <notice-list></notice-list>
    </b-modal>
  </div>
</template>

<script>
import NoticeList from "@/components/map/components/NoticeList.vue";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  components: {
    NoticeList,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  data() {
    return {
      modalShow: false,
      bgVariant: "notice-3",
      textVariant: "notice-2",
      buttonVariant: "notice-2",
    };
  },
};
</script>

<style scoped>
#notice-button {
  position: absolute;
  top: 40px;
  right: 0px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
  background-color: white;
}

#notice-button:hover {
  cursor: pointer;
}
</style>
