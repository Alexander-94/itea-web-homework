<?php
session_start();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Photoshoot 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20110926

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Photoshoot by FCT</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="jquery.poptrox-0.1.js"></script>
</head>
<body>
<div id="header" class="container">
	<div id="logo">
		<h1><a href="#">Мой магазин </a></h1>
		<p>я - двоечник и лентяй (<a href="http://www.freecsstemplates.org"></a></p>
	</div>
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="index.php">Главная</a></li>
			<li><a href="products.php">Товары</a></li>
			<li><a href="registration.php">Регистрация</a></li>
			<li><a href="login.php">Вход</a></li>
			<li><a href="cart.php">Корзина</a></li>
		</ul>
	</div>
</div>
<!-- end #header -->
<div id="poptrox">
	<!-- start -->
	<ul id="gallery">
		<li><a href="images/img01_big.jpg"><img src="images/img01.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="images/img02_big.jpg"><img src="images/img02.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="images/img03_big.jpg"><img src="images/img03.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="images/img04_big.jpg"><img src="images/img04.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="images/img02_big.jpg"><img src="images/img02.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="images/img01_big.jpg"><img src="images/img01.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="images/img04_big.jpg"><img src="images/img04.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="images/img03_big.jpg"><img src="images/img03.jpg" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
	</ul>
	<br class="clear" />
	<script type="text/javascript">
		$('#gallery').poptrox();
		</script>
	<!-- end -->
</div>
<div id="page">
	<div id="bg1">
		<div id="bg2">
			<div id="bg3">
				<div id="content">
					<h2>Добро пожаловать в магазин !</h2>
					<p> Наши профессиональные менеджеры помогут быстро и качественно подобрать нужный Вам товар. Звоните круглосуточно! Втюхаем все!</p>
					<p>К сожалению, Вы можете наткнуться на схожий дизайн в интернете - это все потому,что автор лентяй и двоечник и не знает, как поменять шаблон.</p>
					<p>Надеемся, что это не скажется на Вашей покупательной способности.</p>
				</div>
				<div id="sidebar">
					<h2>Товары</h2>
					<ul>
						<li><a href="products.php">Категория 1</a></li>
						<li><a href="products.php">Категория 2</a></li>
						<li><a href="products.php">Категория 3</a></li>
						<li><a href="registration.php">Регистрация</a></li>
						<li><a href="login.php">Вход</a></li>
						<li><a href="cart.php">Корзина</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<p>Copyright (c) by Бондаренко Антон</p>
</div>
<!-- end #footer -->
</body>
</html>
