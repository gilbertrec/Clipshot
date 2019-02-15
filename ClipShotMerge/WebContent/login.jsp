<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="Model.UtenteBean"%>
    <% UtenteBean u=(UtenteBean)request.getSession().getAttribute("utente"); %>
    <%if(u!=null) {%>    	<!-- response.sendRedirect("index.jsp"); -->
    	<meta http-equiv="refresh" content="0;URL='HomePage'">
    <%  } %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/styleLogin.css">
</head>
<body>

<div class="login-page">
  <img src="img/logo_ClipShot.png">
  <div class="form">
    <form class="login-form" action="Login" method="post">
      <input type="text" placeholder="username" name="idUtente"/>
      <input type="password" placeholder="password" name="passwordUtente"/>
      <button>login</button>
      <p class="message">Not registered? <a href="registrazione.html">Create an account</a></p>
    </form>
  </div>
</div>
</body>
</html>