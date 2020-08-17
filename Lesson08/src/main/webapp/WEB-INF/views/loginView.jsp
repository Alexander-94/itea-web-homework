<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp" %>
<body>
<c:choose>
<c:when test = "${user==null}">
	<form id="loginForm" action="./authorization" method="post">

		<div class="field">
			<label>Enter your login:</label>
			<div class="input">
				<input type="text" name="f1" value="" id="login" />
			</div>
		</div>

		<div class="field">
			<a href="#" id="forgot">Forgot your password?</a>
			<label>Enter your password:</label>
			<div class="input">
				<input type="password" name="f2" value="" id="pass" />
			</div>
		</div>

		<div class="submit">
			<button type="submit" value="Send">Enter</button>
			<label id="remember"><input name="" type="checkbox" value="" />Remember	me</label>
		</div>
		
		${errorText}
	</form>

</c:when>
<c:otherwise>
<form action="./authorization" method="post">
	<input type="hidden" name="logOut" value = "logOut"/>
	<input type="submit" value="Log Out"/>
</form>
</c:otherwise>
</c:choose>
</body>
</html> 
<%@include file="/source/includes/footer.jsp" %>
