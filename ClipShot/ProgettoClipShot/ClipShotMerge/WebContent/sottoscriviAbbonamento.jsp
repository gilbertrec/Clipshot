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
  <%UtenteBean u_request=(UtenteBean)request.getAttribute("utente"); %>
    <div class="login-page">
    	<h1>Sottoscrivi Abbonamento</h1>
      <div class="form">
        <form class="login-form" method="post" action="SottoscrizioneAbbonamento" >
        	<input type="text" placeholder="Nome" value="<%=u_request.getNome()%>" readonly/>
        	<input type="text" placeholder="Cognome" value=<%=u_request.getCognome()%> readonly/>
        	<input type="text" placeholder="Email" value="<%=u_request.getEmail()%>" readonly/>
        	<input type="text" placeholder="Carta di Credito" value="****-****-****-<%=((String)request.getAttribute("numeroCarta")).substring(12,16)%>" readonly/>
        	<p>Il seguente abbonamento sarà effettuato sul profilo:<%=u_request.getIdUtente() %></p>
        	<p>L'abbonamento avrà un costo di 5,99€, applicato sulla Carta di Credito inserita sul tuo Profilo.</p>
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
        <form method="post" action="SottoscrizioneAbbonamento">
        	<div class="butconf">
         	 <a ><button>Conferma</button></a>
        	 <a href="NuovoAbbonamento"><button>Annulla</button></a>
        	</div>
        </form>

       
    </div>

    <script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>
    <script  src="js/index.js"></script>
  <!-- FINE -->
  </body>
</html>