<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="Model.UtenteBean"%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/styleModInfo.css">
</head>
<body>
<div class="login-page">
	<h1>Modifica Profilo</h1>
  <div class="form">
    <form class="login-form" action="ModificaDati" method="post">
    	<p>Foto Profilo</p>
    	<input type="file" id="imgInp" name="photo" placeholder="Foto del Profilo" >
      <button type="submit" >Modifica</button>
    </form>
  </div>
</div>

<script src='js/fotopreview.js'></script>
</body>
</html>