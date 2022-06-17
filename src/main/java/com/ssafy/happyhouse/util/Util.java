package com.ssafy.happyhouse.util;

public class Util {
	public static class Coordinate {
		double lat, lng;

		public Coordinate(double lat, double lng) {
			this.lat = lat;
			this.lng = lng;
		}
	}
	
	public static final double R_EARTH = 6371; // 지구의 반지름
	public static final double DEG_TO_RAD = Math.PI / 180; // degree -> radian 변환
	
	/**
	 * 두 좌표의 거리를 구합니다
	 * @param c1 좌표 1
	 * @param c2 좌표 2
	 * @return 두 좌표상 구면 거리(haversine 사용)를 metre단위로 리턴
	 */
	public static double getDistance(Coordinate c1, Coordinate c2) {

		double lat1 = c1.lat * DEG_TO_RAD;
		double lat2 = c2.lat * DEG_TO_RAD;
		double d_lat = Math.abs(c1.lat - c2.lat) * DEG_TO_RAD;
		double d_lng = Math.abs(c1.lng - c2.lng) * DEG_TO_RAD;
		
		double a = (2 * Math.sin(d_lat / 2)) + (Math.cos(lat1) * Math.cos(lat2) * 2 * Math.sin(d_lng / 2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		return R_EARTH * c;
	}
}
