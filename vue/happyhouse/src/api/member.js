import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api
    .post(`/userinfo/login`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}
async function updateUser(user, success, fail) {
  await api
    .put(`/userinfo/update`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}
async function selectAll(success, fail) {
  await api.get(`/userinfo/list`).then(success).catch(fail);
}

async function deleteUser(uid, success, fail) {
  await api
    .delete(`/userinfo/delete/${uid}`, JSON.stringify(uid))
    .then(success)
    .catch(fail);
}

async function findById(uid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`./userinfo/info/${uid}`).then(success).catch(fail);
}

async function logout(success, fail) {
  await api.post(`/userinfo/logout`).then(success).catch(fail);
}

async function regist(user, success, fail) {
  await api
    .post(`/userinfo/insert`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

function getUserLife(uid, success, fail) {
  api.get(`/userlife/list/${uid}`).then(success).catch(fail);
}

function insertUserLife(userlife, success, fail) {
  api
    .post(`/userlife/insert`, JSON.stringify(userlife))
    .then(success)
    .catch(fail);
}

export {
  login,
  findById,
  logout,
  regist,
  deleteUser,
  updateUser,
  selectAll,
  insertUserLife,
  getUserLife,
};
