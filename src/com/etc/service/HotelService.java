package com.etc.service;

import java.util.List;

import com.etc.entity.Hotel;

public interface HotelService {
	
	//根据省份和城市获取酒店列表
	List<Hotel> getHotelList(String province, String city,String keyword);
}
