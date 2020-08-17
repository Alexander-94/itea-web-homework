package model;

public class Dog {

	private int age;
	private String name;

	public Dog() {
		super();
	}

	public int getAge() {
		return age;
	}

	public Dog setAge(int age) {		
		this.age = age;
		return this;
	}

	public String getName() {
		return name;
	}

	public Dog setName(String name) {
		this.name = name;
		return this;
	}

	public static void main(String[] args) {
		new Dog().setAge(117).setName("Dogg");
	}
}


