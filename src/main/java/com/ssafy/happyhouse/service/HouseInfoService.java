package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.dto.HouseInfo;

public interface HouseInfoService {
	public HouseInfo getHouseInfoByAptCode(int aptCode);
}
