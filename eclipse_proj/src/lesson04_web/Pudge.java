package lesson04_web;

public class Pudge {

	private String name;
	private int level;
	
	public Pudge() {
		name = "Pudge";
		level = 15;
	}
	
	public String ultimate() {
		return "MeatHook";
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
