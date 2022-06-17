<template>
  <div id="search-result-side-bar-item" @click="clickItem()">
    <div>
      <div id="item-head">
        {{ deal.dealYear }}년 {{ deal.dealMonth }}월 {{ deal.dealDay }}일 매매
      </div>
      <div id="item-body">
        {{ deal.floor }}층 - {{ deal.dealAmount }}만원<br />
      </div>
    </div>

    <b-collapse v-model="visible" class="mt-2">
      <b-card id="item-detail">
        전용 면적 : {{ deal.area }} (㎡)<br />
        건축 년도 : {{ deal.buildYear }} 년<br />
      </b-card>
    </b-collapse>
  </div>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
    };
  },
  props: {
    deal: {},
  },
  methods: {
    clickItem() {
      this.visible = !this.visible;
    },
  },
};
</script>

<style>
#search-result-side-bar-item {
  padding: 5px;
  width: 100%;
  text-align: left;
}

#search-result-side-bar-item:hover {
  background-color: rgb(245, 245, 245);
  cursor: pointer;
  color: rgba(77, 85, 178, 1);
}

#item-head {
  font-size: 18px;
}

#item-body {
  font-size: 13px;
  color: grey;
}

#item-detail {
  font-size: 15px;
  color: grey;
}
</style>
