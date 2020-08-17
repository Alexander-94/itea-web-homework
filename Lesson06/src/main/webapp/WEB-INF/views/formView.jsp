<%@ page isELIgnored="false"%>
<%@include file="/source/includes/menu.jsp"%>
<body>
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
</body>
</html>