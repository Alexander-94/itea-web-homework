package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.DBWork;

public class UserController extends HttpServlet {

	private static final String LOGIN_FORM = "WEB-INF/views/formView.jsp";
	private static final String USER_ENETERED_FORM = "WEB-INF/views/userView.jsp";
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

		if (req.getParameter("logOut") != null) {
			session.setAttribute("user", null);
		}
		if (session.getAttribute("user") != null) {
			rd = req.getRequestDispatcher(USER_ENETERED_FORM);
			rd.forward(req, resp);
		} else {
			String login = req.getParameter("f1");
			String password = req.getParameter("f2");
			
			DBWork dbWork = new DBWork();
			User user = dbWork.getLogin(login, password);
			
			if (user == null) {
				errorText = "Login or Password incorrect!";
				req.setAttribute("errorText", errorText);
				rd = req.getRequestDispatcher(LOGIN_FORM);
			} else {
				//errorText = "";
				req.setAttribute("user", user);
				session.setAttribute("user", user);
				rd = req.getRequestDispatcher(USER_ENETERED_FORM);
			}
			rd.forward(req, resp);
		}

		//rd.forward(req, resp);
	}
}
