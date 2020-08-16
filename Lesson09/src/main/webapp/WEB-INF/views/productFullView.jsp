<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp"%>
<body>
	<center>
		<table id="productTable">
			<tr>
				<td width="210">${product.name}</td>
				<td width="150"></td>
			</tr>
			<tr>
				<td><img width="400" src="./source/images/${product.id}.png"></td>
			</tr>
			<tr>
				<td width="300">${product.description}</td>
			</tr>
			<tr>
				<td>${product.price}</td>
				<td>
					<form action="./cart" method="post">
						<input type="hidden" name="prodId" value="${product.id}" />
						<input id="buyBtn" type="submit" value="Купить" />
					</form>
				</td>
			</tr>
		</table>
		<br />
		<br />
	</center>
</body>
</html>
<%@include file="/source/includes/footer.jsp"%>