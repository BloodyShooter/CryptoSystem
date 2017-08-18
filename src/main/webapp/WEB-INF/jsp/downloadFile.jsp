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
<div class="generic-container">
    <div class="container">

        <div class="panel panel-default">
            <div class="panel-heading">
					<span class="lead">
						<h1>Удача ${name}</h1>
					</span>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    Вы можете загрузить файл -> <a href="/download?nameFile=${newFile}">download file ${newFile} </a>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <h1>Размер текущего файла ${bytes} байт.</h1>
                </div>
            </div>
        </div>
        <div class="well">
            Вернуться <a href="/">Home</a>
        </div>
    </div>

</div>
</body>
</html>
