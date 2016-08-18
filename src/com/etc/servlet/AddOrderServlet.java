package com.etc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.entity.Hotel;
import com.etc.entity.Orders;
import com.etc.entity.Room;
import com.etc.entity.Types;
import com.etc.entity.Users;
import com.etc.service.OrdersService;
import com.etc.service.impl.OrdersServiceImpl;


public class AddOrderServlet extends HttpServlet {
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
		
		System.out.println("插入订单！");
		
		//接受参数
		String resident = request.getParameter("resident");
		String phone = request.getParameter("phone");
		String checkin_time = request.getParameter("checkin_time");
		String checkout_time = request.getParameter("checkout_time");
		int userID = Integer.parseInt(request.getParameter("userID"));
		int hotelID = Integer.parseInt(request.getParameter("hotelID"));
		String room_number = request.getParameter("room_number");
		float realprice = Float.parseFloat(request.getParameter("realprice"));
		String room_type = request.getParameter("room_type");
		
		
		System.out.println("res"+resident);
		System.out.println("phone"+phone);
		System.out.println("checkin_time"+checkin_time);
		System.out.println("checkout_time"+checkout_time);
		System.out.println("userID"+userID);
		System.out.println("hotelID"+hotelID);
		System.out.println("room_number"+room_number);
		System.out.println("realprice"+realprice);
		System.out.println("room_type"+room_type);
		
		//封装orders类
		Orders orders = new Orders();
		orders.setResident(resident);
        orders.setRes_tel(phone);
        orders.setCheckin_time(checkin_time);
        orders.setCheckout_time(checkout_time);
        orders.setRealprice(realprice);
        orders.setState("未处理");
        orders.setRoom_number(room_number);
        orders.setRoom_type(room_type);
        
        Hotel hotel = new Hotel();
        hotel.setHotelID(hotelID);
        orders.setHotel(hotel);
        
        Users users = new Users();
        users.setUserID(userID);
        orders.setUsers(users);
        
        
        //插入用户产生的订单
		OrdersService ordersService = new OrdersServiceImpl();
		if(ordersService.submitOrder(orders))
		{
			System.out.println("插入成功！");
			out.println("插入成功");
		}
		else
		{
			System.out.println("插入失败！");
			out.println("插入失败");
		}
	}

}
