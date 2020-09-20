package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.User;
import service.UserDataSource;

@Controller
@RequestMapping("/authorization")
public class UserController {

	@RequestMapping(method = RequestMethod.GET)
	public String getLoginM() {
		return "loginView"; // переход на вью loginView.jsp по гету
	}

	@RequestMapping(method = RequestMethod.POST)
	public String checkUserM(HttpSession session, HttpServletRequest req) {
		String errorText = "";
		String login = req.getParameter("f1");
		String password = req.getParameter("f2");
		String logOut = req.getParameter("logOut");
		if (logOut != null) {
			session.setAttribute("user", null);
		} else {
			ApplicationContext context = new ClassPathXmlApplicationContext("data-source-context.xml");
			UserDataSource us = (UserDataSource) context.getBean("userDataSourceBean");

			User user = us.getLogin(login, password);
			errorText = "";
			session.setAttribute("user", user);
			if (user == null) {
				errorText = "Login or Password incorrect!";
				req.setAttribute("errorText", errorText);
			}
		}
		return "loginView"; // переход на вью loginView.jsp
	}

}
