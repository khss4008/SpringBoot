import jwt_decode from "jwt-decode";
import { login, getUserLife } from "@/api/member.js";
import { findById } from "../../api/member";
import { logout } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: {},
    userlife: [],
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    LOGOUT(state) {
      state.isLogin = false;
      sessionStorage.clear();
      location.reload();
    },
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_USER_LIFE(state, userlife) {
      state.userlife = userlife;
    },
    ADD_USER_LIFE(state, userlife) {
      state.userlife.push(userlife);
    },
  },
  actions: {
    // registConfirm(context, user) {},
    userUpdate({ commit }, user) {
      commit("SET_USER_INFO", user);
    },
    lifeUpdate({ commit }, userlife) {
      commit("SET_USER_LIFE", userlife);
    },
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.uid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
            getUserLife(
              decode_token.uid,
              ({ data }) => {
                commit("SET_USER_LIFE", data);
              },
              (error) => {
                console.error(error);
              }
            );

            alert("로그인 성공 !");
          } else {
            console.log("유저 정보 없음!!");
            console.log(response.data.message);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    logout2({ commit }) {
      logout(
        () => {
          // alert(response);
          // sessionStorage.clear();
          commit("LOGOUT");
        },
        (error) => {
          console.log(error);
        }
      );
    },

    updateUserLife(context, userlife) {
      context.commit("ADD_USER_LIFE", userlife);
    },
  },
};

export default memberStore;
