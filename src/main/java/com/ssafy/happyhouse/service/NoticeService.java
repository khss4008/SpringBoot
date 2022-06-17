package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Notice;

public interface NoticeService {
	int insertNotice(Notice notice)throws SQLException;
	int updateNotice(Notice notice)throws SQLException;
	int deleteNotice(int nid)throws SQLException; 
	Notice selectNotice(int nid)throws SQLException;
//	List<Board> selectAllBoard() throws SQLException;
}
