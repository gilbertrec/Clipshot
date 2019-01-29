<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Prova</title>
</head>
<body>
	<form action="operatore/RegistrazioneOperatore" method="post">
	Username:	<input type="text" name="username" >
	password: <input type="password" name="password">
	nome: <input type="text" name="nome">
	cognome: <input type="text" name="cognome">
	email: <input type="text" name="email">
	Tipo: <input type="radio" name="tipo" value="Amministratore">
	<input type="radio" name="tipo" value="Operatore">
	<input type="submit" value="Registra Operatore" name="registraOperatore">
	</form>
</body>
</html>