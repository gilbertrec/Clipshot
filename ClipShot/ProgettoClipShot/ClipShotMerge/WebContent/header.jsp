<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@page import="Model.UtenteBean"%>
 
<!DOCTYPE html>
<html>

<head>
  <title>
  </title>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.css'>
  <link href="css/sb-admin-2.css" rel="stylesheet">
  <link href="vendor/fontawesome-free/css/all.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">


  <link rel="stylesheet" type="text/css" href="css/popup.css">
  <link rel="stylesheet" type="text/css" href="css/styleForm.css">

              <!-- popup -->
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.css'>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>
<body>
 <% UtenteBean u=(UtenteBean)request.getSession().getAttribute("utente");%>
  <%if(u==null) {%>    	<!-- response.sendRedirect("index.jsp"); -->
    	<meta http-equiv="refresh" content="0;URL='login.jsp'">
    <%  } %>
  <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
          <div id="logo">
            
            <a href="HomePage"> <img class="logo" src="img/logo_ClipShot2.png"></a>
            
          </div>
 		  <div class="pubblica-post">
            <ul class="inline-popups">
              <a href="#test-popup" data-effect="mfp-zoom-in">Pubblica Post</a>
            </ul>
          </div>
		<div class="vendi-foto">
            <%if(u.getTipo().equalsIgnoreCase("Artista")){%>
            <ul class="inline-popups">
              <a href="#test-popupvf" data-effect="mfp-zoom-in">Vendi foto</a>
            </ul>
            <%} %>
          </div>

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>
          <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"  action="RicercaUtente">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" name="campoRicerca" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-primary" type="submit" >
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search" action="RicercaUtente" method="get">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>
            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%= u.getNome()%> <%= u.getCognome()%></span>

                <img class="img-profile rounded-circle" src="${pageContext.request.contextPath}/PostImageProxyController?user=<%=u.getIdUtente() %>">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
    
                	  <a class="dropdown-item" href="VisualizzaDati">
                 		 <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                 		 Profilo
               		 </a>
               	    <a class="dropdown-item" href="#">
                  	 <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                  	  Assistenza
               	    </a>
                	<div class="dropdown-divider"></div>
                		<a class="dropdown-item" href="Logout">
                  			<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                 		 	Logout
                		</a>
                
              </div>
            </li>

          </ul>

        </nav>

        <div id="test-popup" class="white-popup mfp-with-anim mfp-hide form">
          <form id="post" action="AggiungiPost" method="post" enctype="multipart/form-data">
            <p>PUBBLICA POST</p>
           <!-- prova <input type="file" name=""> -->
            <input type="file" name="fileFoto" >
            <textarea name="descrizionePost" placeholder="Descrizione..."></textarea>
            <button >Pubblica Post</button>
          </form>
        </div>
        <div id="test-popupvf" class="white-popup mfp-with-anim mfp-hide form">
          <form id="post" action="AggiungiPost" method="post" enctype="multipart/form-data">
            <p>VENDI FOTO</p>
           <!-- prova <input type="file" name=""> -->
            <input type="file" name="fileFoto" >
            <textarea name="descrizionePost" placeholder="Descrizione..."></textarea>
            	<input type="number" name="prezzoFoto" placeholder="prezzo"  min="0.00" max="10000.00" step="0.01">
            
            <button >Pubblica Post</button>
          </form>
        </div>
        <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>



 <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>

  

    <script  src="js/index.js"></script>


</body>
</html>