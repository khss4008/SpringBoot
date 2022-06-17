package com.ssafy.happyhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.service.HouseInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/houseinfo")
public class HouseInfoController {

	HouseInfoService houseInfoService;

	public HouseInfoController(HouseInfoService houseInfoService) {
		this.houseInfoService = houseInfoService;
	}

	@ApiOperation(value = "건물 정보 가져오기", notes="aptCode값을 통해 1개의 건물 정보를 가져옵니다")
	@GetMapping("/aptcode/{aptCode}")
	public ResponseEntity<?> getHouseInfo(@PathVariable int aptCode) {
		HouseInfo houseInfo = houseInfoService.getHouseInfoByAptCode(aptCode);

		if (houseInfo == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<HouseInfo>(houseInfo, HttpStatus.OK);

	}
}
