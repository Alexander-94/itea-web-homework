<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp" %>
<body>    
<center>
	<c:forEach items="${sessionScope.cart}" var="itms">	
	<table id="productTable">
	<tr>
	 <td><a href="./prodFull?productId=${itms.id}" style="text-decoration:none;"><img width="75" src="./source/images/${itms.id}.png"></a></td>
	 <td width="300"><a href="./prodFull?productId=${itms.id}" style="text-decoration:none;">${itms.name}</a></td>
	 <td>
     <form action="./cart" method="post">
         <input type="hidden" name="deleteId" value="${itms.id}"/>
		 <input type="image" src="./source/images/del.png" width="25" height = "40">
	 </form>
	 </td>
    </tr>
	</table>
	<br/>
	<br/>
</c:forEach>
</center>
</body>
</html>
<%@include file="/source/includes/footer.jsp" %>