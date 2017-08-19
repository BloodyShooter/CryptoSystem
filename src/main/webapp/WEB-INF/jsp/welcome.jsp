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
					<li class="active"><a href="/">Encrypt</a></li>
					<li><a href="/decrypt">Decrypt</a></li>
					<li><a href="/downloadJar">Download</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="generic-container">
		<div class="container">

			<div class="panel panel-default">
				<div class="panel-heading">
					<span class="lead">
						<h1>Hi</h1>
						<h2><P>This is Page for encrypt files.</P></h2>
					</span>
				</div>
				<div class="uploadcontainer">
					<form method="post" action="upload" enctype="multipart/form-data">
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable">File to upload:</label>
								<div class="col-md-7">
									<input type="file" name="file" class="form-control input-sm"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable">Пароль</label>
								<div class="col-md-7">
									<input type="password" name="password" class="form-control input-sm" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="alert alert-info myinfo">
								<strong>Info!</strong> Если поле "Пароль" будет пустым то используется случайно сгенерированный пароль.
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable">Архивация</label>
								<div class="radio">
									<br>
									<label><input type="radio" name="arch" value="false">Неархивировать</label>
									<br>
									<label><input type="radio" name="arch" value="true" checked>Архивировать</label>
								</div>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable">Трансп. кодирование.</label>
								<div class="radio">
									<br>
									<label><input type="radio" name="base64" value="true">Использовать</label>
									<br>
									<label><input type="radio" name="base64" value="false" checked>Обойтись</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-actions floatRight">
								<input type="submit" value="Погнали" class="btn btn-primary btn-sm">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- /.container -->
	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
