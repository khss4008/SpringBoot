import { apiInstance } from "./index.js";

const api = apiInstance();

function getHouseInfoDeal(keyword, type, success, fail) {
  let url = "infodeal";

  if (type == 1) {
    url += `/apt/${keyword} `;
  } else if (type == 2) {
    url += `/dong/${keyword}`;
  }

  api.get(url).then(success).catch(fail);
}

function getHouseInfoDealByRadius(lat, lng, radius, success, fail) {
  api
    .get(`infodeal/radius`, {
      params: {
        lat: lat,
        lng: lng,
        radius: radius,
      },
    })
    .then(success)
    .catch(fail);
}

export { getHouseInfoDeal, getHouseInfoDealByRadius };
