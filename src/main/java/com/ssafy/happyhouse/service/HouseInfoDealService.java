package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfoDeal;
import com.ssafy.happyhouse.util.Util.Coordinate;

public interface HouseInfoDealService {

	public HouseInfoDeal getHouseInfoDeal(long no);
	public List<HouseInfoDeal> getHouseInfoDealAll();
	public List<HouseInfoDeal> getHouseInfoDealByDong(String dongCode);
	public List<HouseInfoDeal> getHouseInfoDealByName(String aptName);
	public List<HouseInfoDeal> getHouseInfoDealsortName();
	public List<HouseInfoDeal> getHouseInfoDealByRadius(String lat, String lng, String radius);
	
	public List<HouseInfoDeal> getHouseInfoDealNearAll(Coordinate userCoordinate);
}
