<template>
  <div id="search-result-box">
    <div v-if="isLoading">
      <b-container>
        <b-row><div id="result_div">찾아보는 중</div></b-row>
        <b-row id="loading-row">
          <b-spinner
            v-if="isLoading"
            style="width: 3rem; height: 3rem"
            label="Loading"
            type="grow"
          ></b-spinner>
        </b-row>
      </b-container>
    </div>
    <div v-else>
      <div v-if="result_houses_array.length == 0">
        <b-container>
          <b-row
            align-v="center"
            class="justify-content-md-center"
            id="no-result-row"
          >
            <b-col
              ><img src="@/assets/no-result.png" width="100%" height="100%"
            /></b-col>
          </b-row>
        </b-container>
      </div>
      <div v-else>
        <b-container>
          <b-row><div id="result_div">검색 결과</div></b-row>
          <b-row v-for="(houses, index) in result_houses_array" :key="index">
            <search-result-item :houses="houses"></search-result-item>
          </b-row>
        </b-container>
      </div>
    </div>

    <search-result-side-bar></search-result-side-bar>
  </div>
</template>

<script>
import SearchResultItem from "@/components/map/components/SearchResultItem.vue";
import SearchResultSideBar from "@/components/map/components/SearchResultSideBar.vue";

import { mapGetters, mapState } from "vuex";

const searchStore = "searchStore";

export default {
  components: {
    SearchResultItem,
    SearchResultSideBar,
  },
  computed: {
    ...mapGetters(searchStore, ["result_houses_array"]),
    ...mapState(searchStore, ["isLoading"]),
  },
};
</script>

<style>
#search-result-box {
  position: absolute;
  top: 55px;
  left: 10px;
  width: 350px;
  height: 700px;
  background-color: rgba(252, 252, 252, 1);
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
  word-break: break-all;
  overflow: auto;
}

#no-result-row {
  height: 650px;
}

#result_div {
  width: 100%;
  height: 50px;
  line-height: 50px;
  font-size: 20px;
  color: white;
  background-color: rgba(77, 85, 178, 1);
}

#loading-row {
  height: 550px;
  margin-left: 42%;
  align-content: center;
}
</style>
