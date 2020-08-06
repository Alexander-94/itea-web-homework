<%@ page isELIgnored="false"%>
Hello user: ${user.name}!
<br>
<form action="./authorization" method="post">
	<input type="hidden" name="logOut" />
	<input type="submit" value="Log out!" />
</form>