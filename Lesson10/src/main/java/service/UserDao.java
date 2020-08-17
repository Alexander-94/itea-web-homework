package service;

import java.sql.SQLException;

import model.User;

public interface UserDao {

	User getLogin(String login, String password);

	void insertUser(User user);

	String hashPassword(String password) throws SQLException;
}
