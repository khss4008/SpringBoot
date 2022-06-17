package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.SelectAll;

public interface SelectAllService {
	List<SelectAll> selectAllNotice() throws SQLException;
}
