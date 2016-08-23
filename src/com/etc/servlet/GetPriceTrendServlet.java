package com.etc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.service.PriceTrendService;
import com.etc.service.impl.PriceTrendServiceImpl;
import com.google.gson.Gson;


public class GetPriceTrendServlet extends HttpServlet {
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
		
		System.out.println("获取城市价格走势");
		
		//获取城市变量
		String city = request.getParameter("city");
		//String city = "呼和浩特";
		System.out.println(city);
		
		PriceTrendService price = new PriceTrendServiceImpl();
		float[] priceTrend = price.getPrices(city);
		
		Gson gson = new Gson();
		String userJSON = gson.toJson(priceTrend);
		
		System.out.println(userJSON);
		out.println(userJSON);
		
	}

}
