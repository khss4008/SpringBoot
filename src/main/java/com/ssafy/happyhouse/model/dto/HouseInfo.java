package com.ssafy.happyhouse.model.dto;

public class HouseInfo {

	private long aptCode;
	private String aptName;
	private String dongCode;
	private String dong;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;

	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String roadNameSeq;
	private String roadNameBasementCode;
	private String roadNameCode;

	private String bonbun;
	private String bubun;

	private String sigunguCode;
	private String eubmyundongCode;
	private String landCode;
	private String apartmentName;

	public HouseInfo(long aptCode, String aptName, String dongCode, String dong, int buildYear, String jibun, String lat,
			String lng, String img, String roadName, String roadNameBonbun, String roadNameBubun, String roadNameSeq,
			String roadNameBasementCode, String roadNameCode, String bonbun, String bubun, String sigunguCode,
			String eubmyundongCode, String landCode, String apartmentName) {
		super();
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.dongCode = dongCode;
		this.dong = dong;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.img = img;
		this.roadName = roadName;
		this.roadNameBonbun = roadNameBonbun;
		this.roadNameBubun = roadNameBubun;
		this.roadNameSeq = roadNameSeq;
		this.roadNameBasementCode = roadNameBasementCode;
		this.roadNameCode = roadNameCode;
		this.bonbun = bonbun;
		this.bubun = bubun;
		this.sigunguCode = sigunguCode;
		this.eubmyundongCode = eubmyundongCode;
		this.landCode = landCode;
		this.apartmentName = apartmentName;
	}

	public long getAptCode() {
		return aptCode;
	}

	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}

	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}

	public String getRoadNameBubun() {
		return roadNameBubun;
	}

	public void setRoadNameBubun(String roadNameBubun) {
		this.roadNameBubun = roadNameBubun;
	}

	public String getRoadNameSeq() {
		return roadNameSeq;
	}

	public void setRoadNameSeq(String roadNameSeq) {
		this.roadNameSeq = roadNameSeq;
	}

	public String getRoadNameBasementCode() {
		return roadNameBasementCode;
	}

	public void setRoadNameBasementCode(String roadNameBasementCode) {
		this.roadNameBasementCode = roadNameBasementCode;
	}

	public String getRoadNameCode() {
		return roadNameCode;
	}

	public void setRoadNameCode(String roadNameCode) {
		this.roadNameCode = roadNameCode;
	}

	public String getBonbun() {
		return bonbun;
	}

	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}

	public String getBubun() {
		return bubun;
	}

	public void setBubun(String bubun) {
		this.bubun = bubun;
	}

	public String getSigunguCode() {
		return sigunguCode;
	}

	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}

	public String getEubmyundongCode() {
		return eubmyundongCode;
	}

	public void setEubmyundongCode(String eubmyundongCode) {
		this.eubmyundongCode = eubmyundongCode;
	}

	public String getLandCode() {
		return landCode;
	}

	public void setLandCode(String landCode) {
		this.landCode = landCode;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	@Override
	public String toString() {
		return "HouseInfo [aptCode=" + aptCode + ", aptName=" + aptName + ", dongCode=" + dongCode + ", dong=" + dong
				+ ", buildYear=" + buildYear + ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng + ", img=" + img
				+ ", roadName=" + roadName + ", roadNameBonbun=" + roadNameBonbun + ", roadNameBubun=" + roadNameBubun
				+ ", roadNameSeq=" + roadNameSeq + ", roadNameBasementCode=" + roadNameBasementCode + ", roadNameCode="
				+ roadNameCode + ", bonbun=" + bonbun + ", bubun=" + bubun + ", sigunguCode=" + sigunguCode
				+ ", eubmyundongCode=" + eubmyundongCode + ", landCode=" + landCode + ", apartmentName=" + apartmentName
				+ "]";
	}

}
