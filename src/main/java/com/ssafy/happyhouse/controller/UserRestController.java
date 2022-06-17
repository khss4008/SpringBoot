package com.ssafy.happyhouse.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/userinfo")
public class UserRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	private static final String LOGIN_SUCCESS = "success";
	private static final String LOGIN_FAIL = "fail";
	
	private UserService service;
	private JwtService jwtService;
	
	public UserRestController(UserService service, JwtService jwtService) {
		this.service = service;
		this.jwtService = jwtService;
	}
	@ApiOperation(value="사용자 정보 전체 가져오기", notes = "전체 정보를 가져옵니다.")
	@GetMapping("/list")
	public List<User> selectAll() throws Exception{
		
		return service.selectAllUser();
	}
	@ApiOperation(value="사용자 하나 가져오기", notes = "id값을 통해 사용자 하나의 정보를 가져옵니다")
	@GetMapping("/select/{uid}")
	public User select(@PathVariable String uid) throws Exception{
		
		return service.selectUser(uid);
	}
	
	@ApiOperation(value="사용자 정보 삭제하기", notes = "사용자 정보 하나를 삭제합니다.")
	@DeleteMapping("/delete/{uid}")
	public List<User> delete(@PathVariable String uid) throws Exception{
		service.deleteUser(uid);
		return service.selectAllUser();
	}  
	
	@ApiOperation(value="사용자 정보 하나 변경하기", notes = "사용자 정보 하나를 변경합니다.")
	@PutMapping("/update")
	public List<User> update(@RequestBody HashMap<String, String> map) throws Exception{
		User user = new User();
		user.setUserId(map.get("userId"));
		user.setUserPwd(map.get("userPwd"));
		user.setUserName(map.get("userName"));
		user.setUserAddr(map.get("userAddr"));
		user.setUserTel(map.get("userTel"));
		service.updateUser(user);
		return service.selectAllUser();
	}
	
	@ApiOperation(value="사용자 등록하기", notes = "사용자 정보를 등록합니다.")
	@PostMapping("/insert")
	public List<User> insert(@RequestBody HashMap<String, String> map) throws Exception{
		User user = new User();
		user.setUserId(map.get("userId"));
		user.setUserPwd(map.get("userPwd"));
		user.setUserName(map.get("userName"));
		user.setUserAddr(map.get("userAddr"));
		user.setUserTel(map.get("userTel"));
		//key, data, subject
		String token = jwtService.create("password", user.getUserPwd(), "password");
		user.setUserPwd(token);
		service.insertUser(user);
		return service.selectAllUser();
	}
	
	@ApiOperation(value="로그인 하기", notes = "로그인을 합니다.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody HashMap<String, String> map) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		System.out.println("map => " + map);	//들어온 map 값 id, pw
		
		List<User> ul = service.selectAllUser();
//		System.out.println(ul);	//모든 유저 값
		
		String success="";
		
		for(int i=0; i<ul.size();i++) {
			User user = ul.get(i);	//인코딩된 pw 토큰을 가져옴
//			System.out.println(user.getUserPwd());
			if(user.getUserPwd().length()>100) {	//기존의 값들 말고 pwd 토큰들
				String payload = user.getUserPwd().split("\\.")[1];
//				String data = new String(org.apache.commons.codec.binary.Base64.decodeBase64(payload),"UTF-8");
				String data = new String (org.apache.tomcat.util.codec.binary.Base64.decodeBase64(payload),"UTF-8");
//				System.out.println(data);
				String[] arr = data.split(",");
				String[] arrDetail = arr[1].split(":");
//				System.out.println(arrDetail[1]);
				String result = arrDetail[1].replace("\"", "");
//				System.out.println(result);
				if(map.get("userPwd").equals(result)) {
					success=user.getUserPwd();
				}
			}
		}
		System.out.println("성공한 값 ==> "+success);
		try {
			User loginUser = service.login(map.get("userId"), success);
			
			if (loginUser != null) {
				String token = jwtService.create("uid", loginUser.getUid(), "access-token");
				logger.debug("로그인 토큰 정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", LOGIN_SUCCESS);
				status = HttpStatus.ACCEPTED; 
			} else {
				resultMap.put("message", LOGIN_FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{uid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("uid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String uid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = service.selectUser(uid);
				resultMap.put("userInfo", user);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "fail");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="로그아웃 하기", notes = "로그아웃을 합니다.")
	@PostMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		
		return "로그아웃 되었습니다.";
	}
	
	
}
