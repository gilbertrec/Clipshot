<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="Model.UtenteBean"%>
    <%@page import="Model.AbbonamentoBean"%>
<!DOCTYPE html>
<html>
  <head>
      <title></title>
      <link rel="stylesheet" type="text/css" href="css/styleModInfo.css">
      

      <!-- POPUP -->
      <link rel="stylesheet" type="text/css" href="css/popup.css">
      <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.css'>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
      <!-- FINE -->
  </head>
  <body>
  
  <%@include file="headerProfilo.jsp" %>
  <% UtenteBean utenteBean=(UtenteBean) session.getAttribute("utente"); %>
  <% AbbonamentoBean abbonamentoBean=(AbbonamentoBean) request.getAttribute("abbonamento"); %>
  <div class="form nscc">
      <h1>Dati Abbonamento</h1>
      <div class="datab">
  <% if(abbonamentoBean!=null){%>
    
        <div class="nome">
          <p>Nome:</p>
          <p class="space"><%=utenteBean.getNome() %></p>
        </div>
        <div class="cognome">
          <p>Cognome:</p>
          <p class="space"><%=utenteBean.getCognome() %></p>
        </div>
        <div class="numcart">
          <p>Numero Carta:</p>
          <p class="space">****-****-****-<%=abbonamentoBean.getNumeroCarta().substring(12,16) %></p>
        </div>
        <div class="data">
          <p>valido fino al</p>
          <p class="space"><%=abbonamentoBean.getStringDataScadenza() %></p>
        </div>
        <div class="butconf">
          <ul class="inline-popups">
            <a href="#test-popupda" data-effect="mfp-zoom-in" >
              <button>Disdici</button>
            </a>
          </ul>
        </div>
      
	<%}else{ %>
		 <div class="noabbonamento">
          <p>Non hai ancora effettuato l'abbonamento per diventare un'Artista!</p>
          <p>Inizia a vendere le tue foto grazie all'Account Artista!</p>
          <p class="space">Diventa ora un'artista</p>
          <form method="post" action="NuovoAbbonamento">
          	<button type="submit">Iscriviti</button>
          </form>
          
        </div>
	<% }%>
	   </div>	
    </div>
    <!-- Popup itself -->
     <div id="test-popupda" class="white-popup mfp-with-anim mfp-hide form">
        <div class="messaggio-c">
          <p>Sei sicuro di volere disdire l'abbonamento?</p>
        </div>
        <div class="butconf">
          <a href=""><button>Conferma</button></a>
          <a href=""><button>Annulla</button></a>
        </div>

       
    </div>


    <script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>
    <script  src="js/index.js"></script>
  <!-- FINE -->
  </body>
</html>