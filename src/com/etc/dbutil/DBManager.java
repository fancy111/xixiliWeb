package com.etc.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

	private Connection conn;
	private PreparedStatement pstmt;
	
	//构造函数
	public DBManager()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//连接数据库
	private void openConnection()
	{
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelorder", "root", "111111");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ִ执行更新，删除，添加操作
	public int execUpdate(String sql,Object... params)
	{
		this.openConnection();
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			for(int i = 0; i < params.length; i++)
			{
				this.pstmt.setObject(i+1, params[i]);
			}
			return this.pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		finally{
				this.closeConnection();
		}
	}
	
		//ִ执行查询操作
		public ResultSet execQuery(String sql,Object... params)
		{
			this.openConnection();
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				for(int i = 0; i < params.length; i++)
				{
					this.pstmt.setObject(i+1, params[i]);
				}
				return this.pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	
	//关闭连接
	public void closeConnection()
	{
		
		try {
				if (this.pstmt != null)
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		try {
				if (this.conn != null)
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
