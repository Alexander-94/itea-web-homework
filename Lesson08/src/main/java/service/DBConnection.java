package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String HOST = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "test";
	private static final String USER = "root";
	private static final String PASS = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";			

	public DBConnection() {
		try {
			Class.forName(DRIVER).newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(HOST + DB_NAME + "?" + "user=" + USER + "&" + "password=" + PASS);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return conn;
	}
}
