package com.etc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.etc.dao.PriceTrendDAO;
import com.etc.dbutil.DBManager;

public class PriceTrendDAOImpl implements PriceTrendDAO {
	
	DBManager dbManager = new DBManager();
	
	//得到该城市的价格趋势
	@Override
	public float[] getPriceTrend(String city) {
		float[] prices = new float[4];
		String sql = "select price from price_trend where city=?";
		ResultSet rs = dbManager.execQuery(sql, city);
		if(rs==null)
			return null;
		int i = 0;
		try {
			while(rs.next()){
				prices[i] = rs.getFloat(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prices;
	}

}
