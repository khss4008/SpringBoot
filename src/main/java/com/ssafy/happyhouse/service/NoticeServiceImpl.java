package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;
@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeMapper notice;
	

	public NoticeServiceImpl(NoticeMapper notice) {
		super();
		this.notice = notice;
	}

	@Override
	public int insertNotice(Notice notice) throws SQLException {
		return this.notice.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) throws SQLException {
		return this.notice.updateNotice(notice);
	}

	@Override
	public int deleteNotice(int nid) throws SQLException {
		return this.notice.deleteNotice(nid);
	}
 
	@Override
	public Notice selectNotice(int nid) throws SQLException {
		return this.notice.selectNotice(nid);
	}

//	@Override
//	public List<Board> selectAllBoard() throws SQLException {
//		return this.board.selectAllBoard();
//	}

}
