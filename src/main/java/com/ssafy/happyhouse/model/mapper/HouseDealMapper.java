package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;

public interface HouseDealMapper {
	public HouseDeal select(int no);
	public List<HouseDeal> selectAll();
}
