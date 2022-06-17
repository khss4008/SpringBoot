<template>
  <div>
    <kakao-map></kakao-map>
    <map-interface></map-interface>
  </div>
</template>

<script>
import KakaoMap from "@/components/map/KakaoMap.vue";
import MapInterface from "@/components/map/MapInterface.vue";

export default {
  data() {
    return {};
  },
  components: {
    KakaoMap,
    MapInterface,
  },
};
</script>

<style></style>
