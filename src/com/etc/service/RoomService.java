package com.etc.service;

import java.util.List;

import com.etc.entity.Room;

public interface RoomService {

	//根据酒店id和房型得到所有的房间号
	List<Room> getRoomList(int hotelID,String room_type);
}
