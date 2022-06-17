<template>
  <b-container class="bv-example-row mt-3 text-center">
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron
          bg-variant="dark"
          text-variant="white"
          border-variant="dark"
        >
          <template #header> <b-icon icon="house"></b-icon>HappyHouse</template>

          <hr id="horizontal-line" class="my-4" />

          <p>
            <b-button variant="primary" href="#" @click="moveToMap()"
              >찾으러가기</b-button
            >
          </p>
          <p>
            <span>김영진, 김제관</span>
          </p>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  methods: {
    moveToMap() {
      this.$router.push("/map");
    },
  },
};
</script>

<style scoped>
#horizontal-line {
  background-color: white;
}
</style>
