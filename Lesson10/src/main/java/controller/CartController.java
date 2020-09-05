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
		String amountStr = req.getParameter("amount");// количество товаров одинаковых добавил пользователь
		String deleteId = req.getParameter("deleteId");
		String refreshItem = req.getParameter("refreshItem");// индикатор обновления товаров одной группы
		String directionFlag = req.getParameter("directionFlag");// индикатор отнятия/добавления товара

		HttpSession session = req.getSession();
		String redirect = "";
		Map<Product, Integer> products = new HashMap<>();
		int allQuantity = 0;
		PrintWriter out = resp.getWriter();

		if (session.getAttribute("cart") != null) {
			products = (Map<Product, Integer>) session.getAttribute("cart");
			allQuantity = (int) session.getAttribute("allQuantity");
		}

		if (refreshItem != null) {
			int amount = Integer.valueOf(amountStr);// одного товара
			System.out.println("In refresh: amountStr:" + amountStr);

			DaoFactory daoFactory = DaoFactory.getInstance(MYSQL);
			ProductDao productDao = daoFactory.getProductDao();
			Product tmpProd = productDao.getProductById(Integer.valueOf(productId));

			if (products.get(tmpProd) != null) {

				int prevItemAmt = products.get(tmpProd);

				if (directionFlag.equals("plus")) {
					allQuantity += 1;
					prevItemAmt += 1;
				} else if (directionFlag.equals("minus")) {
					allQuantity -= 1;
					prevItemAmt -= 1;
				}
				// апдейтим айтем
				products.put(tmpProd, prevItemAmt);
				session.setAttribute("allQuantity", allQuantity);
				session.setAttribute("cart", products);
				System.out.println("refresh: allQuantity:" + allQuantity);
			}

			out.write(String.valueOf(allQuantity));// -передаем данные $.ajax обратно, и потом в footer.jsp
		}

		if (productId != null && refreshItem == null) {
			int amount = Integer.valueOf(amountStr);// одного товара
			allQuantity += amount;
			DaoFactory daoFactory = DaoFactory.getInstance(MYSQL);
			ProductDao productDao = daoFactory.getProductDao();
			Product tmpProd = productDao.getProductById(Integer.valueOf(productId));
			if (products.get(tmpProd) != null) {
				amount = products.get(tmpProd) + amount;
			}
			products.put(tmpProd, amount);
			// System.out.println("Product: " + tmpProd.getName() + ": amount:" +
			// products.get(tmpProd));
			session.setAttribute("cart", products);
			session.setAttribute("allQuantity", allQuantity);
			out.write(String.valueOf(allQuantity));// передаем данные $.ajax обратно, и потом в footer.jsp
		}

		// Delete from cart list all items of one product(bucket button)
		if (deleteId != null) {
			Map<Product, Integer> prod = (Map<Product, Integer>) session.getAttribute("cart");
			DaoFactory daoFactory = DaoFactory.getInstance(MYSQL);
			ProductDao productDao = daoFactory.getProductDao();
			Product productDel = productDao.getProductById(Integer.valueOf(deleteId));
			int productDelQnt = prod.get(productDel);// находим количество элементов в удаляемом продукте

			prod.keySet().stream().filter(p -> p.getId() == Integer.valueOf(deleteId)).findFirst()
					.ifPresent(p -> prod.remove(p));// удаляем продукт из мапы

			// System.out.println("deleteId:" + Integer.valueOf(deleteId));
			// System.out.println(prod);
			session.setAttribute("cart", prod);
			allQuantity -= productDelQnt;// обновляем общее количество на странице
			session.setAttribute("allQuantity", allQuantity);
			redirect = "./cart";
			resp.sendRedirect(redirect);
		}

	}
}
