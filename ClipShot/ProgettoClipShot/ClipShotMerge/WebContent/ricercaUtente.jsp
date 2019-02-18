<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@page import="Model.UtenteBean"%>
    <%@ page import ="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
  <title>ClipShot - Ricerca Utente</title>
  <link rel="stylesheet" type="text/css" href="css/styleRicerca.css">
</head>
<body>
<%@ include file="header.jsp" %>
<%ArrayList <UtenteBean> u_request=(ArrayList <UtenteBean>)request.getAttribute("utente_list"); 
 for (int i=0; i<u_request.size();i++){   %>
	<div class="mp">
      <div class="foto">
        <a href="VisualizzaProfilo?idUtente=<%=u_request.get(i).getIdUtente()%>">
          <img src="${pageContext.request.contextPath}/PostImageProxyController?user=<%=u_request.get(i).getIdUtente() %>" width="128px" height="128px">
        </a>
        
      </div>
      <div class="nome">
        <a href="VisualizzaProfilo?idUtente=<%=u_request.get(i).getIdUtente()%>">
          <p><%=u_request.get(i).getNome() %> <%=u_request.get(i).getCognome() %></p>
        </a> 
      </div>
   </div>
  <%}%>
</body>
</html>