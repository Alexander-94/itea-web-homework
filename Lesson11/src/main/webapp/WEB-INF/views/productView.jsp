<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@include file="/WEB-INF/views/header.jsp"%>
<script src="<c:url value="/resources/scripts/jquery-3.5.1.js" />"></script>
<body>
	<center>
		<c:forEach items="${products}" var="itms">
			<table id="productTable">
				<tr>
					<td width="210">
					<a id="buyHref"	href="./prodFull?productId=${itms.id}">
					 ${itms.name}
					</a>
					</td>
					<td width="150"></td>
				</tr>
				<tr>
					<td>
					  <a href="./prodFull?productId=${itms.id}">
					   <img width="200" src="<c:url value="/resources/images/${itms.id}.png" />" >
					  </a>
					</td>
					<td width="300"><c:set var="string"	value="${itms.description}" />
					  <a id="buyHref" href="./prodFull?productId=${itms.id}">
					   ${fn:substring(string, 0, 100).concat("...")}
					  </a>
					</td>
				</tr>
				<tr>
					<td>${itms.price}</td>
					<td>
						<input type="hidden" name="prodId" value="${itms.id}" />
						<div id="inlnStyle">
						<img id="inlnStyle" width="25" height="25" src="<c:url value="/resources/images/minus.png" />" onclick="minus(${itms.id})" />
					    <input type="text" id="qnt${itms.id}" value="1" size="2"/>
						<img id="inlnStyle" width="25" height="25" src="<c:url value="/resources/images/plus.png" />" onclick="plus(${itms.id})" />
						<input id="buyBtn" type="button" value="Купить" onclick="show(${itms.id})"/>
						</div>
					</td>
				</tr>
			</table>
			<br />
			<br />
		</c:forEach>
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