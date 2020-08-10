package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import service.ProductService;

public class CartController extends HttpServlet {

	private static final String CART_FORM = "WEB-INF/views/cartView.jsp";

	public CartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(CART_FORM);
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("prodId");
		HttpSession session = req.getSession();

		List<Product> products = new ArrayList<Product>();
		if (session.getAttribute("cart") != null) {
			products = (List<Product>) session.getAttribute("cart");
		}
		ProductService ps = new ProductService();
		products.add(ps.getProductById(Integer.valueOf(productId)));

		session.setAttribute("cart", products);
		resp.sendRedirect("./products");
	}
}
