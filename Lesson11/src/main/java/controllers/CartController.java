package controllers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Product;
import service.ProductDataSource;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private HttpSession session;

	@RequestMapping(method = RequestMethod.GET)
	public String getCart() {
		return "cartView"; // переход на вью cartView.jsp по гету
	}

	// product plus/minus from product view - to ajax
	@RequestMapping(value = "/productAdd", method = RequestMethod.POST)
	public @ResponseBody String addProduct(@RequestParam("prodId") String productId,
			@RequestParam("amount") String amountStr) {

		Map<Product, Integer> products = new HashMap<>();
		int allQuantity = 0;

		if (session.getAttribute("cart") != null) {
			products = (Map<Product, Integer>) session.getAttribute("cart");
			allQuantity = (int) session.getAttribute("allQuantity");
		}

		if (productId != null) {
			int amount = Integer.valueOf(amountStr);// одного товара
			allQuantity += amount;
			ApplicationContext context = new ClassPathXmlApplicationContext("data-source-context.xml");
			ProductDataSource ps = (ProductDataSource) context.getBean("productDataSourceBean");
			Product tmpProd = ps.getProductById(Integer.valueOf(productId));
			if (products.get(tmpProd) != null) {
				amount = products.get(tmpProd) + amount;
			}
			products.put(tmpProd, amount);
			session.setAttribute("cart", products);
			session.setAttribute("allQuantity", allQuantity);
			System.out.println("out write ajax 2");
			return String.valueOf(allQuantity);// передаем данные $.ajax обратно, и потом в footer.jsp
		}
		return "0";
	}

	// product plus/minus from cart view - to ajax
	@RequestMapping(value = "/productPlusMinus", method = RequestMethod.POST)
	public @ResponseBody String plusMinusProduct(@RequestParam("refreshItem") String refreshItem,
			@RequestParam("directionFlag") String directionFlag, @RequestParam("prodId") String productId,
			@RequestParam("amount") String amountStr) {

		Map<Product, Integer> products = new HashMap<>();
		int allQuantity = 0;

		if (session.getAttribute("cart") != null) {
			products = (Map<Product, Integer>) session.getAttribute("cart");
			allQuantity = (int) session.getAttribute("allQuantity");
		}

		if (refreshItem != null) {
			int amount = Integer.valueOf(amountStr);// одного товара
			System.out.println("In refresh: amountStr:" + amountStr);

			ApplicationContext context = new ClassPathXmlApplicationContext("data-source-context.xml");
			ProductDataSource ps = (ProductDataSource) context.getBean("productDataSourceBean");
			Product tmpProd = ps.getProductById(Integer.valueOf(productId));

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
			}
			return String.valueOf(allQuantity);// передаем данные $.ajax обратно, и потом в footer.jsp
		}
		return "0";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String updateCart(@RequestParam(value = "prodId", required = false) String productId,
			@RequestParam(value = "amount", required = false) String amountStr,
			@RequestParam(value = "deleteId", required = false) String deleteId) {

		Map<Product, Integer> products = new HashMap<>();
		int allQuantity = 0;

		if (session.getAttribute("cart") != null) {
			products = (Map<Product, Integer>) session.getAttribute("cart");
			allQuantity = (int) session.getAttribute("allQuantity");
		}

		// Delete from cart list all items of one product(bucket button)
		if (deleteId != null) {
			Map<Product, Integer> prod = (Map<Product, Integer>) session.getAttribute("cart");
			ApplicationContext context = new ClassPathXmlApplicationContext("data-source-context.xml");
			ProductDataSource ps = (ProductDataSource) context.getBean("productDataSourceBean");
			Product productDel = ps.getProductById(Integer.valueOf(deleteId));
			int productDelQnt = prod.get(productDel);// находим количество элементов в удаляемом продукте

			prod.keySet().stream().filter(p -> p.getId() == Integer.valueOf(deleteId)).findFirst()
					.ifPresent(p -> prod.remove(p));// удаляем продукт из мапы

			session.setAttribute("cart", prod);
			allQuantity -= productDelQnt;// обновляем общее количество на странице
			session.setAttribute("allQuantity", allQuantity);
			return "cartView";
		}

		if (productId != null) {
			int amount = Integer.valueOf(amountStr);// одного товара
			allQuantity += amount;
			ApplicationContext context = new ClassPathXmlApplicationContext("data-source-context.xml");
			ProductDataSource ps = (ProductDataSource) context.getBean("productDataSourceBean");
			Product tmpProd = ps.getProductById(Integer.valueOf(productId));
			if (products.get(tmpProd) != null) {
				amount = products.get(tmpProd) + amount;
			}
			products.put(tmpProd, amount);
			session.setAttribute("cart", products);
			session.setAttribute("allQuantity", allQuantity);
			System.out.println("out write ajax 2");
			return "cartView";
		}

		return "cartView";
	}

}
