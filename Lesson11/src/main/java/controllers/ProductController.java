package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Product;
import service.ProductDataSource;

@Controller
@RequestMapping("/products")
public class ProductController {

	// private static final String PRODUCT_FORM = "WEB-INF/views/productView.jsp";

	@RequestMapping(method = RequestMethod.GET)
	public String getProducts(HttpSession session, HttpServletRequest req) {
		ApplicationContext context = new ClassPathXmlApplicationContext("data-source-context.xml");
		ProductDataSource ps = (ProductDataSource) context.getBean("productDataSourceBean");

		String category = req.getParameter("catId");
		List<Product> lp = new ArrayList<Product>();
		if (category != null) {
			lp = ps.getProductsByCategory(Integer.valueOf(category));
		} else {
			lp = ps.getProducts();
		}

		req.setAttribute("products", lp);

		return "productView"; // переход на вью productView.jsp по гету
	}
}
