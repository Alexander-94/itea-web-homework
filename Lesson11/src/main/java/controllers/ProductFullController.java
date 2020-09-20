package controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Product;
import service.ProductDataSource;

@Controller
@RequestMapping("/prodFull")
public class ProductFullController {

	@RequestMapping(method = RequestMethod.GET)
	public String getFullProduct(HttpServletRequest req) {
		ApplicationContext context = new ClassPathXmlApplicationContext("data-source-context.xml");
		ProductDataSource ps = (ProductDataSource) context.getBean("productDataSourceBean");

		String productId = req.getParameter("productId");
		Product product = ps.getProductById(Integer.valueOf(productId));

		req.setAttribute("product", product);
		return "productFullView";// переход на вью productFullView.jsp по гету
	}
}
