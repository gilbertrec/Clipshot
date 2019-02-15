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
<%@ include file="header.jsp"%>

<%UtenteBean u_request=(UtenteBean) request.getAttribute("utenteBean"); %>
<div class="login-page">
	<h1>Modifica Profilo</h1>
  <div class="form">
    <form class="login-form" action="ModificaDati" enctype="multipart/form-data" method="post">
    	<p>Foto Profilo</p>
    	<input type="file" id="imgInp"  name="fotoProfiloModifica" placeholder="Foto del Profilo" >
    	<img id="preview" src="${pageContext.request.contextPath}/PostImageProxyController?user=<%=u.getIdUtente()%>" alt="stai caricando questa immagine">
    	<input type="text" name="nomeModifica" placeholder="Nome" value=<%=u_request.getNome()%> />
    	<input type="text" name="cognomeModifica" placeholder="Cognome" value=<%=u_request.getCognome()%> />
    	<input type="text" name="emailModifica" placeholder="E-mail" value="<%=u_request.getEmail() %>"/>
    	<input type="password" name="passwordModifica" placeholder="Password"/>
     	<input type="text" name="indirizzoModifica" placeholder="Indirizzo"value="<%=u_request.getIndirizzo()%>"/>
     	<div class="sesso">
      	<div class="man"><input type="radio" name="sessoModifica" value="M" <%if( u_request.getSesso().equalsIgnoreCase("M")){ %>checked<%} %> > M</div>
      	<div class="woman"><input type="radio" name="sessoModifica" value="F" <%if( u_request.getSesso().equalsIgnoreCase("F")){ %>checked<%} %>> F</div>
      	</div>
      <input type="date" name="dataNascitaModifica" value="<%=u_request.getStringData().toString() %>">
      <button type="submit" >Modifica</button>
    </form>
  </div>
</div>

<script src='js/fotopreview.js'></script>
</body>
</html>