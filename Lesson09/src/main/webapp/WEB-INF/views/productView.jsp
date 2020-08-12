<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp" %>
<body>    
<center>
	<c:forEach items="${products}" var="itms">	
	<table id="productTable">
	<tr><td width="210">${itms.name}</td><td width="150"></td></tr>
	<tr><td><img width="200" src="./source/images/${itms.id}.png"></td><td width="300"><c:set var="string" value="${itms.description}"/>${fn:substring(string, 0, 100).concat("...")}</td></tr>
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