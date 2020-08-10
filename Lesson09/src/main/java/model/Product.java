package model;

public class Product {

	private int id;
	private int price;
	private String description;
	private String name;
	private int category;

	public Product() {
		super();
	}

	public Product(int id, int price, String description, String name, int category) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
		this.name = name;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public Product setId(int id) {
		this.id = id;
		return this;
	}

	public int getPrice() {
		return price;
	}

	public Product setPrice(int price) {
		this.price = price;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Product setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getName() {
		return name;
	}

	public Product setName(String name) {
		this.name = name;
		return this;
	}

	public int getCategory() {
		return category;
	}

	public Product setCategory(int category) {
		this.category = category;
		return this;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", description=" + description + ", name=" + name
				+ ", category=" + category + "]";
	}

}
