package com.etc.dao.impl;

import com.etc.dao.OrdersDAO;
import com.etc.dbutil.DBManager;
import com.etc.entity.Orders;

public class OrdersDAOImpl implements OrdersDAO {

	DBManager dbManager = new DBManager();
	
	//插入用户提交订单
	@Override
	public boolean insertOrder(Orders orders) {
		String sql = "insert into orders VALUES(null,?,?,?,?,null,null,null,null,?,?,?,?,?,?)";
		if(orders.getCheckin_time() != null && orders.getResident() != null && orders.getRes_tel() != null)
		{
			if(dbManager.execUpdate(sql, orders.getUsers().getUserID(),
				orders.getHotel().getHotelID(),orders.getRoom_type(),orders.getRoom_number(),
				orders.getCheckin_time(),orders.getCheckout_time(),orders.getRealprice(),
				"未处理",orders.getResident(),orders.getRes_tel())>0)
				return true;
		}
		return false;
	}
}
