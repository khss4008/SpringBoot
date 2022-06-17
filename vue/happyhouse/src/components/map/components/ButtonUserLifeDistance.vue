<template>
  <div id="button" @click="toggleUserLifeDistanceOverlay()">
    <div v-if="show_life_marker">
      <img
        id="on-img"
        v-if="show_life_distance_overlay"
        src="@/assets/btn_distance.png"
      />
      <img id="off-img" v-else src="@/assets/btn_distance.png" />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const mapStore = "mapStore";

export default {
  computed: {
    ...mapState(mapStore, ["show_life_marker", "show_life_distance_overlay"]),
  },
  methods: {
    toggleUserLifeDistanceOverlay() {
      this.$store.dispatch(
        `${mapStore}/update_show_life_distance_overlay`,
        !this.show_life_distance_overlay
      );
    },
  },
};
</script>

<style scoped>
#button {
  position: absolute;
  top: 120px;
  right: 50px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
  background-color: white;
}

#off-img {
  filter: opacity(0.5) drop-shadow(0 0 0 rgb(70, 70, 70));
}
</style>
