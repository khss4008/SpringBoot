<template>
  <div id="notice-list">
    <b-table
      id="notice-table"
      hover
      borderless
      small
      head-variant="dark"
      table-variant="dark"
      :fields="fields"
      :items="notices"
    ></b-table>
  </div>
</template>

<script>
import { getNoticeList } from "@/api/notice";

export default {
  data() {
    return {
      fields: [
        {
          key: "nid",
          label: "번호",
        },
        {
          key: "userName",
          label: "유저 이름",
        },
        {
          key: "title",
          label: "제목",
        },
        {
          key: "content",
          label: "내용",
        },
        {
          key: "regtime",
          label: "등록일",
        },
      ],
      notices: [],
    };
  },
  created() {
    getNoticeList(({ data }) => {
      this.notices = data;
    });
  },
};
</script>

<style></style>
