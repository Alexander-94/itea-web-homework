<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp" %>
<body>    
<center>
	<c:forEach items="${products}" var="itms">	
	<table border="1">
	<tr><td width="210">${itms.name}</td><td width="150"></td></tr>
	<tr><td><img width="200" src="./source/images/${itms.id}.png"></td><td>${itms.description}</td></tr>
	<tr><td>${itms.price}</td><td><input type="button" value="Buy"/></td></tr>
	
	</table>
	<br/>
	<br/>
	
</c:forEach>
</center>
</body>
</html>
<%@include file="/source/includes/footer.jsp" %>