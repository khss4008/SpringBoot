package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;

public interface HouseInfoMapper {
	
	public HouseInfo selectByAptCode(int aptCode) throws SQLException;
//	public List<HouseInfo> selectAllByName(String aptName) throws SQLException;
//	public List<HouseInfo> selectAllByDongCode(String dongCode) throws SQLException;
//	public List<HouseInfo> selectAllByDongName(String dongName) throws SQLException;
}
