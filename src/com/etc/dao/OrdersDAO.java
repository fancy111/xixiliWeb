package com.etc.dao;

import com.etc.entity.Orders;

public interface OrdersDAO {

	//插入订单数据
	boolean insertOrder(Orders orders);
}
