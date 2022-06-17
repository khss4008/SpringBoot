import { apiInstance } from "./index.js";

const api = apiInstance();

function getFavoriteList(uid, success, fail) {
  api.get(`userlife/list/${uid}`).then(success).catch(fail);
}
async function deleteFavorite(lid, success, fail) {
  await api
    .delete(`/userlife/delete/${lid}`, JSON.stringify(lid))
    .then(success)
    .catch(fail);
}
async function updateFavorite(userLife, success, fail) {
  await api
    .put(`/userlife/update`, JSON.stringify(userLife))
    .then(success)
    .catch(fail);
}

export { getFavoriteList, deleteFavorite, updateFavorite };
