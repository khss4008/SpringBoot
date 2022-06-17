<template>
  <div>
    <img
      id="user-button"
      v-b-toggle.sidebar-right
      src="@/assets/button-user.png"
    />
    <b-sidebar id="sidebar-right" title="USER MENU" right shadow>
      <div id="menu-list">
        <menu-button-login id="menu-item"></menu-button-login>
        <menu-button-profile id="menu-item"></menu-button-profile>
        <menu-button-admin-setting id="menu-item"></menu-button-admin-setting>
        <menu-button-user-life id="menu-item"></menu-button-user-life>
        <menu-button-withdrawal id="menu-item"></menu-button-withdrawal>
        <menu-button-register id="menu-item"></menu-button-register>
      </div>
    </b-sidebar>
  </div>
</template>

<script>
import MenuButtonLogin from "@/components/map/components/MenuButtonLogin.vue";
import MenuButtonRegister from "@/components/map/components/MenuButtonRegister.vue";
import MenuButtonWithdrawal from "@/components/map/components/MenuButtonWithdrawal.vue";
import MenuButtonProfile from "@/components/map/components/MenuButtonProfile.vue";
import MenuButtonAdminSetting from "@/components/map/components/MenuButtonAdminSetting.vue";
import MenuButtonUserLife from "@/components/map/components/MenuButtonUserLife.vue";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  components: {
    MenuButtonLogin,
    MenuButtonRegister,
    MenuButtonWithdrawal,
    MenuButtonProfile,
    MenuButtonAdminSetting,
    MenuButtonUserLife,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
};
</script>

<style>
#user-button {
  position: absolute;
  top: 0px;
  right: 0px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
  background-color: white;
}

#menu-list {
  text-align: left;
}

#menu-item {
  padding: 15px;
}

#menu-item:hover {
  background-color: rgb(231, 231, 231);
}
</style>
