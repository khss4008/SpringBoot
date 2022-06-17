<template>
  <div id="button" @click="toggleUserLifeMarker()">
    <img id="on-img" v-if="show_life_marker" src="@/assets/btn_marker.png" />
    <img id="off-img" v-else src="@/assets/btn_marker.png" />
  </div>
</template>

<script>
import { mapState } from "vuex";

const mapStore = "mapStore";

export default {
  computed: {
    ...mapState(mapStore, ["show_life_marker"]),
  },
  methods: {
    toggleUserLifeMarker() {
      this.$store.dispatch(
        `${mapStore}/update_show_life_marker`,
        !this.show_life_marker
      );
    },
  },
};
</script>

<style scoped>
#button {
  position: absolute;
  top: 120px;
  right: 0px;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
  background-color: white;
}

#off-img {
  filter: opacity(0.5) drop-shadow(0 0 0 rgb(70, 70, 70));
}
</style>
