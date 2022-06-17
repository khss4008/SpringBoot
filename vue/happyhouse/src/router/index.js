import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import MapView from "@/views/MapView.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/map",
    name: "map",
    component: MapView,
  },
  // {
  //   path: "/board",
  //   name: "board",
  //   component: () => import("@/views/BoardView.vue"),
  //   redirect: "/board/list",
  //   children: [
  //     {
  //       path: "/board/list",
  //       name: "boardList",
  //       component: () => import("@/components/board/BoardList.vue"),
  //     },
  //     {
  //       path: "/board/select/:qnano",
  //       name: "boardDetail",
  //       component: () => import("@/components/board/BoardDetail.vue"),
  //     },
  //     {
  //       path: "/board/modify/:qnano",
  //       name: "boardModify",
  //       component: () => import("@/components/board/BoardModify.vue"),
  //     },
  //     {
  //       path: "/board/create",
  //       name: "boardCreate",
  //       component: () => import("@/components/board/BoardCreate.vue"),
  //     },
  //   ],
  // },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
