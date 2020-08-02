package itea.web.lesson05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authorization extends HttpServlet {

	private String form = "<form id='loginForm' action='' method='post'>" + "	<div class='field'>"
			+ "		<label>Enter your login:</label>"
			+ "		<div class='input'><input type='text' name='f1' value='' id='login' /></div>" + "	</div>"
			+ "	<div class='field'>" + "		<a href='#' id='forgot'>Forgot your password?</a>"
			+ "		<label>Enter your password:</label>"
			+ "		<div class='input'><input type='password' name='f2' value='' id='pass' /></div>" + "	</div>"
			+ "	<div class='submit'>" + "		<button type='submit' value='Send'>Enter</button>"
			+ "		<label id='remember'><input name='' type='checkbox' value='' /> Remember me</label>"
			+ "	</div></form>";

	private String resultStr = "";
	private String menu = "<a href='../authorization'>Authorization</a>|<a href='../registration'>Registration</a>";
	private String login;
	private String password;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		login = null;
		password = null;
		out.write("<!doctype html><html lang='en'>");
		out.write("<head><title></title><meta charset='UTF-8' />"
				+ "<link href='./source/styles/styleAuthorization.css' rel='stylesheet' /></head>");
		out.write(resultStr + menu + form);
		out.write("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder result = new StringBuilder();
		result.append("<!doctype html><html lang='en'>");
		PrintWriter out = resp.getWriter();
		login = req.getParameter("f1");
		password = req.getParameter("f2");

		if (login == null && password == null) {

		} else {
			if (login.length() == 0 && password.length() == 0) {
				result.append("Input values are empty!");

			} else {
				if (login.equals("admin") && password.equals("123")) {
					result.append("<div style='color:green'>Access granted!</div>");
				} else {
					result.append("<div style='color:red'>Access denied!</div>");
					resultStr = "<div style='color:red'>Access denied!</div>";
					doGet(req, resp);
				}
			}
		}

		result.append("</html>");
		out.write(result.toString());
	}
}
