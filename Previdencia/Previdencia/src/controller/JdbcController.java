package controller;

import java.sql.*;

public class JdbcController {
	
	Connection con;
	
	public void jdbcConnect() {
		
		//registra JDBC driver
		try {
			Driver d = (Driver)Class.forName("com.filemaker.jdbc.Driver").newInstance();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:filemaker://localhost:2399/Previdência","Admin", "admin");
			this.con = con;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public Connection getConnection() {
		return con;
	}
	
	
	
		
}
