package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.User;
import service.UserDataSource;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@RequestMapping(method = RequestMethod.GET)
	public String getRegView() {
		return "regView"; // переход на вью regView.jsp по гету
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registration(@RequestParam(value = "login", required = false) String login,
			@RequestParam(value = "password", required = false) String pass,
			@RequestParam(value = "rePassword", required = false) String rePass,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "comment", required = false) String comment,
			@RequestParam(value = "agree", required = false) String agree, HttpServletRequest req) {

		boolean isError = false;
		List<String> errText = new ArrayList<>();
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
				ApplicationContext context = new ClassPathXmlApplicationContext("data-source-context.xml");
				UserDataSource us = (UserDataSource) context.getBean("userDataSourceBean");

				User user = new User(login, pass, name, gender, address, comment,
						(Objects.equals(agree, "on") ? 1 : 0));
				req.setAttribute("user", user);
				us.insertUser(user);
			} else {
				req.setAttribute("errorText", errText);
			}
		}
		return "regView";

	}

}
