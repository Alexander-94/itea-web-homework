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
	private static final String PRODUCT_FORM = "WEB-INF/views/productView.jsp";

	public CartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(CART_FORM);
		rd.forward(request, response);
	}

	/*
	 * @Override protected void doDelete(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { // Delete from cart list String
	 * deleteId = req.getParameter("deleteId"); HttpSession session =
	 * req.getSession();
	 * 
	 * List<Product> prod = (List<Product>) session.getAttribute("cart");
	 * prod.stream().filter(p -> p.getId() ==
	 * Integer.valueOf(deleteId)).findFirst().ifPresent(p -> prod.remove(p));
	 * System.out.println(prod); session.setAttribute("cart", prod);
	 * resp.sendRedirect("./cart"); }
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String productId = req.getParameter("prodId");
		String deleteId = req.getParameter("deleteId");
		HttpSession session = req.getSession();
		String redirect = "";

		List<Product> products = new ArrayList<Product>();
		if (session.getAttribute("cart") != null) {
			products = (List<Product>) session.getAttribute("cart");
		}

		if (productId != null) {
			ProductService ps = new ProductService();
			products.add(ps.getProductById(Integer.valueOf(productId)));
			session.setAttribute("cart", products);
			redirect = "./products";
		}

		// Delete from cart list
		if (deleteId != null) {
			List<Product> prod = (List<Product>) session.getAttribute("cart");
			prod.stream().filter(p -> p.getId() == Integer.valueOf(deleteId)).findFirst()
			.ifPresent(p -> prod.remove(p));			
			System.out.println("deleteId:" + Integer.valueOf(deleteId));
			System.out.println(prod);
			session.setAttribute("cart", prod);
			redirect = "./cart";
		}
		resp.sendRedirect(redirect);
	}
}
