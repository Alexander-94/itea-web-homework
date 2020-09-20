<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href="<c:url value="/resources/css/styleAuthorization.css" />" rel="stylesheet" type="text/css" media="screen" />
<link href="<c:url value="/resources/css/styleRegistration.css" />"  rel="stylesheet" type="text/css" media="screen" />
<script src="<c:url value="/resources/scripts/jquery-3.5.1.js" />"></script>
<script src="<c:url value="/resources/scripts/jquery.poptrox.js" />"></script>
</head>
<body>
<div id="header" class="container">
	<div id="logo">
		<h1><a href="#">Coral Club</a></h1>
		<p>Продукты для здоровья<a href="http://www.freecsstemplates.org"></a></p>
	</div>
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="./index.jsp">Главная</a></li>
			<li><a href="./products">Товары</a></li>
			<li><a href="./registration">Регистрация</a></li>
			<li><a href="./authorization">Вход</a></li>
			<li><a href="./cart">Корзина</a></li>
		</ul>
	</div>
</div>
<!-- end #header -->
<div id="poptrox">
	<!-- start -->
	<ul id="gallery">
		<li><a href="<c:url value="/resources/images/top1_big.png" />"><img src="<c:url value="/resources/images/top1.png" />" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="<c:url value="/resources/images/top2_big.png" />"><img src="<c:url value="/resources/images/top2.png" />" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="<c:url value="/resources/images/top3_big.png" />"><img src="<c:url value="/resources/images/top3.png" />" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li><a href="<c:url value="/resources/images/top4_big.png" />"><img src="<c:url value="/resources/images/top4.png" />" title="Phasellus nec erat sit amet nibh pellentesque congue." alt="" /></a></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
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