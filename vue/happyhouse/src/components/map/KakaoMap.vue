<template>
  <div>
    <div id="map" class="mx-auto"></div>
    <b-modal
      hide-footer
      title="이 장소의 이름을 지어주세요"
      centered
      header-bg-variant="app"
      header-text-variant="white"
      v-model="showAddModal"
    >
      <b-overlay :show="show_modal_spinner" rounded="sm">
        <div id="user-life-form">
          <b-form-input id="name-input" v-model="userLifeName"></b-form-input>
        </div>
        <b-button
          id="modal-btn"
          variant="app"
          @click="addUserLife"
          :disabled="userLifeName.length == 0"
          >추가</b-button
        >
      </b-overlay>
    </b-modal>

    <b-modal
      hide-footer
      :title="infoModalTitle"
      centered
      header-bg-variant="app"
      header-text-variant="white"
      v-model="showInfoModal"
    >
      <h5>{{ infoModalContent }}</h5>
      <b-button id="modal-btn" variant="app" @click="closeInfoModal"
        >닫기</b-button
      >
    </b-modal>
  </div>
</template>

<script>
import { insertUserLife } from "@/api/member";

import { KAKAO_MAP_URL } from "@/config";
import { mapState, mapActions } from "vuex";

const searchStore = "searchStore";
const mapStore = "mapStore";
const memberStore = "memberStore";

