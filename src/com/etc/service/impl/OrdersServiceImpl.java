package com.etc.service.impl;

import com.etc.dao.HotelDAO;
import com.etc.dao.OrdersDAO;
import com.etc.dao.RoomDAO;
import com.etc.dao.impl.HotelDAOImpl;
import com.etc.dao.impl.OrdersDAOImpl;
import com.etc.dao.impl.RoomDAOImpl;
import com.etc.entity.Orders;
import com.etc.service.OrdersService;

public class OrdersServiceImpl implements OrdersService {

	OrdersDAO ordersDao = new OrdersDAOImpl();
	HotelDAO hotelDao = new HotelDAOImpl();
	RoomDAO roomDao = new RoomDAOImpl();
	
	//插入用户提交订单,提交订单酒店人数加1
	@Override
	public boolean submitOrder(Orders orders) {
		if(hotelDao.updateVisitor_num(orders.getHotel().getHotelID(), 1)&&
				roomDao.setRoomChecked(orders.getHotel().getHotelID(), orders.getRoom_number()))
			return ordersDao.insertOrder(orders);
		return false;
	}

}
