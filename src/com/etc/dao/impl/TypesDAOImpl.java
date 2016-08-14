package com.etc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.etc.dao.TypesDAO;
import com.etc.dbutil.DBManager;
import com.etc.entity.Types;

public class TypesDAOImpl implements TypesDAO {
	
	DBManager dbManager = new DBManager();
	
	@Override
	public List<Types> findHotelTypeList(int hotelID) {
		String sql = "select * from types where hotelID=?";
		ResultSet rs = dbManager.execQuery(sql,hotelID);
		List<Types> list = new ArrayList<Types>();
		try {
		while(rs.next())
		{
			//向列表中添加Types
			Types type = new Types();
			type.setHotelID(rs.getInt(1));
			type.setRoom_type(rs.getString(2));
			type.setPhoto(rs.getString(3));
			type.setPrice(rs.getFloat(4));
			list.add(type);
		}
		return list;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		finally{
			dbManager.closeConnection();}
	}

	//得到这些类型中的最低价格
	@Override
	public float findLowestPrice(List<Types> hoteltypes) {
		
		Comparator<Types> compareComment = new Comparator<Types>() {
			@Override
			public int compare(Types arg0, Types arg1) {
				return (int) (arg0.getPrice()-arg1.getPrice());
			}
        };
        Collections.sort(hoteltypes,compareComment);
        return hoteltypes.get(0).getPrice();
	}

}
