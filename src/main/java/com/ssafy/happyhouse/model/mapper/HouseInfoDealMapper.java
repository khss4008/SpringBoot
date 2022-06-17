package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfoDeal;

public interface HouseInfoDealMapper {
	public HouseInfoDeal select(long no);
	public List<HouseInfoDeal> selectAll();
	public List<HouseInfoDeal> selectAllByDong(String dongCode);
	public List<HouseInfoDeal> selectAllByName(String aptName);
	public List<HouseInfoDeal> selectAllByRadius(String lat, String lng, String radius);
}
