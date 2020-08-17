package model;

public class User {

	private String login;
	private String password;
	private String name;
	private String gender;
	private String address;
	private String comment;
	private int agree;

	public User() {
	}

	public User(String login, String password, String name, String gender, String address, String comment, int agree) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.comment = comment;
		this.agree = agree;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getAgree() {
		return agree;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ ", address=" + address + ", comment=" + comment + ", agree=" + agree + "]";
	}

}
