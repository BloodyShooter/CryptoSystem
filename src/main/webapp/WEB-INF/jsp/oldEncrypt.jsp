<!DOCTYPE html PUBLIC "-//IETF//DTD HTML 2.0//EN">
<%@ page contentType="text/html;charset=utf-8" %>
<HTML>
<HEAD>
    <meta charset="utf-8">
    <TITLE>
        CryptoSystem
    </TITLE>
</HEAD>
<BODY>
    <H1>Hi</H1>
    <P>This is application for encrypt/decrypt files.</P>

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
</BODY>
</HTML>