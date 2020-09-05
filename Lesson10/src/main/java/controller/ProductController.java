package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.DaoFactory;
import service.ProductDao;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String PRODUCT_FORM = "WEB-INF/views/productView.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(PRODUCT_FORM);		
		DaoFactory daoFactory = DaoFactory.getInstance(Integer.valueOf(1));
		ProductDao productDao = daoFactory.getProductDao();
		
		String category = request.getParameter("catId");
		List<Product> lp = new ArrayList<Product>();
		if (category != null) {
			lp = productDao.getProductsByCategory(Integer.valueOf(category));
		}else {
			lp = productDao.getProducts();
		}

		request.setAttribute("products", lp);
		rd.forward(request, response);
	}

}
