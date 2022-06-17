package com.ssafy.happyhouse.model.dto;

public class UserLife {
	private int lid;
	private int uid;
	private String locationName;
	private double lat;
	private double lng;
	
	public UserLife(int lid, int uid, String locationName, double lat, double lng) {
		super();
		this.lid = lid;
		this.uid = uid;
		this.locationName = locationName;
		this.lat = lat;
		this.lng = lng;
	}
	
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	@Override
	public String toString() {
		return "UserLife [lid=" + lid + ", uid=" + uid + ", locationName=" + locationName + ", lat=" + lat + ", lng="
				+ lng + "]";
	}
	
	
	
	
}