export default {
  name: "kakaoMap",
  components: {},
  data() {
    return {
      marker: null,
      searchMarkers: [],
      lifeMarkers: [],
      lifeMarkerInfoWindows: [],
      infowindow: null,
      showAddModal: false,
      showInfoModal: false,
      infoModalTitle: "",
      infoModalContent: "",
      userLifeName: "",

      distanceLines: [],
      distanceOverlays: [],

      drawingFlag: false,
      drawingCircle: null,
      drawingLine: null,
      drawingOverlay: null,
      drawingDot: null,
      centerPosition: null,
    };
  },
  computed: {
    ...mapState(searchStore, ["result_houses", "selected_house"]),
    ...mapState(mapStore, [
      "selected_position",
      "show_modal_spinner",
      "show_life_marker",
      "show_life_distance_overlay",
      "draw_range_circle",
      "range_circle",
    ]),
    ...mapState(memberStore, ["userInfo", "userlife"]),
  },
  watch: {
    result_houses(result_houses) {
      for (let marker of this.searchMarkers) marker.setMap(null);

      this.searchMarkers = [];
      for (let aptCode in result_houses) {
        let house = result_houses[aptCode][0];
        let markerPosition = new kakao.maps.LatLng(house.lat, house.lng);
        let marker = new kakao.maps.Marker({
          position: markerPosition,
          clickable: true,
        });
        marker.house = house;

        kakao.maps.event.addListener(marker, "click", () => {
          console.log(marker.house);
        });
        marker.setMap(this.map);
        this.searchMarkers.push(marker);
      }
    },
    userlife() {
      this.updateLifeMarkers();
    },
    selected_position() {
      this.updateDistanceLines();
    },
    show_life_marker(value) {
      for (let marker of this.lifeMarkers)
        marker.setMap(value ? this.map : null);

      for (let infowindow of this.lifeMarkerInfoWindows)
        infowindow.setMap(value ? this.map : null);
    },
    show_life_distance_overlay(value) {
      for (let line of this.distanceLines) line.setMap(value ? this.map : null);

      for (let overlay of this.distanceOverlays)
        overlay.setMap(value ? this.map : null);
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.type = "text/javascript";
      script.src = KAKAO_MAP_URL;
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(mapStore, ["set_map"]),
    openAddModal() {
      this.userLifeName = "";
      this.showAddModal = true;
    },
    closeAddModal() {
      this.showAddModal = false;
    },
    openInfoModal(title, content) {
      this.infoModalTitle = title;
      this.infoModalContent = content;
      this.showInfoModal = true;
    },
    closeInfoModal() {
      this.showInfoModal = false;
    },
    placeMarker(e) {
      const latlng = e.latLng;

      if (!this.marker) {
        this.marker = new kakao.maps.Marker({
          position: latlng,
          clickable: true,
        });
        kakao.maps.event.addListener(this.marker, "click", () => {
          console.log(this.marker);
        });
        this.marker.setMap(this.map);
      } else {
        this.marker.setPosition(latlng);
      }

      if (this.infowindow) {
        this.infowindow.setMap(null);
      }

      this.infowindow = new kakao.maps.CustomOverlay({
        clickable: true,
        position: latlng,
        content: this.getCustomOverlayContent(),
        xAnchor: 0.5,
        yAnchor: 2,
      });
      this.infowindow.setMap(this.map);

      this.$store.dispatch(
        `${mapStore}/update_selected_position`,
        this.marker.getPosition()
      );

      console.log(
        `클릭한 지점의\n위도 : ${latlng.getLat()}\n경도 : ${latlng.getLng()}`
      );
    },
    getTimeHTML(distance, searchBtn, centerPosition) {
      // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
      let walkTime = (distance / 67) | 0;
      let walkHour = document.createElement("span");
      walkHour.classList.add("number");
      let walkMin = document.createElement("span");
      walkMin.classList.add("number");

      // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
      if (walkTime > 60) {
        walkHour.innerText = Math.floor(walkTime / 60);
      }
      walkMin.innerText = walkTime % 60;

      // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
      let bycicleTime = (distance / 227) | 0;
      let bycicleHour = document.createElement("span");
      bycicleHour.classList.add("number");
      let bycicleMin = document.createElement("span");
      bycicleMin.classList.add("number");

      // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
      if (bycicleTime > 60) {
        bycicleHour.innerText = Math.floor(bycicleTime / 60);
      }
      bycicleMin.innerText = bycicleTime % 60;

      // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
      let content = document.createElement("ul");
      content.classList.add("dotOverlay", "distanceInfo");

      let distanceLi = document.createElement("li");
      let walkLi = document.createElement("li");
      let bycicleLi = document.createElement("li");

      let distanceLabel = document.createElement("span");
      distanceLabel.classList.add("label");
      distanceLabel.innerText = "총거리";

      let distanceValue = document.createElement("span");
      distanceValue.classList.add("number");
      distanceValue.innerText = distance;

      let walkLabel = document.createElement("span");
      walkLabel.classList.add("label");
      walkLabel.innerText = "도보";

      let bycicleLabel = document.createElement("span");
      bycicleLabel.classList.add("label");
      bycicleLabel.innerText = "자전거";

      distanceLi.appendChild(distanceLabel);
      distanceLi.appendChild(distanceValue);
      distanceLi.append("m");

      walkLi.appendChild(walkLabel);
      if (walkTime > 60) {
        walkLi.appendChild(walkHour);
        walkLi.append("시간 ");
      }
      walkLi.appendChild(walkMin);
      walkLi.append("분");

      bycicleLi.appendChild(bycicleLabel);
      if (bycicleTime > 60) {
        bycicleLi.appendChild(bycicleHour);
        bycicleLi.append("시간 ");
      }
      bycicleLi.appendChild(bycicleMin);
      bycicleLi.append("분");

      content.appendChild(distanceLi);
      content.appendChild(walkLi);
      content.appendChild(bycicleLi);

      if (searchBtn) {
        let btnLi = document.createElement("li");
        let btn = document.createElement("button");
        btn.id = "radius-find-btn";
        btn.onclick = () => {
          this.$store.dispatch(`${searchStore}/search_by_radius`, {
            lat: centerPosition.getLat(),
            lng: centerPosition.getLng(),
            radius: distance,
          });
        };
        btn.innerText = "주변 검색";

        btnLi.appendChild(btn);
        content.appendChild(btnLi);
      }
      return content;
    },
    startDrawingCircle(e) {
      this.drawingFlag = true;

      if (this.rangeCircle) {
        this.rangeCircle.circle.setMap(null);
        this.rangeCircle.polyline.setMap(null);
        this.rangeCircle.overlay.setMap(null);
        this.rangeCircle = null;
        this.$store.dispatch(`${mapStore}/update_range_circle`, 0);
      }

      this.centerPosition = e.latLng;

      if (!this.drawingLine) {
        this.drawingLine = new kakao.maps.Polyline({
          strokeWeight: 3,
          strokeColor: "#00a0e9",
          strokeOpacity: 1,
          strokeStyle: "solid",
        });
      }

      if (!this.drawingCircle) {
        this.drawingCircle = new kakao.maps.Circle({
          strokeWeight: 1,
          strokeColor: "#00a0e9",
          strokeOpacity: 0.1,
          strokeStyle: "solid",
          fillColor: "#00a0e9",
          fillOpacity: 0.2,
        });
      }

      if (!this.drawingOverlay) {
        this.drawingOverlay = new kakao.maps.CustomOverlay({
          xAnchor: 0,
          yAnchor: 0,
          zIndex: 1,
        });
      }
    },
    updateDrawingCircle(e) {
      if (this.drawingFlag) {
        let mousePosition = e.latLng;

        let linePath = [this.centerPosition, mousePosition];

        this.drawingLine.setPath(linePath);

        let length = this.drawingLine.getLength();

        if (length > 0) {
          let circleOptions = {
            center: this.centerPosition,
            radius: length,
          };

          this.drawingCircle.setOptions(circleOptions);

          let radius = Math.round(this.drawingCircle.getRadius());
          let content = `
              <div class="drawing-circle-info">
                반경 <span class="drawing-cicle-number">${radius}</span>m
              </div>
            `;

          this.drawingOverlay.setPosition(mousePosition);
          this.drawingOverlay.setContent(content);
          this.drawingCircle.setMap(this.map);
          this.drawingLine.setMap(this.map);
          this.drawingOverlay.setMap(this.map);
        } else {
          this.drawingCircle.setMap(null);
          this.drawingLine.setMap(null);
          this.drawingOverlay.setMap(null);
        }
      }
    },
    endDrawingCircle(e) {
      let clickPosition = e.latLng;

      let polyline = new kakao.maps.Polyline({
        path: [this.centerPosition, clickPosition],
        strokeWeight: 3,
        strokeColor: "#00a0e9",
        strokeOpacity: 1,
        strokeStyle: "solid",
      });

      let circle = new kakao.maps.Circle({
        center: this.centerPosition,
        radius: polyline.getLength(),
        strokeWeight: 1,
        strokeColor: "#00a0e9",
        strokeOpacity: 0.1,
        strokeStyle: "solid",
        fillColor: "#00a0e9",
        fillOpacity: 0.2,
      });

      let radius = Math.round(circle.getRadius());
      let content = this.getTimeHTML(radius, true, this.centerPosition);

      let radiusOverlay = new kakao.maps.CustomOverlay({
        content: content,
        position: clickPosition,
        xAnchor: 0,
        yAnchor: 0,
        zIndex: 1,
        clickable: true,
      });

      circle.setMap(this.map);
      polyline.setMap(this.map);
      radiusOverlay.setMap(this.map);

      let range_circle = {
        polyline: polyline,
        circle: circle,
        overlay: radiusOverlay,
      };

      this.rangeCircle = range_circle;
      this.$store.dispatch(`${mapStore}/update_range_circle`, radius);

      this.drawingCircle.setMap(null);
      this.drawingLine.setMap(null);
      this.drawingOverlay.setMap(null);

      this.drawingFlag = false;
      this.drawingCircle = null;
      this.drawingLine = null;
      this.drawingOverlay = null;
      this.drawingDot = null;
      this.centerPosition = null;
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.511332, 126.986381),
        level: 6,
      };

      this.map = new kakao.maps.Map(container, options);
      this.initMapEvents(this.map);
      this.set_map(this.map);
    },
    initMapEvents(map) {
      kakao.maps.event.addListener(map, "click", (e) => {
        if (this.draw_range_circle) {
          if (!this.drawingFlag) this.startDrawingCircle(e);
          else this.endDrawingCircle(e);
        } else {
          this.placeMarker(e);
        }
      });

      kakao.maps.event.addListener(map, "mousemove", (e) => {
        this.updateDrawingCircle(e);
      });
    },
    getCustomOverlayContent() {
      const overlay = document.createElement("div");
      overlay.id = "overlay";

      const button = document.createElement("button");
      button.id = "overlay-btn";
      button.innerText = "장소 기억하기";

      button.onclick = () => {
        if (!this.userInfo.uid) {
          this.openInfoModal(
            "로그인이 필요합니다",
            "이 장소를 기억하려면 로그인이 필요합니다"
          );
          return;
        }

        this.openAddModal();
      };
      overlay.appendChild(button);

      return overlay;
    },
    addUserLife() {
      for (let item of this.userlife) {
        if (item.locationName == this.userLifeName) {
          alert("이미 같은 이름을 가진 장소가 있습니다!");
          return;
        }
      }

      const userlife = {
        lid: 0,
        uid: this.userInfo.uid,
        locationName: this.userLifeName,
        lat: this.selected_position.getLat(),
        lng: this.selected_position.getLng(),
      };
      insertUserLife(
        userlife,
        ({ data }) => {
          if (data == 1) {
            this.$store.dispatch(`${memberStore}/updateUserLife`, userlife);
            this.openInfoModal("장소 기억 성공!", "이 장소가 기억되었습니다");
            this.closeAddModal();
          }
        },
        (error) => {
          console.error(error);
        }
      );
    },
    updateLifeMarkers() {
      for (let marker of this.lifeMarkers) marker.setMap(null);
      for (let infowindow of this.lifeMarkerInfoWindows) infowindow.close();

      let imageSrc = require("@/assets/marker2.png");
      let imageSize = new kakao.maps.Size(64, 69);
      let imageOption = { offset: new kakao.maps.Point(32, 64) };

      let markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      this.lifeMarkerInfoWindows = [];
      this.lifeMarkers = [];
      for (let item of this.userlife) {
        // 마커의 위치 계산
        let markerPosition = new kakao.maps.LatLng(item.lat, item.lng);

        // 마커 생성
        let marker = new kakao.maps.Marker({
          position: markerPosition,
          image: markerImage,
          clickable: true,
        });

        // 인포 윈도우 생성
        let content = `
          <div id="life-info-window">
            ${item.locationName}
          </div>
        `;
        let infowindow = new kakao.maps.InfoWindow({
          position: markerPosition,
          content: content,
        });
        infowindow.open(this.map, marker);
        marker.setMap(this.map);
        this.lifeMarkers.push(marker);
        this.lifeMarkerInfoWindows.push(infowindow);
      }

      for (let marker of this.lifeMarkers)
        marker.setMap(this.show_life_marker ? this.map : null);

      for (let infowindow of this.lifeMarkerInfoWindows)
        infowindow.setMap(this.show_life_marker ? this.map : null);

      this.updateDistanceLines();
    },
    updateDistanceLines() {
      if (!this.selected_position) return;

      for (let line of this.distanceLines) line.setMap(null);
      for (let overlay of this.distanceOverlays) overlay.setMap(null);

      this.distanceLines = [];
      this.distanceOverlays = [];
      for (let item of this.userlife) {
        let markerPosition = new kakao.maps.LatLng(item.lat, item.lng);

        let distanceLine = new kakao.maps.Polyline({
          map: this.map,
          path: [
            markerPosition,
            new kakao.maps.LatLng(
              this.selected_position.getLat(),
              this.selected_position.getLng()
            ),
          ],
          strokeWeight: 2,
          strokeColor: "#4d55b2",
          strokeOpacity: 0.8,
          strokeStyle: "dashed",
        });

        let distanceOverlay = new kakao.maps.CustomOverlay({
          map: this.map,
          content: this.getTimeHTML(Math.round(distanceLine.getLength())),
          position: markerPosition,
          xAnchor: 0,
          yAnchor: 0,
          zIndex: 3,
        });

        this.distanceLines.push(distanceLine);
        this.distanceOverlays.push(distanceOverlay);
      }

      for (let line of this.distanceLines)
        line.setMap(this.show_life_distance_overlay ? this.map : null);

      for (let overlay of this.distanceOverlays)
        overlay.setMap(this.show_life_distance_overlay ? this.map : null);
    },
  },
};
</script>

