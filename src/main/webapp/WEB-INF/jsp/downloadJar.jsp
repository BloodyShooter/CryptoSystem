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
            <a class="navbar-brand" href="/">CryptoSystem</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/encrypt">Encrypt</a></li>
                <li><a href="/decrypt">Decrypt</a></li>
                <li class="active"><a href="/downloadJar">Download</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="generic-container">
    <div class="container">

        <div class="panel panel-default">
            <div class="panel-heading">
					<span class="lead">
						<h1>Загрузите Jar и пользуйтесь везде.</h1>
					</span>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    Вы можете загрузить файл -> <a href="/download?nameFile=jar/OFB.jar">OFB.jar</a>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <div class="alert alert-info myinfo">
                        <strong>Info!</strong> Для запуска jar файла, нужно установленая JVM.
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container -->
</div>

<script type="text/javascript"
        src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>