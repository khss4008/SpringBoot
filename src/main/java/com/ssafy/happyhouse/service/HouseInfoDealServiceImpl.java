package com.ssafy.happyhouse.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfoDeal;
import com.ssafy.happyhouse.model.mapper.HouseInfoDealMapper;
import com.ssafy.happyhouse.util.Util;
import com.ssafy.happyhouse.util.Util.Coordinate;

@Service
public class HouseInfoDealServiceImpl implements HouseInfoDealService {

	HouseInfoDealMapper mapper;
	
	public HouseInfoDealServiceImpl(HouseInfoDealMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public HouseInfoDeal getHouseInfoDeal(long no) {
		HouseInfoDeal houseDeal = null;
		try {
			houseDeal = mapper.select(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return houseDeal;
	}

	@Override
	public List<HouseInfoDeal> getHouseInfoDealAll() {
		List<HouseInfoDeal> list = null;
		try {
			list = mapper.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<HouseInfoDeal> getHouseInfoDealByDong(String dongCode) {
		List<HouseInfoDeal> list = null;
		try {
			list = mapper.selectAllByDong(dongCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<HouseInfoDeal> getHouseInfoDealByName(String aptName) {
		List<HouseInfoDeal> list = null;
		try {
			list = mapper.selectAllByName(aptName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<HouseInfoDeal> getHouseInfoDealsortName() {
		List<HouseInfoDeal> list = null;
		try {
			list = mapper.selectAll();
			list.sort(new Comparator<HouseInfoDeal>() {
				@Override
				public int compare(HouseInfoDeal o1, HouseInfoDeal o2) {
					if(o1.getApartmentName().charAt(0)>o2.getApartmentName().charAt(0))
					return 1;
					else if(o1.getApartmentName().charAt(0)==o2.getApartmentName().charAt(0))
					return o1.getApartmentName().compareTo(o2.getApartmentName());
					else
					return -1;
				};
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	@Override
	public List<HouseInfoDeal> getHouseInfoDealNearAll(Coordinate userCoordinate) {
		List<HouseInfoDeal> list = null;
		try {
			list = mapper.selectAll();
			for (HouseInfoDeal hd : list) {
				hd.setDistance(Util.getDistance(userCoordinate, new Coordinate(Double.parseDouble(hd.getLat()), Double.parseDouble(hd.getLng()))));
			}
			
			Collections.sort(list, (HouseInfoDeal h1, HouseInfoDeal h2) -> {
				return h1.getDistance() - h2.getDistance() < 0 ? -1 : 1;
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<HouseInfoDeal> getHouseInfoDealByRadius(String lat, String lng, String radius) {
		List<HouseInfoDeal> list = null;
		try {
			list = mapper.selectAllByRadius(lat, lng, radius);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
