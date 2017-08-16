<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
	<title>CryptoSystem</title>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">CryptoSystem</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>Hi</h1>
			<h2><P>This is application for encrypt/decrypt files.</P></h2>
			<form method="post" action="upload" enctype="multipart/form-data">
				File to upload: <input type="file" name="file"><br><br>
				<input type="password" name="password"> Пароль<br><br>
				<P>Если поле "Пароль" будет пустым то используется случайно сгенерированный пароль.</P>
				<input type="radio" name="arch" value="false"> Неархивировать <br>
				<input type="radio" name="arch" value="true" checked> Архивировать <br>
				<hr>
				<input type="radio" name="base64" value="true"> Обойтись <br>
				<input type="radio" name="base64" value="false" checked> Использовать трансп. кодирование. <br><br>
				<input type="submit" value="Погнали"> Press!!!
			</form>
		</div>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
