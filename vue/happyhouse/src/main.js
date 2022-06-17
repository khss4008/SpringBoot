import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

// Vue 부트스트랩 사용
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
Vue.use(BootstrapVue);

// Vue 부트스트랩 아이콘 사용
import { BootstrapVueIcons } from "bootstrap-vue";
Vue.use(BootstrapVueIcons);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
