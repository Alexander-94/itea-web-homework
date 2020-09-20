<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@include file="/WEB-INF/views/header.jsp"%>
<script src="<c:url value="/resources/scripts/jquery-3.5.1.js" />"></script>
<body>
	<center>
		<table id="productTable">
			<tr>
				<td width="210">${product.name}</td>
				<td width="150"></td>
			</tr>
			<tr>
				<td><img width="400" src="<c:url value="/resources/images/${product.id}.png" />"></td>
			</tr>
			<tr>
				<td width="300">${product.description}</td>
			</tr>
			<tr>
				<td>${product.price}</td>
					<td>
						<input type="hidden" name="prodId" value="${product.id}" />
						<div id="inlnStyle">
						<img id="inlnStyle" width="25" height="25" src="<c:url value="/resources/images/minus.png" />" onclick="minus(${product.id})" />
					    <input type="text" id="qnt${product.id}" value="1" size="2"/>
						<img id="inlnStyle" width="25" height="25" src="<c:url value="/resources/images/plus.png" />" onclick="plus(${product.id})" />
						<input id="buyBtn" type="button" value="Купить" onclick="show(${product.id})"/>
						</div>
					</td>
			</tr>
		</table>
		<br />
		<br />
	</center>
</body>
</html>
<%@include file="/WEB-INF/views/footer.jsp"%>
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
		 
		$.ajax({
			  url: './cart/productAdd',
		      type: 'POST',
		      dataType: 'html',
		      data: { prodId: numb, amount: qnt.value }, //то что передаем на сервер
		      success : function(data) {
	                console.log("SUCCESS: ", data);
	                document.getElementById("amountField").innerHTML = data;
	            },
	            error : function(e) {
	                console.log("ERROR: ", e);
	                alert('error'+e);
	            }		      
	        });
	}
</script>