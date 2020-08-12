<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp" %>
<body>    
<center>
	<c:forEach items="${products}" var="itms">	
	<table id="productTable">
	<tr><td width="210"><a href="./prodFull?productId=${itms.id}" style="text-decoration:none;">${itms.name}</a></td><td width="150"></td></tr>
	<tr><td><a href="./prodFull?productId=${itms.id}" style="text-decoration:none;"><img width="200" src="./source/images/${itms.id}.png"></a></td>
	<td width="300"><c:set var="string" value="${itms.description}"/><a href="./prodFull?productId=${itms.id}" style="text-decoration:none;">${fn:substring(string, 0, 100).concat("...")}</a></td></tr>
	<tr><td>${itms.price}</td><td><form action="./cart" method="post">
									<input type="hidden" name="prodId" value = "${itms.id}"/>
									<input type="submit" value="Buy"/>
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