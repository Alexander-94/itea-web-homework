package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.DaoFactory;
import service.UserDao;

public class UserController extends HttpServlet {

	private static final String LOGIN_FORM = "WEB-INF/views/loginView.jsp";
	private String errorText = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(LOGIN_FORM);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();

		String login = req.getParameter("f1");
		String password = req.getParameter("f2");
		if (req.getParameter("logOut") != null) {
			session.setAttribute("user", null);
		} else {
			DaoFactory daoFactory = DaoFactory.getInstance(Integer.valueOf(1));
			UserDao userDao = daoFactory.getUserDao();

			User user = userDao.getLogin(login, password);
			errorText = "";
			session.setAttribute("user", user);
			if (user == null) {
				errorText = "Login or Password incorrect!";
				req.setAttribute("errorText", errorText);
			}
		}

		rd = req.getRequestDispatcher(LOGIN_FORM);
		rd.forward(req, resp);
	}
}
