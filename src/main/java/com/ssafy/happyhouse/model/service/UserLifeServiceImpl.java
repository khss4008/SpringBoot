package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.UserLife;
import com.ssafy.happyhouse.model.mapper.UserLifeMapper;
@Service
public class UserLifeServiceImpl implements UserLifeService{
	
	@Autowired
	UserLifeMapper life;
	
	public UserLifeServiceImpl(UserLifeMapper life) {
		super();
		this.life = life;
	}

	@Override
	public int insertUserLife(UserLife userlife) throws SQLException {
		return life.insertUserLife(userlife);
	}

	@Override
	public int updateUserLife(UserLife userlife) throws SQLException {
		return life.updateUserLife(userlife);
	}

	@Override
	public int deleteUserLife(int lid) throws SQLException {
		return life.deleteUserLife(lid);
	}

	@Override
	public List<UserLife> selectUserLife(int uid) throws SQLException {
		return life.selectUserLife(uid);
	}

}
