<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp"%>
<body>
	<center>
		<c:forEach items="${sessionScope.cart}" var="itms">
			<table id="productTable">
				<tr>
					<td>
					   <a href="./prodFull?productId=${itms.key.id}">
					     <img width="75" src="./source/images/${itms.key.id}.png">
					   </a>
					</td>
					<td width="300">
					    <a id="buyHref" href="./prodFull?productId=${itms.key.id}">
					      ${itms.key.name}
					    </a>
					</td>
					<td width="150">
						<div id="inlnStyle">
						 <img id="inlnStyle" width="25" height="25" src="./source/images/minus.png" onclick="minus(${itms.key.id})" />
					     <input type="text" id="qnt${itms.key.id}" value="1" size="2"/>
						 <img id="inlnStyle" width="25" height="25" src="./source/images/plus.png" onclick="plus(${itms.key.id})" />
						</div>
					</td>
					<td>
						<form action="./cart" method="post">
							<input type="hidden" name="deleteId" value="${itms.key.id}" />
							<input type="image" src="./source/images/del.png" width="25" height="40">
						</form>
					</td>
				</tr>
			</table>
			<br />
			<br />
		</c:forEach>
	</center>
</body>
</html>
<%@include file="/source/includes/footer.jsp"%>
<script>
	function minus(numb){
		var qnt = document.getElementById("qnt"+numb);//получение ссылки
		qnt.value = +qnt.value-1;
		if(qnt.value<0)
		    qnt.value = 0;
	}
	function plus(numb){
		var qnt = document.getElementById("qnt"+numb);//получение ссылки
		qnt.value = +qnt.value+1;
	}

	function show(numb){
		var qnt = document.getElementById("qnt"+numb);//получение ссылки
	    alert("id:"+ numb+". quantity:"+qnt.value);
	}
</script>