package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.mapper.UserMapper;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper user;
	
	public UserServiceImpl(UserMapper user) {
		super();
		this.user = user;
	}

	@Override
	public int insertUser(User user) throws SQLException {
		return this.user.insertUser(user);
	}

	@Override
	public int updateUser(User user) throws SQLException {
		return this.user.updateUser(user);
	}

	@Override
	public int deleteUser(String uid) throws SQLException {
		return this.user.deleteUser(uid);
	}

	@Override
	public User selectUser(String uid) throws SQLException {
		return this.user.selectUser(uid);
	}

	@Override
	public List<User> selectAllUser() throws SQLException {
		return this.user.selectAllUser();
	}

	@Override
	public User login(String userId, String userPwd) throws SQLException {
		return this.user.login(userId, userPwd);
	}

}
