<template>
  <div
    id="search-result-item"
    v-b-toggle.search-result-side-bar
    @click="select_house()"
  >
    <div id="upper">
      <span id="dong">
        {{ houses.dong }}
      </span>
      <span id="apartment-name">
        {{ houses.name }}
      </span>
    </div>
    <div id="lower">
      <div id="deal-count">전체 매매 수 {{ houses.deals.length }}개</div>
      <div id="avg-price">평균 거래가 {{ houses.avgPrice }}만원</div>
      <div v-show="userlife.length > 0">
        <div id="far">가장 먼 곳 [{{ farName }}] {{ maxD }}m</div>
        <div id="close">가장 가까운 곳 [{{ closeName }}] {{ minD }}m</div>
        <div id="avg">생활 영역으로부터 평균 거리 {{ avgD }}m</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const mapStore = "mapStore";
const searchStore = "searchStore";
const memberStore = "memberStore";

const default_maxD = 0;
const default_minD = 1000000000;
const default_avgD = 0;
const default_farName = "";
const default_closeName = "";

export default {
  data() {
    return {
      maxD: default_maxD,
      minD: default_minD,
      avgD: default_avgD,
      farName: default_farName,
      closeName: default_closeName,
    };
  },
  computed: {
    ...mapState(mapStore, ["map"]),
    ...mapState(memberStore, ["userlife"]),
  },
  watch: {
    userlife() {
      this.distance();
    },
  },
  props: {
    houses: {},
  },
  methods: {
    select_house() {
      /* global kakao */
      this.map.panTo(new kakao.maps.LatLng(this.houses.lat, this.houses.lng));
      this.$store.dispatch(`${searchStore}/select_house`, this.houses);
    },
    distance() {
      this.maxD = default_maxD;
      this.minD = default_minD;
      this.avgD = default_avgD;

      for (var i = 0; i < this.userlife.length; i++) {
        var radLat1 = (Math.PI * this.houses.lat) / 180;
        var radLat2 = (Math.PI * this.userlife[i].lat) / 180;
        var theta = Math.abs(this.houses.lng - this.userlife[i].lng);
        var radTheta = (Math.PI * theta) / 180;
        var dist =
          Math.sin(radLat1) * Math.sin(radLat2) +
          Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
        if (dist > 1) dist = 1;
        dist = Math.acos(dist);
        dist = (dist * 180) / Math.PI;
        dist = dist * 60 * 1.1515 * 1.609344 * 1000;
        if (dist < 100) dist = Math.round(dist / 10) * 10;
        else dist = Math.round(dist / 100) * 100;
        if (dist > this.maxD) {
          this.maxD = dist;
          this.farName = this.userlife[i].locationName;
        }
        if (dist < this.minD) {
          this.minD = dist;
          this.closeName = this.userlife[i].locationName;
        }

        this.avgD += dist;
      }
      this.avgD /= this.userlife.length;
      this.avgD = Math.floor(this.avgD);
    },
  },
  created() {
    this.distance();
  },
  mounted() {
    this.distance();
  },
};
</script>

<style>
#search-result-item {
  padding: 5px;
  width: 100%;
  text-align: left;
}

#search-result-item:hover {
  background-color: rgb(245, 245, 245);
  cursor: pointer;
}

#upper {
  font-size: 18px;
}

#lower {
  font-size: 13px;
  color: grey;
}
</style>
