package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.happyhouse.model.dto.User;
@Mapper
public interface UserMapper {
	int insertUser(User user) throws SQLException;
	int updateUser(User user) throws SQLException;
	int deleteUser(String uid) throws SQLException;
	User selectUser(String uid) throws SQLException;
	List<User> selectAllUser() throws SQLException;
	User login(String userId, String userPwd) throws SQLException;
	
}
