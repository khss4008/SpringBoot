package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.User;

public interface UserService {
	int insertUser(User user) throws SQLException;
	int updateUser(User user) throws SQLException;
	int deleteUser(String uid) throws SQLException;
	User selectUser(String uid) throws SQLException;
	List<User> selectAllUser() throws SQLException;
	User login(String userId, String userPwd) throws SQLException;
}
