package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductService extends DBConnection {

	private final static String GET_PRODUCTS = "SELECT * FROM PRODUCT";

	public ProductService() {
		super();
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		Connection conn = getConnection();

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

}
