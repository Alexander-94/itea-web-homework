<%@include file="includes/menu.jsp" %>
<%@ page import="lesson04_web.DBWork"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.regex.*"%>
<%
    boolean showForm = true;
    boolean isError = false;
	StringBuilder errorText = new StringBuilder();
	errorText.append("<ul>");
	
    String login = request.getParameter("login");
	String pass = request.getParameter("password");
	String rePass = request.getParameter("rePassword");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String address = request.getParameter("address");
	String comment = request.getParameter("comment");
	String agree = request.getParameter("agree");
	
	if(login!=null){
		if(login.isEmpty()){
			errorText.append("<li>The 'login' is empty.</li>");
			isError = true;
		}
		if(pass.isEmpty()){
			errorText.append("<li>The 'pass' is empty.</li>");
			isError = true;
		}
		if(rePass.isEmpty()){
			errorText.append("<li>The 'rePass' is empty.</li>");
			isError = true;
		}
		if(name.isEmpty()){
			errorText.append("<li>The 'name' is empty.</li>");
			isError = true;
		}
		if(gender==null){
			errorText.append("<li>The 'gender' is empty.</li>");
			isError = true;
		}
		if(address.isEmpty()){
			errorText.append("<li>The 'address' is empty.</li>");
			isError = true;
		}
		if(comment.isEmpty()){
			errorText.append("<li>The 'comment' is empty.</li>");
			isError = true;
		}
		if(agree == null){
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
		
		//если есть данные проверяем
		if(!isError && validateEmail && validatePass){
			DBWork dbWork = new DBWork();
			dbWork.insertUser(login, pass, name, gender, address, comment, (Objects.equals(agree, "on")?1:0));
            out.write("Registration successfull!");
			showForm = false;
		}
	}
	errorText.append("</ul>");	


if(showForm){%>
    <div class="divError">
     	<%out.write(errorText.toString());%>
	</div>
    
	<form id="registrationForm" action="" method="post">
	    <div class="field">
	    	<label>Enter your login:</label>
	    	<div class="input"><input type="text" name="login" value="<%=(login!=null?login:"")%>"/></div>
	    </div>

        <div class="field">		
	    	<label>Enter your password:</label>
	    	<div class="input"><input type="password" name="password"/></div>
	    </div>	
	
        <div class="field">		
	    	<label>Reenter your password:</label>
	    	<div class="input"><input type="password" name="rePassword"/></div>
	    </div>
	
	    <div class="field">		
	    	<label>Enter your name:</label>
	    	<div class="input"><input type="text" name="name"/></div>
	    </div>
	
	    <div>		
	    	<label>Choose your gender:&nbsp;</label>
	    	<label>M</label><input type="radio" name="gender" value="male" <%=(gender!=null&&gender.equals("male")?"checked":"")%>>
	    	<label>F</label><input type="radio" name="gender" value="female" <%=(gender!=null&&gender.equals("female")?"checked":"")%>>
	    </div><br>
	
	    <div class="field">		
	    	<label>Enter your address:</label>
	        	<div class="input">
	    	    <select name="address">
	    		    <option value="lnr" <%=(address!=null&&address.equals("lnr")?"selected":"")%>>LNR</option>
	    			<option value="dnr" <%=(address!=null&&address.equals("dnr")?"selected":"")%>>DNR</option>
	    			<option value="crimea" <%=(address!=null&&address.equals("crimea")?"selected":"")%>>Crimea</option>
	    	    </select>
	          	</div>
	    </div>
	
	    <div class="field">		
	    	<label>Enter your comment:</label>
	    	<div class="input"><textarea name="comment" cols="15" rows="10"></textarea></div>
	    </div>
	
	    <div>		
	    	<label>I agree:</label>
	    	<input type="checkbox" name="agree" style="vertical-align: middle;">
	    </div>
	
	    <div class="submit">
	    	<button type="submit" value="send">Enter</button>
	    </div>
    </form>
<%}%>