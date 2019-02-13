<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@page import="Model.UtenteBean"%>
    <%@page import="Model.CartaDiCreditoBean"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	

	<!--  va in conflitto
	<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
	-->
	
	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
	<link rel="stylesheet" type="text/css" href="css/styleInfo.css">
</head>
<body>


<%@include file="headerProfilo.jsp" %>

<%UtenteBean u_request=(UtenteBean) request.getAttribute("utenteBean"); %>
<%CartaDiCreditoBean c_request=(CartaDiCreditoBean) request.getAttribute("cartaDiCreditoBean"); %>
<div id="user-profile-2" class="user-profile">
		<div class="tabbable">

			<div class="tab-content no-border padding-24">
				<div id="home" class="tab-pane in active">
					<div class="row">
						<div class="col-xs-12 col-sm-3 center">
							<span class="profile-picture">
								<img class="editable img-responsive" alt=" Avatar" id="avatar2" src="${pageContext.request.contextPath}/ImageProxyController?name=<%=u_request.getFotoProfilo() %>">
							</span>

							<div class="space space-4"></div>
							<form action="VisualizzaAbbonamento" method="post">
								<button >ABBONAMENTO</button>
							</form>
							<ul class="inline-popups">
        						<a href="#test-popup" data-effect="mfp-zoom-in"><button>ELIMINA ACCOUNT</button></a>
      						</ul>
							
						</div><!-- /.col -->

						<div class="col-xs-12 col-sm-9">
							<h4 class="blue">
								<span class="middle">INFO</span>
							</h4>

							<div class="profile-user-info">
								<div class="profile-info-row">
									<div class="profile-info-name">Nome</div>

									<div class="profile-info-value">
										<span><%=u_request.getNome() %></span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Cognome</div>

									<div class="profile-info-value">
										<span><%=u_request.getCognome() %></span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Email</div>

									<div class="profile-info-value">
										<span><%=u_request.getEmail() %></span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> Professione</div>

									<div class="profile-info-value">
										<span>Fotografo</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Indirizzo</div>

									<div class="profile-info-value">
										<span><%=u_request.getIndirizzo() %></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">Sesso</div>

									<div class="profile-info-value">
										<span><%=u_request.getSesso() %></span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Data di Nascita</div>

									<div class="profile-info-value">
										<span><%=u_request.getStringData() %></span>
									</div>
								</div>
								<div class="modifica">
      								<form action="VisualizzaDati" method="post">
      	
      									<input type="hidden" name="mode" value="edit">
          								<input type="submit" value="Modifica">
      								</form>
								</div>	
								
								<h4 class="blue">
								<span class="middle">DATI DI PAGAMENTO</span>
							</h4>
						<%if (c_request.getNumeroCarta()!=null){ %>
							<div class="profile-user-info">
								<div class="profile-info-row">
									<div class="profile-info-name">Titolare</div>

									<div class="profile-info-value">
										<span><%=c_request.getIntestatario()%></span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Numero Carta</div>

									<div class="profile-info-value">
										<span>****-****-****-<%=c_request.getNumeroCarta()%></span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Scadenza</div>

									<div class="profile-info-value">
										<span><%=c_request.getStringDataScadenza()%></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">CVV</div>

									<div class="profile-info-value">
										<span>***</span>
									</div>
								</div>
								<a href="VisualizzaCartaDiCredito">Modifica</a>
							</div>
							<%} else{
									%>
									<div class="profile-user-info">
										<div class="profile-info-row">
											<span> Non hai ancora inserito una Carta di Credito!</span>
											<a href="aggiungiCarta.jsp"> Inserisci una Carta</a>
										</div>
									</div>
								<%} %>
						</div><!-- /.col -->
					</div><!-- /.row -->

					<div class="space-20"></div>

					
				</div><!-- /#home -->

				
			</div>
		</div>
	</div>
	<div id="test-popup" class="white-popup mfp-with-anim mfp-hide">
        <p>
          Sei sicuro di volere eliminare l'account?
        </p>
        <a href=""><button>Si</button></a>
        <a href=""><button>No</button></a>
      </div>
	<!-- POP UP -->
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>
	<script  src="js/index.js"></script>
</body>
</html>