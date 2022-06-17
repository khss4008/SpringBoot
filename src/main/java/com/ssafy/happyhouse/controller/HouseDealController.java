package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.service.HouseDealService;
import com.ssafy.happyhouse.util.Util;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/deal")
public class HouseDealController {

	HouseDealService houseDealService;
	
	public HouseDealController(HouseDealService houseDealService) {
		this.houseDealService = houseDealService;
	}

	@ApiOperation(value = "매매 정보 가져오기", notes="no값을 통해 해당하는 매매 정보를 가져옵니다.")
	@GetMapping("/{no}")
	public ResponseEntity<?> getDeal(@PathVariable int no) {
		HouseDeal houseDeal = houseDealService.getHouseDeal(no);
		if (houseDeal == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<HouseDeal>(houseDeal, HttpStatus.OK);
	}

	@ApiOperation(value = "모든 매매 정보 가져오기", notes="모든 매매 정보를 가져옵니다.")
	@GetMapping("/")
	public ResponseEntity<?> getDeals() {
		List<HouseDeal> list = houseDealService.getHouseDealAll();
		if (list == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<HouseDeal>>(list, HttpStatus.OK);
	}
}
