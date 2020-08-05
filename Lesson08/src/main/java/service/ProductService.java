package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.User;

public class ProductService {
	private static final String HOST = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "test";
	private static final String USER = "root";
	private static final String PASS = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private final static String GET_PRODUCTS = "SELECT * FROM PRODUCT";
	private Connection conn;

	public ProductService() {
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

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		getConnection();

		try (Statement ps = conn.createStatement()) {

			ResultSet rs = ps.executeQuery(GET_PRODUCTS);
			while (rs.next()) {
				Product p = new Product().setId(rs.getInt("ID")).setName(rs.getString("NAME"))
						.setPrice(rs.getInt("PRICE")).setDescription(rs.getString("DESCRIPTION"))
						.setCategory(rs.getInt("CATEGORY"));

				products.add(p);
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

		return products;
	}

	/*public static void main(String[] args) {
		ProductService p = new ProductService();
		List<Product> lP = p.getProducts();
		
		System.out.println(lP);
		
	}*/
}
