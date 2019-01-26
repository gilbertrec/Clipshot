CREATE DATABASE clipshot;
USE clipshot;

CREATE TABLE utente(
	username VARCHAR(32) PRIMARY KEY,
    passw VARCHAR(32) NOT NULL,
    email VARCHAR(32) UNIQUE NOT NULL,
    nome VARCHAR(32) NOT NULL,
    cognome VARCHAR(32) NOT NULL,
    dataNascita DATE NOT NULL,
    sesso ENUM('M', 'F') NOT NULL,
    indirizzo VARCHAR(255),
    stato ENUM('BLOCKED', 'FREE') NOT NULL,
    tipo ENUM('BASE', 'ARTISTA') NOT NULL,
    fotoProfilo VARCHAR(255) );
    
CREATE TABLE statistiche(
	username VARCHAR(32) PRIMARY KEY,
    numeroVisualizzazioni INT(11),
    FOREIGN KEY (username) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE segui(
	userSeguente VARCHAR(32),
    userSeguito VARCHAR(32),
    PRIMARY KEY(userSeguente, userSeguito),
    FOREIGN KEY (userSeguente) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (userSeguito) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE messaggio(
	userMittente VARCHAR(32), 
    userDestinatario VARCHAR(32),
    dataMessaggio DATE,
    ora TIME,
    corpo VARCHAR(255) NOT NULL,
    PRIMARY KEY (userMittente, userDestinatario, dataMessaggio, ora),
    FOREIGN KEY (userMittente) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (userDestinatario) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE cartadiCredito(
	numeroCarta INT(16),
    username VARCHAR(32),
    intestatario VARCHAR(64) NOT NULL,
    dataScadenza DATE NOT NULL,
    cvv INT(3) NOT NULL,
    PRIMARY KEY (numeroCarta, username),
    FOREIGN KEY (username) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE abbonamento(
	idAbbonamento VARCHAR(32) PRIMARY KEY,
    dataScadenza DATE NOT NULL,
    stato ENUM('ATTIVO', 'SOSPESO') NOT NULL,
    numeroCarta INT(16) NOT NULL,
    FOREIGN KEY (idAbbonamento) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (numeroCarta, idAbbonamento) REFERENCES cartadiCredito(numeroCarta, username) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE operatore(
	username VARCHAR(32) PRIMARY KEY,
    passw VARCHAR(24) NOT NULL,
    nome VARCHAR(24) NOT NULL,
    cognome VARCHAR(24) NOT NULL,
    email VARCHAR(40) NOT NULL,
    tipo ENUM('AMMINISTRATORE', 'OPERATORE') NOT NULL );
    
CREATE TABLE messaggioAssistenza(
	userMittente VARCHAR(32),
	userDestinatario VARCHAR(32),
    dataMessaggio DATE,
    ora TIME,
    corpo VARCHAR(255) NOT NULL,
    oggetto VARCHAR(40) NOT NULL,
    tipo ENUM('RICHIESTA', 'RISPOSTA'),
    PRIMARY KEY (userMittente, userDestinatario, dataMessaggio, ora),
    FOREIGN KEY (userMittente) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (userDestinatario) REFERENCES operatore(username) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE foto(
	idFoto INT(11) PRIMARY KEY,
    path VARCHAR(255) NOT NULL,
    prezzo DECIMAL(5,2) );
    
CREATE TABLE acquisto(
	username VARCHAR(32),
    idFoto INT(11), 
    dataAcquisto DATE NOT NULL, 
    PRIMARY KEY (username, idFoto),
    FOREIGN KEY (username) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idFoto) REFERENCES foto(idFoto) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE post(
	idPost INT AUTO_INCREMENT,
    username VARCHAR(32),
    idFoto INT(11) NOT NULL,
    descrizione VARCHAR(255),
    dataPubblicazione DATE NOT NULL,
    stato ENUM('BLOCCATO', 'SBLOCCATO') NOT NULL,
    PRIMARY KEY (idPost, username),
    FOREIGN KEY (username) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idFoto) REFERENCES foto(idFoto) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE likeP(
	username VARCHAR(32),
    idPost INT(11), 
    PRIMARY KEY(username, idPost),
    FOREIGN KEY (username) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idPost) REFERENCES post(idPost) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE commento(
	username VARCHAR(32),
    idPost INT(11),
    userPost VARCHAR(32) NOT NULL,
    descrizone VARCHAR(255) NOT NULL,
    dataCommento DATE NOT NULL,
    PRIMARY KEY (username, idPost),
    FOREIGN KEY (username) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idPost, userPost) REFERENCES post(idPost, username) ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE segnalazione(
	idSegnalazione INT AUTO_INCREMENT,
    username VARCHAR(32),
    idPost INT(11) NOT NULL,
    userPost VARCHAR(32) NOT NULL,
    causa VARCHAR(32) NOT NULL,
    stato ENUM('completata', 'attesa'),
    dataSegnalazione DATE NOT NULL,
    descrizione VARCHAR(255),
    PRIMARY KEY (idSegnalazione, username),
    FOREIGN KEY (username) REFERENCES utente(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idPost, userPost) REFERENCES post(idPost, username) ON DELETE CASCADE ON UPDATE CASCADE );
    
   SHOW TABLES;