<style scoped>
#map {
  position: relative;
  width: 100vw;
  height: 100vh;
}
</style>

<style>
#overlay {
  background-color: white;
  box-shadow: 2px 2px 2px rgb(177, 177, 177);
  border-radius: 4px;
}

#overlay-btn {
  margin: 0;
  padding: 0.5rem 1rem;

  border: none;
  border-radius: 4px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1),
    0 2px 4px -1px rgba(0, 0, 0, 0.06);

  cursor: pointer;

  transition: 0.5s;

  background-color: rgba(77, 85, 178, 1);
  color: white;
}

#overlay-btn:hover {
  background-color: rgb(122, 128, 194);
}

#user-life-form {
  margin-bottom: 20px;
}

#modal-btn {
  color: white;
}

#life-info-window {
  width: 150px;
  height: 24px;
  background-color: rgba(77, 85, 178, 1);
  color: white;
  text-align: center;
}

.dot {
  overflow: hidden;
  float: left;
  width: 12px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png");
}
.dotOverlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  font-size: 12px;
  padding: 5px;
  background: #fff;
  text-align: left;
  background-color: rgba(77, 85, 178, 1);
  color: white;
}
.dotOverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.number {
  font-weight: bold;
  color: white;
}
.dotOverlay:after {
  content: "";
  position: absolute;
  margin-left: -6px;
  left: 50%;
  bottom: -8px;
  width: 11px;
  height: 8px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png");
}
.distanceInfo {
  position: relative;
  top: 5px;
  left: 5px;
  list-style: none;
  margin: 0;
}
.distanceInfo .label {
  display: inline-block;
  width: 50px;
}
.distanceInfo:after {
  content: none;
}

.drawing-circle-info {
  position: relative;
  top: 5px;
  left: 5px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  font-size: 12px;
  padding: 5px;
  background: rgba(77, 85, 178, 1);
  list-style: none;
  margin: 0;
  color: white;
}
.drawing-circle-info:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.drawing-circle-info .label {
  display: inline-block;
  width: 50px;
}
.drawing-cicle-number {
  font-weight: bold;
  color: white;
}

#radius-find-btn {
  margin: 0;
  padding: 0.2rem 0.5rem;

  border-radius: 4px;
  border-color: white;

  font-weight: bold;
  color: rgba(77, 85, 178, 1);
  background-color: white;
}
</style>
