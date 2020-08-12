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
import service.ProductService;


public class ProductFullController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductFullController() {
		super();
	}

	private static final String PRODUCT_FULL_FORM = "WEB-INF/views/productFullView.jsp";


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(PRODUCT_FULL_FORM);
		ProductService ps = new ProductService();

		String productId = request.getParameter("productId");
		Product product = ps.getProductById(Integer.valueOf(productId));

		request.setAttribute("product", product);
		rd.forward(request, response);
	}

}
