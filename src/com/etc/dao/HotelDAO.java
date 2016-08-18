package com.etc.dao;

import java.util.List;

import com.etc.entity.Hotel;

public interface HotelDAO {
	
	//根据省份和城市获取酒店列表
    List<Hotel> findHotelList(String province,String city,String keyword);

    //根据酒店列表得到该所有酒店的综合评分
    float[] getCom_level(List<Hotel> hotelList);
    
    //添加订单后增加酒店的入住人数
    boolean updateVisitor_num(int hotelID,int num);
}
