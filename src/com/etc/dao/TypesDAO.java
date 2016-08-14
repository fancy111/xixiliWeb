package com.etc.dao;

import java.util.List;

import com.etc.entity.Types;

public interface TypesDAO {
	
	//根据hotelID找到相应的房间类型
	List<Types> findHotelTypeList(int hotelID);
	
	//得到这些类型中的最低价格
	float findLowestPrice(List<Types> hoteltypes);
}
