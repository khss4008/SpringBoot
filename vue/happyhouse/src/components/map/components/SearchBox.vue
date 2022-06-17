<template>
  <div id="search-box">
    <b-input-group class="search-bar">
      <template #prepend>
        <b-dropdown :text="dropDownText" variant="notice-2">
          <b-dropdown-item
            v-for="(item, index) in dropDownItems"
            :key="index"
            @click="selectDropDownItem"
            >{{ item }}</b-dropdown-item
          >
        </b-dropdown>
      </template>

      <b-form-input
        class="search-bar"
        :placeholder="searchPlaceholder"
        v-model="keyword"
        @keydown.enter="search()"
      ></b-form-input>

      <b-input-group-append>
        <b-input-group-text id="search-button" @click="search()">
          <b-icon icon="search" />
        </b-input-group-text>
      </b-input-group-append>
    </b-input-group>
  </div>
</template>

<script>
import { SEARCH_TYPE, SEARCH_TYPE_KEYS } from "@/config/constant";
import { mapActions } from "vuex";

const searchStore = "searchStore";

export default {
  data() {
    return {
      dropDownText: "아파트 명",
      dropDownItems: SEARCH_TYPE_KEYS,
      searchPlaceholder: "검색하세요",
      keyword: "",
    };
  },
  methods: {
    ...mapActions(searchStore, ["search"]),
    search() {
      if (this.keyword.length == 0) return;
      if (this.dropDownText == SEARCH_TYPE_KEYS[1]) {
        alert("동 이름 검색 추후 지원 예정입니다.");
        return;
      }

      this.$store.dispatch(`${searchStore}/search`, {
        keyword: this.keyword,
        search_type: SEARCH_TYPE[this.dropDownText],
      });
    },
    selectDropDownItem(e) {
      this.dropDownText = e.target.text;
    },
  },
};
</script>

<style scoped>
#search-box {
  position: absolute;
  top: 5px;
  left: 10px;
  width: 350px;
  background-color: rgba(255, 255, 255, 1);
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
}

.search-bar {
  border: none;
}

.search-bar:focus {
  border-color: lightseagreen;
  box-shadow: none;
}

#search-button:hover {
  cursor: pointer;
}
</style>
