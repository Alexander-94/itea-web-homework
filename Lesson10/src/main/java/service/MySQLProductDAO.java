package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class MySQLProductDAO implements ProductDao {

	private final static String GET_PRODUCTS = "SELECT * FROM PRODUCT";
	private final static String GET_PRODUCTS_BY_CATEGORY = "SELECT * FROM PRODUCT WHERE CATEGORY=?";
	private final static String GET_PRODUCT_BY_ID = "SELECT * FROM PRODUCT WHERE ID=?";
	private MySQLDAOFactory ms;

	public MySQLProductDAO(MySQLDAOFactory ms) {
		this.ms = ms;
	}

	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		Connection conn = ms.getConnection();

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

	@Override
	public List<Product> getProductsByCategory(int categoryId) {
		List<Product> products = new ArrayList<Product>();
		Connection conn = ms.getConnection();

		try (PreparedStatement ps = conn.prepareStatement(GET_PRODUCTS_BY_CATEGORY)) {
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();

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

	@Override
	public Product getProductById(int id) {
		Product product = null;
		Connection conn = ms.getConnection();

		try (PreparedStatement ps = conn.prepareStatement(GET_PRODUCT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				product = new Product().setId(rs.getInt("ID")).setName(rs.getString("NAME"))
						.setPrice(rs.getInt("PRICE")).setDescription(rs.getString("DESCRIPTION"))
						.setCategory(rs.getInt("CATEGORY"));
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

		return product;
	}

}
