package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.UserLife;

@Mapper
public interface UserLifeMapper {
	int insertUserLife(UserLife userlife)throws SQLException;
	int updateUserLife(UserLife userlife)throws SQLException;
	int deleteUserLife(int lid)throws SQLException;
	List<UserLife> selectUserLife(int uid)throws SQLException;
}
