import { apiInstance } from "./index.js";

const api = apiInstance();

function getNoticeList(success, fail) {
  api.get("notice/list").then(success).catch(fail);
}

export { getNoticeList };
