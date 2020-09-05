<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp"%>
<script src="./source/scripts/jquery-3.5.1.js"></script>
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
					  <a id="buyHref" href="./prodFull?productId=${itms.key.id}">${itms.key.name}</a>
					</td>
					<td width="150">
					  <div id="inlnStyle">
					    <img id="inlnStyle" width="25" height="25" src="./source/images/minus.png" onclick="minus(${itms.key.id})" />
						<input type="text" id="qnt${itms.key.id}" value="${itms.value}"	size="2" />
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
		var direction = 'minus';
		qnt.value = +qnt.value-1;
		if(qnt.value<0){
		    qnt.value = 0;
		}else{
			updateInput(direction, numb, qnt);//минус 1 товар
		}
	}
	function plus(numb){
		var qnt = document.getElementById("qnt"+numb);//получение ссылки
		var direction = 'plus';
		qnt.value = +qnt.value+1;
		updateInput(direction, numb, qnt);//плюс 1 товар
	}

	function updateInput(direction, numb, qnt){
	 var refrsh = 1;  
	 $.ajax({
		  url: './cart',
	      type: 'POST',
	      data: {refreshItem: refrsh, directionFlag: direction, prodId: numb, amount: qnt.value }, //то что передаем на сервер
	      success: function (data) { // то что пришло с сервера //результат работы сервлета 	
	    	  document.getElementById("amountField").innerHTML = data;
	      }
       });
	}
</script>