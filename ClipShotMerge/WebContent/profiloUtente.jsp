<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@page import="Model.UtenteBean"%>
     <%@ page import ="Model.PostBean" %>
	 <%@ page import ="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ClipShot -Mio Profilo</title>
 <link rel="stylesheet" type="text/css" href="css/stylePost.css">
  <link rel="stylesheet" type="text/css" href="css/styleForm.css">

  <!-- POPUP -->
    <link rel="stylesheet" type="text/css" href="css/popup.css">
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.css'>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

  </head>

  <body>
  <%@include file="headerProfilo.jsp" %>
  
 <% UtenteBean uprofilo=(UtenteBean)request.getAttribute("utenteBean");%>


<% ArrayList<PostBean> postList =(ArrayList<PostBean>)request.getAttribute("post_list"); %>

 <div class="container">
    <div class="mp">
      <div class="foto">
        <img src="${pageContext.request.contextPath}/ImageProxyController?name=<%=uprofilo.getFotoProfilo() %>" width="256px" height="256px">
      </div>
      <div class="nome"><%=uprofilo.getNome() %> <%=uprofilo.getCognome() %></div>
      <div class="segui">
        <a href="">
          <img src="png/heartw.png" width="64px" height="64px">
        </a>
      </div>
      <div class="messaggio">
        <a href="">
          <img src="png/envelope.png" width="64px" height="64px">
        </a>
      </div>  
   </div>
    

      <!-- Page Heading -->
     
      <%  for(int i=0;(i<10&&i<postList.size());i++){ %>
      <!-- Project One -->
      <div class="post-box">
      	<div class="foto-nome">
        	<div class="img-box">
          <img class="img-profile" src="${pageContext.request.contextPath}/ImageProxyController?name=<%=uprofilo.getFotoProfilo() %>" width="64px" height="64px">
     	 </div>
        <div class="nome-profilo">
          <p class="mp"><%=uprofilo.getIdUtente()%></p>
        </div>  
      
      </div> 
      <div class="row">
        
        <div class="col-md-7">
          <a href="#">
            <img class="img-fluid rounded mb-3 mb-md-0" src="${pageContext.request.contextPath}/FotoProxyController?foto=<%=postList.get(i).getIdFoto()%>" width="400px" height="200px">
          </a>
        </div>
        <div class="col-md-5">
          <p><%=postList.get(i).getDescrizione()%></p>
        </div>
        <div class="interazione">
          <div class="delete-post">
            <ul class="inline-popups">
              <a href="#test-popup10" data-effect="mfp-zoom-in"><img src="png/cancel.png" width="24px" height="24px"></a>
            </ul>
            
          </div>
          <div class="commento">
            <a href="#"><img src="png/chat.png" width="24px" height="24px"></a>
          </div>
          <div class="like">
            <a href="#"><img src="png/heartw.png" width="24px" height="24px"></a>
          </div>
        </div>
      </div>
      <!-- /.row -->
      
    
  </div>
  <div id="test-popup10" class="white-popup mfp-with-anim mfp-hide">
        <p>
          Sei sicuro di volere eliminare la foto?
        </p>
        <a href=""><button>Si</button></a>
        <a href=""><button>No</button></a>
  </div>
 </div>
    <!-- /.container -->
<%} %>
    <!-- Footer -->
 

    <!-- Bootstrap core JavaScript -->
    <script src="vendor2/jquery/jquery.min.js"></script>
    <script src="vendor2/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>

  

    <script  src="js/index.js"></script>

  </body>

</html>
