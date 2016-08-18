package com.etc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.entity.Room;
import com.etc.service.RoomService;
import com.etc.service.impl.RoomServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RoomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");
		
		//将客户端的内容转化为中文
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
	
		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();	
		
		System.out.println("查找房间列表");
		
		//接受参数
		//String room_type = request.getParameter("room_type");
		//int hotelID = Integer.parseInt(request.getParameter("hotelID"));
		String room_type = "标准双人房";
		int hotelID = 2;
		
		System.out.println("room_type"+room_type);
		System.out.println("hotelID"+hotelID);
		
		RoomService roomService = new RoomServiceImpl();
		List<Room> list = new ArrayList<Room>();
		
		list = roomService.getRoomList(hotelID, room_type);
		if(list == null)
		{
			System.out.println("列表为空!");
			out.print("列表为空");
		}
		else
		{
			//创建Gson对象，并传给activity
			GsonBuilder gsonBuilder = new GsonBuilder().serializeSpecialFloatingPointValues();
			Gson gson = gsonBuilder.create();
			String userJSON = gson.toJson(list);
			//输出到服务器上
			out.print(userJSON);
			System.out.println(userJSON);
		}
		out.flush();
		out.close();
		
	}

}
