<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
<!DOCTYPE html>
<html>
<head>
    <title>ClipShot - Aggiungi una nuova Carta!</title>
    <link rel="stylesheet" type="text/css" href="css/styleModInfo.css">
</head>
<body>
<%@include file="headerProfilo.jsp" %>
<div class="login-page">
	<h1>Aggiungi Carta di Credito</h1>
  <div class="form">
    <form class="login-form" action="AggiungiCartaDiCredito" method="post">
    	<input type="text" name="intestatarioCarta" placeholder="Intestatario" required/>
    	<input type="text" name="numeroCartaCarta" placeholder="Numero Carta" required/>
    	<input type="text" name="dataScadenzaCarta" placeholder="Data di scadenza (mm/aaaa)" required/>
    	<input type="text" name="cvvCarta" placeholder="CVV" required/>
    	
      <button type="submit">Conferma</button>
    </form>
  </div>
</div>
</body>
</html>