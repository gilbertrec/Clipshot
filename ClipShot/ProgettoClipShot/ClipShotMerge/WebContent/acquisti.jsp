<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ page import ="java.util.ArrayList"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page import ="Manager.FotoDAO" %>
<%@page import ="Model.FotoBean" %>
<%@page import ="Manager.AcquistiDAO" %>
<%@page import ="Model.AcquistiBean" %> 
<title>ClipShot -Acquisti</title>
</head>
<body>

<%@ include file="header.jsp"%>

<%FotoDAO fotoDAO= new FotoDAO(); %>
<% ArrayList<AcquistiBean> acquistiList =(ArrayList<AcquistiBean>)request.getAttribute("acquisti"); %>
<div class="container">
		<h1>ALBUM</h1>
	<%for (int i=0;i<acquistiList.size();i++) {%>
		<%FotoBean fotoBean=fotoDAO.doRetrieveByKey(acquistiList.get(i).getIdFoto()); %>
		<div class="foto">
			<div class="img-foto">
				<img src="${pageContext.request.contextPath}/FotoProxyController?foto=<%=acquistiList.get(i).getIdFoto() %>" width="256px" height="256px">
			</div>
				<div class="dettagli">
					<div class="prezzo"><%=fotoBean.getPrezzo() %> euro</div>
					<div class="data"><%=acquistiList.get(i).getStringData() %></div>
					<div class="download">
					<a href="${pageContext.request.contextPath}/photopost/<%=fotoBean.getPath()%>">
						<img src="png/cloud-computing.png" width="24px" height="24px">
					</a>
				</div>
			</div>
			
		</div>

	<%} %>
	</div>	
</body>
</html>