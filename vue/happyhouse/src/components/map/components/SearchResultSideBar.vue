<template>
  <b-sidebar id="search-result-side-bar" :title="title" shadow width="350px">
    <b-container>
      <b-row
        ><div id="head-div" v-if="selected_house.deals">
          전체 {{ selected_house.deals.length }}개의 매매 내역
        </div></b-row
      >
      <b-row v-for="(deal, index) in selected_house.deals" :key="index">
        <search-result-side-bar-item :deal="deal"></search-result-side-bar-item>
      </b-row>
    </b-container>
  </b-sidebar>
</template>

<script>
import SearchResultSideBarItem from "@/components/map/components/SearchResultSideBarItem.vue";
import { mapState } from "vuex";

const searchStore = "searchStore";

export default {
  components: {
    SearchResultSideBarItem,
  },
  computed: {
    ...mapState(searchStore, ["selected_house"]),
    title() {
      return this.selected_house.dong + " " + this.selected_house.name;
    },
  },
};
</script>

<style>
#head-div {
  width: 100%;
  height: 50px;
  line-height: 50px;
  font-size: 20px;
  color: white;
  background-color: rgba(77, 85, 178, 1);
}
</style>
