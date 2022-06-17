package com.ssafy.happyhouse.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.mapper.HouseInfoMapper;

@Service
public class HouseInfoServiceImpl implements HouseInfoService {

	HouseInfoMapper houseInfoMapper;
	
	public HouseInfoServiceImpl(HouseInfoMapper houseInfoMapper) {
		this.houseInfoMapper = houseInfoMapper;
	}

	@Override
	public HouseInfo getHouseInfoByAptCode(int aptCode) {
		HouseInfo houseInfo = null;
		
		try {
			houseInfo = houseInfoMapper.selectByAptCode(aptCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return houseInfo; 
	}

}
