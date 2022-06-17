<template>
  <div>
    <div v-show="isLogin">
      <img
        id="userlife"
        v-show="isLogin"
        @click="modalShow = !modalShow"
        src="@/assets/favorite.png"
      />
      생활 영역 관리
    </div>
    <b-modal
      id="notice-modal"
      title="FAVORITE"
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
      <life-list></life-list>
    </b-modal>
  </div>
</template>

<script>
import LifeList from "@/components/map/components/LifeList.vue";

const memberStore = "memberStore";
import { mapState } from "vuex";
export default {
  components: {
    LifeList,
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
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
};
</script>

<style>
#userlife {
  width: 34px;
  height: 34px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
}
</style>
