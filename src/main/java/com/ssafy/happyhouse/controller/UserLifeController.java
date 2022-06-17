package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.dto.UserLife;
import com.ssafy.happyhouse.model.service.UserLifeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/userlife")
public class UserLifeController {
	
	UserLifeService lifeService;

	public UserLifeController(UserLifeService lifeService) {
		super();
		this.lifeService = lifeService;
	}
	
	@ApiOperation(value="userlife 정보 가져오기", notes = "정보를 가져옵니다.")
	@GetMapping("/list/{uid}")
	public List<UserLife> selectLife(@PathVariable int uid) throws Exception{
		
		return lifeService.selectUserLife(uid);
	}
	
	@ApiOperation(value="userlife 정보 삭제하기", notes = "userlife 정보를 삭제합니다.")
	@DeleteMapping("/delete/{lid}")
	public int delete(@PathVariable int lid) throws Exception{
		return lifeService.deleteUserLife(lid);
	}  
	
	@ApiOperation(value="userlife 정보 변경하기", notes = "userlife 정보를 변경합니다.")
	@PutMapping("/update")
	public int update(@RequestBody UserLife userlife) throws Exception{
		return lifeService.updateUserLife(userlife);
	}
	
	@ApiOperation(value="userlife 등록하기", notes = "userlife 정보를 등록합니다.")
	@PostMapping("/insert")
	public int insert(@RequestBody UserLife userlife) throws Exception{
		return lifeService.insertUserLife(userlife);
	}
	
}
