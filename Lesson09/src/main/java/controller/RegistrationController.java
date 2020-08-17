package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.DaoFactory;
import service.UserDao;

public class RegistrationController extends HttpServlet {

	private static final String REG_FORM = "WEB-INF/views/regView.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(REG_FORM);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean isError = false;
		List<String> errText = new ArrayList<>();

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
				errText.add("The 'login' is empty.");
				isError = true;
			}
			if (pass.isEmpty()) {
				errText.add("The 'pass' is empty.");
				isError = true;
			}
			if (rePass.isEmpty()) {
				errText.add("The 'rePass' is empty.");
				isError = true;
			}
			if (name.isEmpty()) {
				errText.add("The 'name' is empty.");
				isError = true;
			}
			if (gender == null) {
				errText.add("The 'gender' is empty.");
				isError = true;
			}
			if (address.isEmpty()) {
				errText.add("The 'address' is empty.");
				isError = true;
			}
			if (comment.isEmpty()) {
				errText.add("The 'comment' is empty.");
				isError = true;
			}
			if (agree == null) {
				errText.add("The 'agree' is empty.");
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
						errText.add("Password must contain A-Z!");
					}
					pPattern = Pattern.compile("^.*[a-z]{1,}.*$");
					pMatcher = pPattern.matcher(pass);
					validatePass = pMatcher.matches();
					if (validatePass == false) {
						errText.add("Password must contain a-z!");
					}
				} else {
					errText.add("Password length must contain A-Za-z0-9 and be>=8!");
				}
			} else if (!Objects.equals(pass, rePass)) {
				errText.add("Passwords are different!");
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
				
				DaoFactory daoFactory = DaoFactory.getInstance(Integer.valueOf(1));
				UserDao userDao = daoFactory.getUserDao();		
				
				User user = new User(login, pass, name, gender, address, comment,(Objects.equals(agree, "on") ? 1 : 0));
				req.setAttribute("user", user);
				userDao.insertUser(user);
			} else {
				req.setAttribute("errorText", errText);
			}
			rd = req.getRequestDispatcher(REG_FORM);
			rd.forward(req, resp);
		}		
	}
}
