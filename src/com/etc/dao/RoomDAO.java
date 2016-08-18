package com.etc.dao;

import java.util.List;

import com.etc.entity.Room;

public interface RoomDAO {

	//根据酒店id和房型得到所有的房间号
	List<Room> findRooms(int hotelID,String room_type);
}
