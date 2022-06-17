import {
  getHouseInfoDeal,
  getHouseInfoDealByRadius,
} from "@/api/houseInfoDeal";

export default {
  namespaced: true,
  state: {
    result_items: [],
    result_houses: {},
    selected_house: {},
    isLoading: false,
  },
  getters: {
    result_houses_array(state) {
      let array = [];
      for (let aptCode in state.result_houses) {
        array.push({
          aptCode: aptCode,
          name: state.result_houses[aptCode][0].apartmentName,
          dong: state.result_houses[aptCode][0].dong,
          deals: state.result_houses[aptCode],
          lat: state.result_houses[aptCode][0].lat,
          lng: state.result_houses[aptCode][0].lng,
        });
      }

      for (let item of array) {
        item.avgPrice = parseInt(
          item.deals.reduce((sum, cur) => {
            return sum + parseInt(cur.dealAmount.split(",").join(""));
          }, 0) / item.deals.length
        );
      }

      return array;
    },
  },
  mutations: {
    UPDATE_RESULT(state, items) {
      state.result_items = items;
      state.result_houses = {};
      for (let item of items) {
        if (!state.result_houses[item.aptCode])
          state.result_houses[item.aptCode] = [];
        state.result_houses[item.aptCode].push(item);
      }
    },
    SELECT_HOUSE(state, house) {
      state.selected_house = house;
    },
    UPDATE_IS_LOADING(state, loading) {
      state.isLoading = loading;
    },
  },
  actions: {
    async search(context, { keyword, search_type }) {
      context.commit("UPDATE_IS_LOADING", true);
      await getHouseInfoDeal(
        keyword,
        search_type,
        ({ data }) => {
          context.commit("UPDATE_RESULT", data);
          context.commit("UPDATE_IS_LOADING", false);
        },
        () => {
          context.commit("UPDATE_RESULT", []);
        }
      );
    },
    async search_by_radius(context, { lat, lng, radius }) {
      context.commit("UPDATE_IS_LOADING", true);
      await getHouseInfoDealByRadius(
        lat,
        lng,
        radius,
        ({ data }) => {
          context.commit("UPDATE_RESULT", data);
          context.commit("UPDATE_IS_LOADING", false);
        },
        () => {
          context.commit("UPDATE_RESULT", []);
        }
      );
    },
    select_house(context, house) {
      context.commit("SELECT_HOUSE", house);
    },
    update_is_loading(context, isLoading) {
      context.commit("UPDATE_IS_LOADING", isLoading);
    },
  },
};
