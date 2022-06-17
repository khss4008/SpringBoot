<template>
  <div class="home">
    <img id="logo" src="@/assets/logo.jpg" />
    <happy-house></happy-house>
  </div>
</template>

<script>
import HappyHouse from "@/components/HappyHouse.vue";

export default {
  name: "HomeView",
  components: { HappyHouse },
};
</script>

<style scoped>
#logo {
  width: 30%;
}
</style>
