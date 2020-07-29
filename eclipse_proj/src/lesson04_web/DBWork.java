package lesson04_web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBWork {
	private static final String HOST = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "test";
	private static final String USER = "root";
	private static final String PASS = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private final static String GET_USER = "SELECT name FROM USERS WHERE LOGIN = ? AND PASSWORD = ?";
	private final static String ADD_USER = "INSERT INTO USERS (login, password, name, gender, address, comment, agree) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private Connection conn;

	public DBWork() {

		try {
			Class.forName(DRIVER).newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(HOST + DB_NAME + "?" + "user=" + USER + "&" + "password=" + PASS);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return conn;
	}

	public String getLogin(String login, String password) {
		String name = null;
		getConnection();

		try (PreparedStatement ps = conn.prepareStatement(GET_USER);) {
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {// потому что результат запроса - 1 строка
				name = rs.getString("name");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return name;
	}

	public void insertUser(String login, String password, String name, String gender, String address, String comment,
			int agree) {
		PreparedStatement st = null;
		getConnection();
		
		try {
			st = conn.prepareStatement(ADD_USER);
			st.setString(1, login);
			st.setString(2, password);
			st.setString(3, name);
			st.setString(4, gender);
			st.setString(5, address);
			st.setString(6, comment);
			st.setInt(7, agree);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
/*
	public static void main(String[] args) {
		DBWork dbWork = new DBWork();
		System.out.println(dbWork.getLogin("login", "password"));
		dbWork.insertUser("user2", "dsfdsf", "sdfdsf", "M", "sdfdf", "sdfdsf", 1);
	}*/

}
