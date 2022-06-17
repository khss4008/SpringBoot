package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.dto.SelectAll;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.service.NoticeService;
import com.ssafy.happyhouse.service.SelectAllService;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/notice")
public class NoticeRestController {
	
	private NoticeService service;
	private SelectAllService selectService;

	public NoticeRestController(NoticeService service, SelectAllService selectService) {
		super();
		this.service = service;
		this.selectService = selectService;
	}
	
	@ApiOperation(value="공지사항 정보 전체 가져오기", notes = "전체 정보를 가져옵니다.")
	@GetMapping("/list")
	public List<SelectAll> selectAllNotice() throws Exception{
		
		return selectService.selectAllNotice();
	}
	
	@ApiOperation(value="글 하나 가져오기", notes = "qnano값을 통해 글 하나의 정보를 가져옵니다")
	@GetMapping("/select/{nid}")
	public Notice select(@PathVariable int nid) throws Exception{
		
		return service.selectNotice(nid);
	}
	
	@ApiOperation(value="글 삭제하기", notes = "글 하나를 삭제합니다.")
	@DeleteMapping("/delete/{nid}")
	public List<SelectAll> delete(@PathVariable int nid) throws Exception{
		service.deleteNotice(nid);
		return selectService.selectAllNotice();
	}  
	
	@ApiOperation(value="글 정보 하나 변경하기", notes = "글 정보 하나를 변경합니다.")
	@PutMapping("/update")
	public List<SelectAll> update(Notice notice) throws Exception{
		service.updateNotice(notice); 
		return selectService.selectAllNotice();
	}
	
	@ApiOperation(value="글 등록하기", notes = "글 정보를 등록합니다.")
	@PostMapping("/insert")
	public List<SelectAll> insert(Notice notice) throws Exception{
		System.out.println(notice);
		service.insertNotice(notice);
		return selectService.selectAllNotice();
	}
	
}
