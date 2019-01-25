<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registrazione</title>
	</head>
	<body>
		<div>
			<form action="" method="" >
				<input type="text" class="inputForm" name="idUtente" placeholder="Nome Utente" id="idUtente">
				<input type="email" class="inputForm" name="email" placeholder="Email" id="email">
				<input type="password" class="inputForm" name="password" placeholder="Password" id="password">
				<input type="password" class="inputForm" name="confermaPassword" placeholder="Ripeti Password" id="confermaPassword">
				<input type="text" class="inputForm" name="nome" placeholder="Nome" id="nome">
				<input type="text" class="inputForm" name="cognome" placeholder="Cognome" id="cognome">
				<input type="radio" class="inputForm" name="sesso" value="M" id="sesso">M
				<input type="radio" class="inputForm" name="sesso" value="F" id="sesso">F
				<input type="date" class="inputForm" name="dataNascita" id="dataNascita">
				<input type="submit" value="Invia">
			</form>
		</div>	
	</body>
</html>