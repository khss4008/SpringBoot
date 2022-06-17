export default {
  namespaced: true,
  state: {
    selected_position: null,
    show_modal_spinner: false,

    show_life_marker: true,
    show_life_distance_overlay: false,

    draw_range_circle: false,
    range_circle_radius: 0,
    map: {},
  },
  getters: {},
  mutations: {
    UPDATE_SELECTED_POSITION(state, position) {
      state.selected_position = position;
    },
    UPDATE_MODAL_SPINNER(state, visible) {
      state.show_modal_spinner = visible;
    },
    SET_MAP(state, map) {
      state.map = map;
    },
    SET_SHOW_LIFE_MAKER(state, value) {
      state.show_life_marker = value;
    },
    SET_SHOW_LIFE_DISTANCE_OVERLAY(state, value) {
      state.show_life_distance_overlay = value;
    },
    SET_DRAW_RANGE_CIRCLE(state, value) {
      state.draw_range_circle = value;
    },
    SET_RANGE_CIRCLE_RADIUS(state, radius) {
      state.range_circle_radius = radius;
    },
  },
  actions: {
    set_map(context, map) {
      context.commit("SET_MAP", map);
    },
    update_selected_position(context, position) {
      context.commit("UPDATE_SELECTED_POSITION", position);
    },
    update_show_life_marker(context, value) {
      context.commit("SET_SHOW_LIFE_MAKER", value);
      if (!value) context.commit("SET_SHOW_LIFE_DISTANCE_OVERLAY", value);
    },
    update_show_life_distance_overlay(context, value) {
      context.commit("SET_SHOW_LIFE_DISTANCE_OVERLAY", value);
    },
    update_draw_range_circle(context, value) {
      context.commit("SET_DRAW_RANGE_CIRCLE", value);
    },
    update_range_circle(context, radius) {
      context.commit("SET_RANGE_CIRCLE_RADIUS", radius);
    },
  },
};
