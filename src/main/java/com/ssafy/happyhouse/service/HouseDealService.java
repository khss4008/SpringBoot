package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.util.Util.Coordinate;

public interface HouseDealService {
	
	public HouseDeal getHouseDeal(int no);
	public List<HouseDeal> getHouseDealAll();
}
