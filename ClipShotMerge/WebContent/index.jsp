<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import ="Model.PostBean" %>																																																																																																																																																	
<%@ page import ="java.util.ArrayList"%>	
<%@ page import ="Manager.FotoDAO" %>
<%@ page import ="Manager.LikeDAO" %>
 <%@page import ="Model.FotoBean" %> 																																																																																																																																																
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
<!--  function like(){
			var xhr= new XMLHttpRequest();
			xhr.open("get", "LikeAjax", true);
			xhr.send();
			xhr.onreadystatechange=function() {
				if(xhr.status=200&&xhr.readyState==4) {
					var r=xhr.responseText;
					//cambio il cuore
				}
			}
		}-->
		
<%@ include file="header.jsp"%>

<% ArrayList<PostBean> postList =(ArrayList<PostBean>)request.getAttribute("post_list"); %>																																																																																																																																																																							<%FotoDAO fotoDAO =new FotoDAO(); %>
<%LikeDAO likeDAO = new LikeDAO(); %>
  <div class="container">
  
  <% if(postList!=null) {
  for(int i=0;(i<10 && i<postList.size());i++){ %>
  <%LikeBean lb=likeDAO.doRetrieveByKey(u.getIdUtente(),postList.get(i).getIdPost(),postList.get(i).getIdUtente()); %>
		<div class="post-box">
  
      <!-- Page Heading -->
    
      <div class="foto-nome">
      	<div class="img-box">																																																																																																																																																																																													<%FotoBean fotoBean =fotoDAO.doRetrieveByKey(postList.get(i).getIdFoto()); %>
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
            <%if(true==true/*(postList.get(i).getIdUtente().equals(u.getIdUtente())*/){ %> 
            <!-- Servlet GetFoto();-->
        <a href="#test-popup2" data-effect="mfp-zoom-in"><button >Acquista a <%=fotoBean.getPrezzo() %> euro </button></a>
     	<input type="button" value="">
        <%} %>
      </ul>
        </div>
        <div class="interazione">
          <div class="segnalazione">
            <ul class="inline-popups">
              <a href="#test-popup1" data-effect="mfp-zoom-in"><img src="png/exclamation-mark.png" width="24px" height="24px"></a>
            </ul>
            
          </div>
          <div class="commento">
            <a href="#"><img src="png/chat.png" width="24px" height="24px"></a>
          </div>
          <div class="like">
          	
            <a href=""><img <%if(lb.getIdUtente()!=null) {%>src="png/heart.png" <%} else { %> src="png/heartw.png" <%} %>width="24px" height="24px"></a>
          </div>
        </div>
      </div>
      <!-- /.row -->
      <div id="test-popup2" class="white-popup mfp-with-anim mfp-hide form">
        <p>
          Sei sicuro di volere acquistare la foto?
        </p>
        <a href=""><button>Si</button></a>
        <a href=""><button>No</button></a>
      </div>
      <div id="test-popup1" class="white-popup mfp-with-anim mfp-hide form">
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
	</div>
    
    <script src='https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js'></script>
    <script  src="js/index.js"></script>
</body>
</html>