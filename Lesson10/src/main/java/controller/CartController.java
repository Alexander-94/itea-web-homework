package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import service.DaoFactory;
import service.ProductDao;

public class CartController extends HttpServlet {

	private static final String CART_FORM = "WEB-INF/views/cartView.jsp";
	private static final String PRODUCT_FORM = "WEB-INF/views/productView.jsp";
	private static final int MYSQL = 1;

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
		String amountStr = req.getParameter("amount");//количество товаров одинаковых добавил пользователь
		String deleteId = req.getParameter("deleteId");
		HttpSession session = req.getSession();
		String redirect = "";
		Map<Product, Integer> products = new HashMap<>();
		int allQuantity = 0;
		PrintWriter out = resp.getWriter();

		if (session.getAttribute("cart") != null) {
			products = (Map<Product, Integer>) session.getAttribute("cart");
			allQuantity = (int) session.getAttribute("allQuantity");
		}

		if (productId != null) {
			int amount = Integer.valueOf(amountStr);//одного товара
			allQuantity += amount;
			DaoFactory daoFactory = DaoFactory.getInstance(MYSQL);
			ProductDao productDao = daoFactory.getProductDao();
			Product tmpProd = productDao.getProductById(Integer.valueOf(productId));
			if (products.get(tmpProd) != null) {
				amount = products.get(tmpProd) + amount;
			}
			products.put(tmpProd, amount);
			session.setAttribute("cart", products);
			session.setAttribute("allQuantity", allQuantity);
			//redirect = "./products";
			out.write(String.valueOf(allQuantity));//-передаем данные $.ajax обратно, и потом в footer.jsp
			System.out.println(String.valueOf(allQuantity));
		}

		// Delete from cart list
		if (deleteId != null) {
			Map<Product, Integer> prod = (Map<Product, Integer>) session.getAttribute("cart");
			prod.keySet().stream().filter(p -> p.getId() == Integer.valueOf(deleteId)).findFirst()
					.ifPresent(p -> prod.remove(p));
			System.out.println("deleteId:" + Integer.valueOf(deleteId));
			System.out.println(prod);
			session.setAttribute("cart", prod);
			redirect = "./cart";
			resp.sendRedirect(redirect);
		}

		
	}
}
