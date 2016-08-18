package com.etc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.dao.RoomDAO;
import com.etc.dbutil.DBManager;
import com.etc.entity.Room;

public class RoomDAOImpl implements RoomDAO {

	DBManager dbManager = new DBManager();
	
	//根据酒店id和房型得到所有的房间号
	@Override
	public List<Room> findRooms(int hotelID, String room_type) {
		String sql = "select room_number,checked from room where hotelID=? and room_type=?";
		ResultSet rs = dbManager.execQuery(sql, hotelID,room_type);
		List<Room> roomList = new ArrayList<Room>();
		try {
			while(rs.next()){
				Room room = new Room();
				room.setRoom_number(rs.getString(1));
				room.setChecked(rs.getBoolean(2));
				roomList.add(room);
			}
			return roomList;
		} catch (SQLException e) {
			return  null;
		}finally{
			dbManager.closeConnection();
		}
	}

	//把已经被提交的房间设为checked
	@Override
	public boolean setRoomChecked(int hotelID, String room_number) {
		String sql = "update room set checked = true where hotelID=? and room_number=?";
		return dbManager.execUpdate(sql, hotelID,room_number)>0;
	}

}
