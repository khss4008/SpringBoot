const noticeStore = {
  namespaced: true,
  state: {
    isShowModal: false,
  },
  getters: {
    getIsShowModal: function (state) {
      return state.isShowModal;
    },
  },
  mutations: {
    SET_IS_SHOW_MODAL: (state, isShowModal) => {
      state.isShowModal = isShowModal;
    },
  },
  actions: {
    openNoticeModal({ commit }, isShowModal) {
      commit("SET_IS_SHOW_MODAL", isShowModal);
    },
  },
};

export default noticeStore;
