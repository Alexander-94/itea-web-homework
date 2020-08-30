<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
</div>            
				<div id="sidebar">
					<table border=1>
                    <tr>
                    <td width="252" align="left">
                    <font color=white>
                    Вы авторизировались как 
<c:choose>
<c:when test = "${user!=null}">
					${user.name}
</c:when>
<c:otherwise>Guest</c:otherwise>
</c:choose>
					<br />
                    В вашей корзине <span id="amountField"></span><!--  ${sessionScope.allQuantity}  -->товаров.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Каталог продукции</h2>
					<ul>
						<li><a href="./products?catId=1">Здоровье</a></li>
						<li><a href="./products?catId=2">Дом</a></li>
						<li><a href="./products?catId=3">Нутриенты</a></li>
						<li><a href="./registration">Регистрация</a></li>
						<li><a href="./authorization">Вход</a></li>
						<li><a href="./cart">Корзина</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<p>-</p>
</div>
<!-- end #footer -->
</body>
</html>