package com.app.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDbConnection {
	
	private static Connection connection;
	
	private MySqlDbConnection() {
	
	}
	 
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					String url = "jdbc:mysql://localhost:3306/console_based_shopping";
					String username = "root";
					String password = "Ipsita02@1999"; //Give your DB username and password
					connection = DriverManager.getConnection(url, username, password);
					return connection;
					
	}

}
