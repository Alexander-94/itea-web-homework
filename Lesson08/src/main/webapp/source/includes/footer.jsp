<%@ page contentType="text/html; charset=UTF-8" %>
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
                    В вашей корзине 0 товаров.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Каталог продукции</h2>
					<ul>
						<li><a href="./products">Здоровье</a></li>
						<li><a href="./products">Дом</a></li>
						<li><a href="./products">Нутриенты</a></li>
						<li><a href="./registration">Регистрация</a></li>
						<li><a href="./authorization">Вход</a></li>
						<li><a href="cart.php">Корзина</a></li>
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