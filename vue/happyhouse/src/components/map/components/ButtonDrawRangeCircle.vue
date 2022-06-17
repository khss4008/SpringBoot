<template>
  <div id="button" @click="toggleDrawRangeCircleMode()">
    <img id="on-img" v-if="draw_range_circle" src="@/assets/btn_range.png" />
    <img id="off-img" v-else src="@/assets/btn_range.png" />
  </div>
</template>

<script>
import { mapState } from "vuex";

const mapStore = "mapStore";

export default {
  computed: {
    ...mapState(mapStore, ["draw_range_circle"]),
  },
  methods: {
    toggleDrawRangeCircleMode() {
      this.$store.dispatch(
        `${mapStore}/update_draw_range_circle`,
        !this.draw_range_circle
      );
    },
  },
};
</script>

<style scoped>
#button {
  position: absolute;
  top: 160px;
  right: 0px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
  background-color: white;
}

#off-img {
  filter: opacity(0.5) drop-shadow(0 0 0 rgb(70, 70, 70));
}
</style>
