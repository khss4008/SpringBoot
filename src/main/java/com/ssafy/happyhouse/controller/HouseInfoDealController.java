package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfoDeal;
import com.ssafy.happyhouse.service.HouseInfoDealService;
import com.ssafy.happyhouse.util.Util;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/infodeal")
public class HouseInfoDealController {

	HouseInfoDealService service;
	
	public HouseInfoDealController(HouseInfoDealService houseDealService) {
		this.service = houseDealService;
	}

	@ApiOperation(value = "매매 정보 가져오기", notes="no값을 통해 해당하는 매매 정보를 가져옵니다.")
	@GetMapping("/{no}")
	public ResponseEntity<?> getDeal(@PathVariable long no) {
		HouseInfoDeal houseInfoDeal = service.getHouseInfoDeal(no);
		if (houseInfoDeal == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<HouseInfoDeal>(houseInfoDeal, HttpStatus.OK);
	}

	@ApiOperation(value = "모든 매매 정보 가져오기", notes="모든 매매 정보를 가져옵니다.")
	@GetMapping("/")
	public ResponseEntity<?> getDeals() {
		List<HouseInfoDeal> list = service.getHouseInfoDealAll();
		if (list == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseInfoDeal>>(list, HttpStatus.OK);
	}
	@ApiOperation(value = "모든 매매 정렬된 값 가져오기", notes="모든 매매 정보의 정렬된 값을 가져옵니다.")
	@GetMapping("/sortDeal")
	public ResponseEntity<?> getSortDeals() {
		List<HouseInfoDeal> list = service.getHouseInfoDealsortName();
		if (list == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseInfoDeal>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "동별 매매 정보 가져오기", notes="동 코드에 해당하는 매매 정보를 가져옵니다.")
	@GetMapping("/dong/{dong}")
	public ResponseEntity<?> getDealsByDong(@PathVariable String dong) {
		List<HouseInfoDeal> list = service.getHouseInfoDealByDong(dong);
		if (list == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseInfoDeal>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트별 매매 정보 가져오기", notes="일치하는 이름을 가진 아파트들의 매매 정보를 가져옵니다.")
	@GetMapping("/apt/{name}")
	public ResponseEntity<?> getDealsByName(@PathVariable String name) {
		List<HouseInfoDeal> list = service.getHouseInfoDealByName(name);
		if (list == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseInfoDeal>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "거리별 매매 정보 가져오기", notes="특정 위치를 기준으로 가까운 순서대로 정렬된 매매 정보를 가져옵니다.")
	@GetMapping("/near")
	public ResponseEntity<?> getNearDeal(@RequestParam double lat, @RequestParam double lng) {
		List<HouseInfoDeal> list = service.getHouseInfoDealNearAll(new Util.Coordinate(lat, lng));
		if (list == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseInfoDeal>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "범위 내 매매 정보 가져오기", notes="특정 위치와 범위를 기준으로 범위 내의 매매 정보를 가져옵니다.")
	@GetMapping("/radius")
	public ResponseEntity<?> getDealsByRadius(@RequestParam HashMap<String, String> map) {
		List<HouseInfoDeal> list = service.getHouseInfoDealByRadius(map.get("lat"), map.get("lng"), map.get("radius"));
		System.out.println(map);
		if (list == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseInfoDeal>>(list, HttpStatus.OK);
	}
}
