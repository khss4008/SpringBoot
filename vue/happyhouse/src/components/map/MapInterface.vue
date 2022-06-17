<template>
  <div id="interface-area">
    <div id="interface-component">
      <button-user></button-user>
      <button-notice></button-notice>
      <button-user-life-marker></button-user-life-marker>
      <button-user-life-distance></button-user-life-distance>
      <button-draw-range-circle></button-draw-range-circle>

      <search-box></search-box>
      <search-result-box></search-result-box>
    </div>
  </div>
</template>

<script>
import SearchBox from "@/components/map/components/SearchBox.vue";
import SearchResultBox from "@/components/map/components/SearchResultBox.vue";
import ButtonUser from "@/components/map/components/ButtonUser.vue";
import ButtonNotice from "@/components/map/components/ButtonNotice.vue";

import ButtonUserLifeMarker from "@/components/map/components/ButtonUserLifeMarker.vue";
import ButtonUserLifeDistance from "@/components/map/components/ButtonUserLifeDistance.vue";
import ButtonDrawRangeCircle from "@/components/map/components/ButtonDrawRangeCircle.vue";

export default {
  components: {
    SearchBox,
    SearchResultBox,
    ButtonUser,
    ButtonNotice,
    ButtonUserLifeMarker,
    ButtonUserLifeDistance,
    ButtonDrawRangeCircle,
  },
};
</script>

<style scoped>
#interface-area {
  position: absolute;
  top: 1vh;
  left: 1vw;
  width: 98vw;
  height: 98vh;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0);
  z-index: 2;
  pointer-events: none;
}

/* 인터페이스 컴포넌트들의 포인터 이벤트를 켜준다. */
#interface-component {
  pointer-events: auto;
}
</style>
