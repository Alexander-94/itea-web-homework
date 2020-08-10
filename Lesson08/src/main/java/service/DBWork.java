package service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class DBWork {
	private static final String HOST = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "test";
	private static final String USER = "root";
	private static final String PASS = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private final static String GET_USER = "SELECT name FROM USERS WHERE LOGIN = ? AND PASSWORD = ?";
	private final static String ADD_USER = "INSERT INTO USERS (login, password, name, gender, address, comment, agree) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private Connection conn;
	private static final String SALT = ":DS145sdcl^41";

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

	public User getLogin(String login, String password) {
		User user = null;
		getConnection();

		try (PreparedStatement ps = conn.prepareStatement(GET_USER);) {
			ps.setString(1, login);
			ps.setString(2, hashPassword(password));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
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

		return user;
	}

	public void insertUser(User user) {
		PreparedStatement st = null;
		getConnection();

		try {
			st = conn.prepareStatement(ADD_USER);
			st.setString(1, user.getLogin());
			st.setString(2, hashPassword(user.getPassword()));
			st.setString(3, user.getName());
			st.setString(4, user.getGender());
			st.setString(5, user.getAddress());
			st.setString(6, user.getComment());
			st.setInt(7, user.getAgree());
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

	private String hashPassword(String password) throws SQLException {
		String hashPass = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(StandardCharsets.UTF_8.encode(password + SALT));
			hashPass = String.format("%032x", new BigInteger(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			throw new SQLException();
		}
		return hashPass;
	}

}
