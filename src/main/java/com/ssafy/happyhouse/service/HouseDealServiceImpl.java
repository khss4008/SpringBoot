package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	HouseDealMapper houseDealMapper;
	
	public HouseDealServiceImpl(HouseDealMapper houseDealMapper) {
		this.houseDealMapper = houseDealMapper;
	}

	@Override
	public HouseDeal getHouseDeal(int no) {
		HouseDeal houseDeal = null;
		try {
			houseDeal = houseDealMapper.select(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return houseDeal;
	}

	@Override
	public List<HouseDeal> getHouseDealAll() {
		List<HouseDeal> list = null;
		try {
			list = houseDealMapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
