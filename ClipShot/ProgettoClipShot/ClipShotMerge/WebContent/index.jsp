<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import ="Model.PostBean" %>																																																																																																																																																	
<%@ page import ="java.util.ArrayList"%>	
<%@ page import ="Manager.FotoDAO" %>
<%@ page import ="Manager.LikeDAO" %>
 <%@page import ="Model.FotoBean" %> 	
 <%@page import ="Model.LikeBean" %> 	
 <%@page import ="Manager.AcquistiDAO" %>
 <%@page import ="Model.AcquistiBean" %>  																																																																																																																																														
<!DOCTYPE html >
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ClipShot -HomePage</title>

   <link rel="stylesheet" type="text/css" href="css/stylePost.css">
	<link rel="stylesheet" type="text/css" href="css/styleForm.css">

	<!-- POPUP -->
    <link rel="stylesheet" type="text/css" href="css/popup.css">
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.css'>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>


  <!--  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.css'>
	-->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>HomePage ClipShot</title>
</head>
<body>

<%@ include file="header.jsp"%>

<% ArrayList<PostBean> postList =(ArrayList<PostBean>)request.getAttribute("post_list"); %>																																																																																																																																																																							<%FotoDAO fotoDAO =new FotoDAO(); %>
<%LikeDAO likeDAO = new LikeDAO(); %>

<%AcquistiDAO acquistoDAO =new AcquistiDAO(); %>
  <div class="container">
  
  <% if(postList!=null) {
  	for(int i=0;(i<10 && i<postList.size());i++){ %>
  
  
     <%FotoBean fotoBean =fotoDAO.doRetrieveByKey(postList.get(i).getIdFoto()); %>
  <%LikeBean lb=likeDAO.doRetrieveByKey(u.getIdUtente(),postList.get(i).getIdPost(),postList.get(i).getIdUtente()); %>
		<%AcquistiBean ab=acquistoDAO.doRetrieveByKey(u.getIdUtente(),fotoBean.getIdFoto()); %>
		<div class="post-box">
    
      <div class="foto-nome">
      	<div class="img-box">																																																																																																																																																																																												
        	<img class="img-profile" src="${pageContext.request.contextPath}/PostImageProxyController?user=<%=postList.get(i).getIdUtente() %>" width="64px" height="64px">
    	</div>
        <div class="nome-profilo">
        	<p class="mp"><%= postList.get(i).getIdUtente() %></p>
        </div>	
      
      </div>  
      
      <!-- Project One -->
      <div class="row">
        
        <div class="img-post-box">
          <a href="#">
            <img class="img-fluid rounded mb-3 mb-md-0" src="${pageContext.request.contextPath}/FotoProxyController?foto=<%=postList.get(i).getIdFoto() %>" width="400px" height="200px">
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
              <a href="#test-popupseg<%=i %>" data-effect="mfp-zoom-in"><img src="png/exclamation-mark.png" width="24px" height="24px"></a>
            </ul>
            
          </div>
          <div class="commento">
            <a href="#"><img src="png/chat.png" width="24px" height="24px"></a>
          </div>
          <div class="like">
          	
            <a ><img id="like<%=i%>" onclick="like('<%=u.getIdUtente()%>', '<%=postList.get(i).getIdPost()%>','<%=postList.get(i).getIdUtente()%>','<%=i%>')" 
            <%if(lb.getIdUtente()!=null) {%>src="png/heart.png" <%} else { %> src="png/heartw.png" <%} %>width="24px" height="24px"></a>
          </div>
        </div>
      </div>
      <!-- /.row -->
      <div id="test-popupacq<%=i %>" class="white-popup mfp-with-anim mfp-hide form">
        <p>
          Sei sicuro di volere acquistare la foto?
        </p>
        <a href="" onclick="acquisto('<%=u.getIdUtente()%>','<%=fotoBean.getIdFoto()%>','<%=i%>')" ><button>Si</button></a>
        <a href=""><button>No</button></a>
      </div>
      <div id="test-popupseg<%=i%> %>" class="white-popup mfp-with-anim mfp-hide form">
          <form id="segnala">
            <p>SEGNALA</p>
            <div id="dati_segnala"><p id="dati_idDestinatario">munneza</p><p id="dati_idMittente">emilio</p></div>
            <select>
              <option value="Violenza">Violenza</option>
              <option value="Razzismo">Razzismo</option>
              <option value="Pornografia">Pornografia</option>
            </select>
            <textarea name="" placeholder="Corpo..."></textarea>
            <button >Invia</button>
          </form>
        </div>
      </div>
		<%}
	}
		%>
	<%if(postList.size()==0){%>
		<p> Questa è la HomePage dove verrano visualizzati i post che metterai tu e le persone che segui. Cosa aspetti? Pubblica un Post Ora!</p>
	<%}%>
	</div>

	<script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>
    <script  src="js/index.js"></script>
     <script  src="js/like.js"></script>
      <script  src="js/acquisto.js"></script>
</body>
</html>