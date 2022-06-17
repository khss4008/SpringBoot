package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.UserLife;

public interface UserLifeService {
	int insertUserLife(UserLife userlife)throws SQLException;
	int updateUserLife(UserLife userlife)throws SQLException;
	int deleteUserLife(int lid)throws SQLException;
	List<UserLife> selectUserLife(int uid)throws SQLException;
}
