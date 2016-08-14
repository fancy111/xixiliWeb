package com.etc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.entity.Hotel;
import com.etc.service.HotelService;
import com.etc.service.impl.HotelServiceImpl;
import com.google.gson.Gson;

public class HotelListServlet extends HttpServlet {
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
		
		//获得请求
		System.out.println(">>>>>>getHotelList被请求！");
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String keyword = request.getParameter("keyword");
		
		System.out.println("pro"+province);
		System.out.println("city"+city);
		System.out.println("keyword"+keyword);
		
		HotelService hotelService = new HotelServiceImpl();
		List<Hotel> list = new ArrayList<Hotel>();
		//list = hotelService.getHotelList("pro1","1city1",5);
		list = hotelService.getHotelList(province,city,keyword);
		
		if(list == null)
			System.out.println("列表为空!");
		
		//创建Gson对象，并传给activity
		Gson gson = new Gson();
		String userJSON = gson.toJson(list);
		
		//输出到服务器上
		out.print(userJSON);
		System.out.println(userJSON);
		
		out.flush();
		out.close();
	}

}
