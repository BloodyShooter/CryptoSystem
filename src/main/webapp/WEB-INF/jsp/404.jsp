<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<html lang="en">
<head>

    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

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
                <li><a href="/downloadJar">Download</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">

    <div class="panel panel-default">
        <div class="panel-heading">
					<span class="lead">
						<h1>Ошибка 404</h1>
					</span>
        </div>
        <div class="row center">
            <img src="404.jpg" alt="404">
        </div>
    </div>
</div>
<script type="text/javascript"
        src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
