package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Notice;

@Mapper
public interface NoticeMapper {
	int insertNotice(Notice notice)throws SQLException;
	int updateNotice(Notice notice)throws SQLException;
	int deleteNotice(int nid)throws SQLException;
	Notice selectNotice(int nid)throws SQLException;
//	List<Board> selectAllBoard() throws SQLException;
} 
