package com.etc.service.impl;

import java.util.List;

import com.etc.dao.RoomDAO;
import com.etc.dao.impl.RoomDAOImpl;
import com.etc.entity.Room;
import com.etc.service.RoomService;

public class RoomServiceImpl implements RoomService {

	RoomDAO roomDao = new RoomDAOImpl();
	//根据酒店id和房型得到所有的房间号
	@Override
	public List<Room> getRoomList(int hotelID, String room_type) {
		return roomDao.findRooms(hotelID, room_type);
	}

}
