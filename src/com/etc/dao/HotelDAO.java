package com.etc.dao;

import java.util.List;

import com.etc.entity.Hotel;

public interface HotelDAO {
	
	//根据省份和城市获取酒店列表
    List<Hotel> findHotelList(String province,String city,String keyword);

    //根据酒店列表得到该所有酒店的综合评分
    float[] getCom_level(List<Hotel> hotelList);
}
