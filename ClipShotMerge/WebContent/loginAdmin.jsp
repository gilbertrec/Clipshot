<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ModelAdmin.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="css\styleadmin.css">



</head>
<body>
<div align="center">
<form action="loginAdmin" method="post" class="w3-panel w3-card w3-light-grey" id="table">
	<h3>Login Admin</h3>
	<br><input type="text" name="usr" placeholder="Username Admin" class="input"><br>
	<br><input type="password" name="psw" placeholder="Password Admin" class="input"><br><br>
	<input type="submit" class="inputsubmit"><br><br>
</form>
</div>
</body>
</html>