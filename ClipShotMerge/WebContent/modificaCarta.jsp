<!DOCTYPE html>
<html>
<head>
	<%@page import="Model.CartaDiCreditoBean"%>
    <title>ClipShot - Modifica Carta</title>
    <link rel="stylesheet" type="text/css" href="css/styleModInfo.css">
</head>
<body>
<%CartaDiCreditoBean c_request=(CartaDiCreditoBean) request.getAttribute("cartaDiCreditoBean");%>
<div class="login-page">
	<h1>Modifica Carta di Credito</h1>
  <div class="form">
    <form class="login-form" action="ModificaCartaDiCredito" method="post">
    	<input type="text" placeholder="Intestatario" name="intestatarioCarta" value="<%=c_request.getIntestatario()%>"/>
    	<input type="text" placeholder="Numero Carta" name="numeroCartaCarta" value="<%=c_request.getNumeroCarta()%>"/>
    	<input type="text" placeholder="Data di scadenza (mm/aaaa)" name="dataScadenzaCarta" value="<%=c_request.getStringDataScadenza().substring(0,6)%>"/>
    	<input type="text" placeholder=" CVV" name="cvvCarta" value="<%=c_request.getCvv() %>"/>
      <button>Modifica</button>
    </form>
  </div>
</div>
</body>
</html>