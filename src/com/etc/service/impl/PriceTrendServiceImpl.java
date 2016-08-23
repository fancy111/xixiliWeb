package com.etc.service.impl;

import com.etc.dao.PriceTrendDAO;
import com.etc.dao.impl.PriceTrendDAOImpl;
import com.etc.service.PriceTrendService;

public class PriceTrendServiceImpl implements PriceTrendService {

	PriceTrendDAO pricetrendDao = new PriceTrendDAOImpl();
	//获得该城市的价格趋势
	@Override
	public float[] getPrices(String city) {
		return pricetrendDao.getPriceTrend(city);
	}

	
}
