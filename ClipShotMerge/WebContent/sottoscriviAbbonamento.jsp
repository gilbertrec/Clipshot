<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@page import="Model.UtenteBean"%>
<%@page import="Model.CartaDiCreditoBean"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ClipShot - Sottoscrivi Abbonamento</title>
</head>
 <body>
 
  <%@include file="headerProfilo.jsp" %>
    <div class="login-page">
    	<h1>Sottoscrivi Abbonamento</h1>
      <div class="form">
        <form class="login-form">
        	<input type="text" placeholder="Nome" value="<%=u.getNome()%>"/>
        	<input type="text" placeholder="Cognome" value=<%=u.getCognome()%>/>
        	<input type="text" placeholder="Email" value="<%=u.getEmail()%>"/>
          <ul class="inline-popups">
            <a href="#test-popupca" data-effect="mfp-zoom-in">
              <button>Conferma</button>
            </a>
          </ul>
        </form>
      </div>

    </div>
    <!-- Popup itself -->
     <div id="test-popupca" class="white-popup mfp-with-anim mfp-hide form">
        <div class="messaggio-c">
          <p>Sei sicuro di volere sottocrivere l'abbonamento?</p>
          <p class="gcic">(Costo mensile di 5,99â‚¬)</p>
        </div>
        <div class="butconf">
          <a href=""><button>Conferma</button></a>
          <a href=""><button>Annulla</button></a>
        </div>

       
    </div>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>
    <script  src="js/index.js"></script>
  <!-- FINE -->
  </body>
</html>