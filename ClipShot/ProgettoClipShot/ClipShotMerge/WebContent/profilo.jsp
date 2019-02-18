<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@page import="Model.UtenteBean"%>
 <%@ page import ="Model.PostBean" %>
 <%@ page import ="java.util.ArrayList"%>
<%@ page import ="Manager.FotoDAO" %>
<%@ page import ="Manager.SeguiDAO" %>
<%@ page import="Manager.LikeDAO" %>
  <%@page import ="Manager.AcquistiDAO" %>  
 <%@page import ="Model.SeguiBean" %> 
 <%@page import ="Model.FotoBean" %> 	
 <%@page import ="Model.LikeBean" %>
  <%@page import ="Model.AcquistiBean" %>  	
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
  
 <% UtenteBean uprofilo=(UtenteBean)request.getAttribute("utenteBean");%>
    <title>ClipShot -<%=uprofilo.getIdUtente() %></title>
 <link rel="stylesheet" type="text/css" href="css/stylePost.css">
  <link rel="stylesheet" type="text/css" href="css/styleForm.css">

  <!-- POPUP -->
    <link rel="stylesheet" type="text/css" href="css/popup.css">
  	 <link rel="stylesheet" type="text/css" href="css/styleProfiloUtente.css">
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.css'>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

  </head>

  <body>
  <%@include file="header.jsp" %>
<%LikeDAO likeDAO = new LikeDAO(); %>
<%SeguiDAO seguiDAO =new SeguiDAO(); %>
<%FotoDAO fotoDAO =new FotoDAO(); %>
<%AcquistiDAO acquistoDAO =new AcquistiDAO(); %>
<% ArrayList<PostBean> postList =(ArrayList<PostBean>)request.getAttribute("post_list"); %>
<% SeguiBean sb =seguiDAO.doRetrieveByKey(u.getIdUtente(),uprofilo.getIdUtente()) ;%>


 <div class="container">
    <div class="mp">
      <div class="foto">
        <img src="${pageContext.request.contextPath}/ImageProxyController?name=<%=uprofilo.getFotoProfilo() %>" width="256px" height="256px">
      </div>
      <div class="nome"><%=uprofilo.getNome() %> <%=uprofilo.getCognome() %></div>
      <div class="segui">
        <a >
          <img id="follow_img" onclick="follow('<%=u.getIdUtente() %>','<%=uprofilo.getIdUtente() %>')" <%if(sb.getIdFollower()!=null) {%>src="png/heart.png" <%}  else{ %> src="png/heartw.png" <%} %>width="64px" height="64px">
        </a>
      </div>
      <div class="messaggio">
        <a href="">
          <img src="png/envelope.png" width="64px" height="64px">
        </a>
      </div>  
   </div>
    

      <!-- Page Heading -->
     
  <%if(postList!=null) {  
      for(int i=0;(i<10&&i<postList.size());i++){ %>
      
      <%FotoBean fotoBean =fotoDAO.doRetrieveByKey(postList.get(i).getIdFoto()); %>
       <%LikeBean lb=likeDAO.doRetrieveByKey(u.getIdUtente(),postList.get(i).getIdPost(),postList.get(i).getIdUtente()); %>
       <%AcquistiBean ab=acquistoDAO.doRetrieveByKey(u.getIdUtente(),fotoBean.getIdFoto()); %>
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
        
        <div class="img-post-box">
          <a href="#">
            <img class="img-fluid rounded mb-3 mb-md-0" src="${pageContext.request.contextPath}/FotoProxyController?foto=<%=postList.get(i).getIdFoto()%>" width="400px" height="200px">
          </a>
        </div>
        <div class="des-post-box">
          <p><%=postList.get(i).getDescrizione()%></p>
          <ul class="inline-popups">
           <%if(!postList.get(i).getIdUtente().equals(u.getIdUtente())&&(fotoBean.getPrezzo()!=0)){ %> 
            <!-- Servlet GetFoto();-->
 				<%if(ab.getIdUtente()==null){ %> 
      			  <a href="#test-popupacq<%=i %>" data-effect="mfp-zoom-in" id="acquisto<%=i%>"><button>Acquista a <%=fotoBean.getPrezzo() %> euro </button></a>
       			 <%} else{ %>
       					<p> Foto già Acquistata</p>
       			 <%} %>
       		<%} %>
        </ul>
        </div>
        <div class="interazione">
          <div class="segnalazione">
            <ul class="inline-popups">
              <a href="#test-popup51" data-effect="mfp-zoom-in"><img src="png/exclamation-mark.png" width="24px" height="24px"></a>
            </ul>
          <div class="commento">
            <a href="#"><img src="png/chat.png" width="24px" height="24px"></a>
          </div>
          <div class="like">
            <a>
             <a ><img id="like<%=i%>" onclick="like('<%=u.getIdUtente()%>', '<%=postList.get(i).getIdPost()%>','<%=postList.get(i).getIdUtente()%>','<%=i%>')" 
            <%if(lb.getIdUtente()!=null) {%>src="png/heart.png" <%} else { %> src="png/heartw.png" <%} %>width="24px" height="24px"></a>
            </a>
          </div>
        </div>
      </div>
      <!-- /.row -->
      
    
  </div>
  <div id="test-popup50" class="white-popup mfp-with-anim mfp-hide">
        <p>
          Sei sicuro di volere eliminare la foto?
        </p>
        <a href=""><button>Si</button></a>
        <a href=""><button>No</button></a>
  </div>
  
  
  
  <div id="test-popupacq<%=i %>" name="acquisto<%=i %>" class="white-popup mfp-with-anim mfp-hide form">
        <p>
          Sei sicuro di volere acquistare la foto?
        </p>
        <a ><button onclick="acquisto('<%=u.getIdUtente()%>','<%=fotoBean.getIdFoto()%>','<%=i%>')">Si</button></a>
        <a href=""><button>No</button></a>
      </div>
  <%	} 
    }%>
  
  
 </div>
    <!-- /.container -->

    <!-- Footer -->
 

    <!-- Bootstrap core JavaScript -->
    <script src="vendor2/jquery/jquery.min.js"></script>
    <script src="vendor2/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>

  

    <script  src="js/index.js"></script>
        <script  src="js/acquisto.js"></script>
    
    <script  src="js/like.js"></script>
    
    <script  src="js/follow.js"></script>

  </body>

</html>>