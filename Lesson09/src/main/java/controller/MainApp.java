package controller;

import java.util.ArrayList;
import java.util.List;

import model.Product;
import service.ProductService;

public class MainApp {

	public static void main(String[] args) {
		ProductService ps = new ProductService();
		List<Product> lp = new ArrayList<Product>();
		lp.add(ps.getProductById(1));
		lp.add(ps.getProductById(2));

		//lp.removeIf(p -> p.getId() == 1);
		//System.out.println(lp);
		
		lp.stream().filter(p->p.getId()==1).findFirst().ifPresent(p->lp.remove(p));//.forEach(p -> System.out.println(p.getName()));
		System.out.println(lp);
	}
}
