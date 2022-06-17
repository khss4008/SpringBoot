package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.SelectAll;
import com.ssafy.happyhouse.model.mapper.SelectAllMapper;
@Service
public class SelectAllServiceImpl implements SelectAllService {
	@Autowired
	SelectAllMapper select;
	
	public SelectAllServiceImpl(SelectAllMapper select) {
		super();
		this.select = select;
	}

	@Override
	public List<SelectAll> selectAllNotice() throws SQLException {
		return this.select.selectAllNotice();
	}

}
