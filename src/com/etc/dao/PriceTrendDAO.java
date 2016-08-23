package com.etc.dao;

public interface PriceTrendDAO {

	//得到该城市的价格趋势
	float[] getPriceTrend(String city);
}
