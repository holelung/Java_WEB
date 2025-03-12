package com.kh.swp.member.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	


	public static Connection getConnect() {
		final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		final String USERNAME = "KH19_JJH";
		final String PASSWORD = "KH1234";
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void dqlClose(ResultSet rset, Statement stmt, Connection conn) {
		
		try {
			if(rset!=null) rset.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt!=null) stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
