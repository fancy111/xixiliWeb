package com.etc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.dao.HotelDAO;
import com.etc.dbutil.DBManager;
import com.etc.entity.Hotel;

public class HotelDAOImpl implements HotelDAO {

	DBManager dbManager = new DBManager();
	
	//根据省份和城市获取酒店列表
	@Override
	public List<Hotel> findHotelList(String province,String city,String keyword) {
		String sql = "select * from hotel where province = ? and city = ? and realname like ?";
		ResultSet rs = dbManager.execQuery(sql,province,city,"%"+keyword+"%");
		if(rs==null)
			return null;
		List<Hotel> list = new ArrayList<Hotel>();
		try {
		while(rs.next())
		{
			//向列表中添加hotel
			Hotel hotel = new Hotel();
			hotel.setHotelID(rs.getInt(1));
			hotel.setHotelname(rs.getString(2));
			hotel.setRealname(rs.getString(3));
			hotel.setPassword(rs.getString(4));
			hotel.setBasic_info(rs.getString(5));
			hotel.setSafe_level(rs.getFloat(6));
			hotel.setComment_level(rs.getFloat(7));
			hotel.setType(rs.getString(8));
			hotel.setVisitor_number(rs.getInt(9));
			hotel.setProvince(rs.getString(10));
			hotel.setCity(rs.getString(11));
			hotel.setLocation(rs.getString(12));
			hotel.setX(rs.getInt(13));
			hotel.setY(rs.getInt(14));
			hotel.setPhoto(rs.getString(15));
			hotel.setTel(rs.getString(16));
			list.add(hotel);
		}
		return list;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		finally{
			dbManager.closeConnection();}
		
	}

	//根据酒店列表得到该所有酒店的综合评分
	@Override
	public float[] getCom_level(List<Hotel> hotelList) {
		int[] visitors = new int[hotelList.size()];
		float[] com_level = new float[hotelList.size()];
		int min,max;
		
		if(hotelList.size() <= 1)
			return com_level;
		
		visitors[0] = hotelList.get(0).getVisitor_number();
		min = visitors[0];
		max = visitors[0];
		
		for(int i = 1; i < hotelList.size(); i++){
			visitors[i] = hotelList.get(i).getVisitor_number();
			if(min > visitors[i])
				min = visitors[i];
			if(max < visitors[i])
				max = visitors[i];
		}
		for(int i = 0; i < hotelList.size(); i++){
			float per_num = (float)(visitors[i] - min)/(float)(max - min)*5;
			com_level[i] = (float) (per_num*0.3+hotelList.get(i).getComment_level()*0.4+hotelList.get(i).getSafe_level()*0.3);
		}
		return com_level;
	}

	//添加订单后增加酒店的入住人数加1
	public boolean updateVisitor_num(int hotelID, int num) {
		String sql = "update hotel set visitor_number=visitor_number+? where hotelID=?";
		return dbManager.execUpdate(sql, num,hotelID)>0;
	}

}
