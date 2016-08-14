package com.etc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.etc.dao.HotelDAO;
import com.etc.dao.TypesDAO;
import com.etc.dao.impl.HotelDAOImpl;
import com.etc.dao.impl.TypesDAOImpl;
import com.etc.entity.Hotel;
import com.etc.entity.Types;
import com.etc.service.HotelService;

public class HotelServiceImpl implements HotelService{

	private HotelDAO hotelDAO = new HotelDAOImpl();
	private TypesDAO typesDao = new TypesDAOImpl();
	
	//根据省份和城市获取酒店列表,并且得到酒店的类型
	@Override
	public List<Hotel> getHotelList(String province, String city,String keyword) {
		List<Hotel> list = hotelDAO.findHotelList(province, city,keyword);
		float[] com_level = hotelDAO.getCom_level(list);
		List<Hotel> result = new ArrayList<Hotel>();
		
		for(int i = 0; i < list.size(); i++){
			Hotel hotel = list.get(i);
			List<Types> types = typesDao.findHotelTypeList(hotel.getHotelID());
			hotel.setHotelTypes(types);
			hotel.setLowestPrice(typesDao.findLowestPrice(types));
			hotel.setCom_level(com_level[i]);
			result.add(hotel);
		}
		return list;
	}

}
