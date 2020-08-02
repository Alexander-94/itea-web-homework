package itea.web.lesson05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {

	private StringBuilder errorText = new StringBuilder();
	private boolean showForm = true;

	private String errorDivTextPart1 = "<div class='divError'>";
	private String errorDivTextPart2 = "</div>";
	private String formPart1 = "<form id='registrationForm' action='' method='post'>"
			+ "	    <div class='field'><label>Enter your login:</label>"
			+ "	    	<div class='input'><input type='text' name='login' value='";
	private String login;
	private String formPart2 = "'/></div></div><div class='field'><label>Enter your password:</label>"
			+ "	    	<div class='input'><input type='password' name='password'/></div>"
			+ "	    </div><div class='field'>" + "<label>Reenter your password:</label>"
			+ "	    	<div class='input'><input type='password' name='rePassword'/></div>"
			+ "	    </div><div class='field'>" + "<label>Enter your name:</label>"
			+ "	    	<div class='input'><input type='text' name='name'/></div></div>"
			+ "	    <div><label>Choose your gender:&nbsp;</label>"
			+ "	    	<label>M</label><input type='radio' name='gender' value='male'";
	private String pass;
	private String rePass;
	private String name;
	private String gender;
	private String formPart3 = "><label>F</label><input type='radio' name='gender' value='female'";
	private String address;
	private String formPart4 = "></div><br><div class='field'><label>Enter your address:</label><div class='input'><select name='address'><option value='lnr'";
	private String formPart5 = ">LNR</option><option value='dnr' ";
	private String formPart6 = ">DNR</option><option value='crimea' ";
	private String formPart7 = ">Crimea</option></select></div></div><div class='field'><label>Enter your comment:</label>"
			+ "<div class='input'><textarea name='comment' cols='15' rows='10'></textarea></div>"
			+ "</div><div><label>I agree:</label>"
			+ "<input type='checkbox' name='agree' style='vertical-align: middle;'>" + "</div><div class='submit'>"
			+ "<button type='submit' value='send'>Enter</button></div></form>";
	private String comment;
	private String agree;
	private String menu = "<a href='../authorization'>Authorization</a>|<a href='../registration'>Registration</a>";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		showForm = true;
		StringBuilder form = new StringBuilder();
		form.append(menu);
		form.append(errorDivTextPart1);
		form.append(errorText.toString());
		form.append(errorDivTextPart2);
		form.append(formPart1);
		form.append(login != null ? login : "");
		form.append(formPart2);
		form.append(gender != null && gender.equals("male") ? "checked" : "");
		form.append(formPart3);
		form.append(gender != null && gender.equals("female") ? "checked" : "");
		form.append(formPart4);
		form.append(address != null && address.equals("lnr") ? "selected" : "");
		form.append(formPart5);
		form.append(address != null && address.equals("dnr") ? "selected" : "");
		form.append(formPart6);
		form.append(address != null && address.equals("crimea") ? "selected" : "");
		form.append(formPart7);

		PrintWriter out = resp.getWriter();
		if (showForm) {
			out.write("<!doctype html><html lang='en'>");
			out.write("<head><title></title><meta charset='UTF-8' />"
					+ "<link href='./source/styles/styleRegistration.css' rel='stylesheet' /></head>");
			out.write(form.toString());
			out.write("</html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder result = new StringBuilder();
		result.append("<!doctype html><html lang='en'>");
		PrintWriter out = resp.getWriter();
		errorText = new StringBuilder();
		boolean isError = false;

		errorText.append("<ul>");
		login = req.getParameter("login");
		pass = req.getParameter("password");
		rePass = req.getParameter("rePassword");
		name = req.getParameter("name");
		gender = req.getParameter("gender");
		address = req.getParameter("address");
		comment = req.getParameter("comment");
		agree = req.getParameter("agree");

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

			// ���� ���� ������ ���������
			if (!isError && validateEmail && validatePass) {
				result.append("Registration successfull!");
				showForm = false;
			} else {
				doGet(req, resp);
			}
		}
		errorText.append("</ul>");

		result.append("</html>");
		out.write(result.toString());
	}

}
