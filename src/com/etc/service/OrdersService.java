package com.etc.service;

import com.etc.entity.Orders;

public interface OrdersService {
	
	//插入用户提交订单
	boolean submitOrder(Orders orders);
	

}
