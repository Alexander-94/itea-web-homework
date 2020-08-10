package controller;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.DBWork;

public class RegistrationController extends HttpServlet {

	private static final String REG_FORM = "WEB-INF/views/regView.jsp";
	//private static final String REG_DONE_FORM = "WEB-INF/views/regDoneView.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(REG_FORM);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean isError = false;
		StringBuilder errorText = new StringBuilder();
		errorText.append("<ul>");

		String login = req.getParameter("login");
		String pass = req.getParameter("password");
		String rePass = req.getParameter("rePassword");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String comment = req.getParameter("comment");
		String agree = req.getParameter("agree");

		String genderMaleState = "";
		String genderFemaleState = "";
		String addressLnrState = "";
		String addressDnrState = "";
		String addressCrimeaState = "";

		if (login != null) {
			if (login.isEmpty()) {
				errorText.append("<li>The 'login' is empty.</li>");
				isError = true;
			}
			if (pass.isEmpty()) {
				errorText.append("<li>The 'pass' is empty.</li>");
				isError = true;
			}
			if (rePass.isEmpty()) {
				errorText.append("<li>The 'rePass' is empty.</li>");
				isError = true;
			}
			if (name.isEmpty()) {
				errorText.append("<li>The 'name' is empty.</li>");
				isError = true;
			}
			if (gender == null) {
				errorText.append("<li>The 'gender' is empty.</li>");
				isError = true;
			}
			if (address.isEmpty()) {
				errorText.append("<li>The 'address' is empty.</li>");
				isError = true;
			}
			if (comment.isEmpty()) {
				errorText.append("<li>The 'comment' is empty.</li>");
				isError = true;
			}
			if (agree == null) {
				errorText.append("<li>The 'agree' is empty.</li>");
				isError = true;
			}

			boolean validateEmail = false;
			Pattern ePattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z]{3}$");
			Matcher eMatcher = ePattern.matcher(login);
			validateEmail = eMatcher.matches();
			if (!validateEmail) {
				login = "not valid email!";
			}
			boolean validatePass = false;
			if (Objects.equals(pass, rePass) && !pass.isEmpty() && !rePass.isEmpty()) {
				Pattern pPattern = Pattern.compile("^[A-Za-z0-9]{8,}$");
				Matcher pMatcher = pPattern.matcher(pass);
				validatePass = pMatcher.matches();
				if (validatePass == true) {
					pPattern = Pattern.compile("^.*[A-Z]{1,}.*$");
					pMatcher = pPattern.matcher(pass);
					validatePass = pMatcher.matches();
					if (validatePass == false) {
						errorText.append("<li>Password must contain A-Z!</li>");
					}
					pPattern = Pattern.compile("^.*[a-z]{1,}.*$");
					pMatcher = pPattern.matcher(pass);
					validatePass = pMatcher.matches();
					if (validatePass == false) {
						errorText.append("<li>Password must contain a-z!</li>");
					}
				} else {
					errorText.append("<li>Password length must contain A-Za-z0-9 and be>=8!</li>");
				}
			} else if (!Objects.equals(pass, rePass)) {
				errorText.append("<li>Passwords are different!</li>");
			}

			if (gender != null && gender.equals("male")) {
				genderMaleState = "checked";
			} else {
				genderMaleState = "";
			}

			if (gender != null && gender.equals("female")) {
				genderFemaleState = "checked";
			} else {
				genderFemaleState = "";
			}

			if (address != null && address.equals("lnr")) {
				addressLnrState = "selected";
			} else {
				addressLnrState = "";
			}
			if (address != null && address.equals("dnr")) {
				addressDnrState = "selected";
			} else {
				addressDnrState = "";
			}
			if (address != null && address.equals("crimea")) {
				addressCrimeaState = "selected";
			} else {
				addressCrimeaState = "";
			}

			// до ошибок - сохраняем атрибуты
			req.setAttribute("login", login);
			req.setAttribute("genderMaleState", genderMaleState);
			req.setAttribute("genderFemaleState", genderFemaleState);
			req.setAttribute("addressLnrState", addressLnrState);
			req.setAttribute("addressDnrState", addressDnrState);
			req.setAttribute("addressCrimeaState", addressCrimeaState);

			// если есть данные проверяем
			if (!isError && validateEmail && validatePass) {
				DBWork dbWork = new DBWork();
				User user = new User(login, pass, name, gender, address, comment,(Objects.equals(agree, "on") ? 1 : 0));
				req.setAttribute("user", user);
				dbWork.insertUser(user);
				//rd = req.getRequestDispatcher(REG_DONE_FORM);
			} else {
				req.setAttribute("errorText", errorText.toString());
				//rd = req.getRequestDispatcher(REG_FORM);
			}
			rd = req.getRequestDispatcher(REG_FORM);
			rd.forward(req, resp);
		}
		errorText.append("</ul>");
	}
}
