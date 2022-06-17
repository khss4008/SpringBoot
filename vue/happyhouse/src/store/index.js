import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import memberStore from "@/store/modules/memberStore.js";
import noticeStore from "@/store/modules/noticeStore.js";
import searchStore from "@/store/modules/searchStore.js";
import mapStore from "@/store/modules/mapStore.js";

export default new Vuex.Store({
  modules: { noticeStore, memberStore, searchStore, mapStore },
});
