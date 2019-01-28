CREATE DATABASE clipshot;
USE clipshot;

CREATE TABLE utente(
	idUtente VARCHAR(32) PRIMARY KEY,
    password VARCHAR(32) NOT NULL,
    email VARCHAR(32) UNIQUE NOT NULL,
    nome VARCHAR(32) NOT NULL,
    cognome VARCHAR(32) NOT NULL,
    dataNascita DATE NOT NULL,
    sesso ENUM('M', 'F') NOT NULL,
    indirizzo VARCHAR(32),
    stato ENUM('BLOCKED', 'FREE') NOT NULL,
    tipo ENUM('BASE', 'ARTISTA') NOT NULL,
    fotoProfilo VARCHAR(255) );
    
CREATE TABLE statistiche(
	idUtente VARCHAR(32) PRIMARY KEY,
    numeroVisualizzazioni INT(11),
    FOREIGN KEY (idUtente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE segui(
	idFollower VARCHAR(32),
    idFollowing VARCHAR(32),
    PRIMARY KEY(idFollower, idFollowing),
    FOREIGN KEY (idFollower) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idFollowing) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE messaggio(
	idMittente VARCHAR(32), 
    idDestinatario VARCHAR(32),
    dataMessaggio DATE,
    ora TIME,
    corpo VARCHAR(255) NOT NULL,
    PRIMARY KEY (idMittente, idDestinatario, dataMessaggio, ora),
    FOREIGN KEY (idMittente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idDestinatario) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE cartadiCredito(
	numeroCarta INT(16) PRIMARY KEY,
    idUtente VARCHAR(32) NOT NULL,
    intestatario VARCHAR(64) NOT NULL,
    dataScadenza DATE NOT NULL,
    cvv INT(3) NOT NULL,
    FOREIGN KEY (idUtente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE abbonamento(
	idUtente VARCHAR(32) PRIMARY KEY,
    dataScadenza DATE NOT NULL,
    numeroCarta INT(16) NOT NULL,
    stato ENUM('ATTIVO', 'SOSPESO'),
    FOREIGN KEY (idUtente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (numeroCarta) REFERENCES cartadiCredito(numeroCarta) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE operatore(
	username VARCHAR(32) PRIMARY KEY,
    password VARCHAR(24) NOT NULL,
    nome VARCHAR(24) NOT NULL,
    cognome VARCHAR(24) NOT NULL,
    email VARCHAR(40) NOT NULL,
    tipo ENUM('AMMINISTRATORE', 'OPERATORE') NOT NULL );
    
CREATE TABLE messaggioAssistenza(
	idUtente VARCHAR(32),
	idOperatore VARCHAR(32),
    dataMessaggio DATE,
    ora TIME,
    oggetto VARCHAR(40) NOT NULL,
    corpo VARCHAR(255) NOT NULL,
    tipo ENUM('RICHIESTA', 'RISPOSTA'),
    PRIMARY KEY (idUtente, idOperatore, dataMessaggio, ora),
    FOREIGN KEY (idUtente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idOperatore) REFERENCES operatore(username) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE foto(
	idFoto INT(11) PRIMARY KEY,
    path VARCHAR(255) NOT NULL,
    prezzo DECIMAL(5,2) );
    
CREATE TABLE acquisto(
	idUtente VARCHAR(32),
    idFoto INT(11), 
    data DATE NOT NULL, 
    PRIMARY KEY (idUtente, idFoto),
    FOREIGN KEY (idUtente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idFoto) REFERENCES foto(idFoto) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE post(
	idPost INT(11),
    idUtente VARCHAR(32),
    idFoto INT(11) NOT NULL,
    descrizione VARCHAR(255),
    data DATE NOT NULL,
    ora TIME NOT NULL,
    stato ENUM('BLOCKED', 'FREE') NOT NULL,
    PRIMARY KEY (idPost, idUtente),
    FOREIGN KEY (idUtente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idFoto) REFERENCES foto(idFoto) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE likeP(
	idUtente VARCHAR(32),
    idPost INT(11),
    idUtentePost VARCHAR(32),
    PRIMARY KEY(idUtente, idPost, idUtentePost),
    FOREIGN KEY (idUtente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idPost, idUtentePost) REFERENCES post(idPost, idUtente) ON DELETE CASCADE ON UPDATE CASCADE );
    
CREATE TABLE commento(
	idUtente VARCHAR(32),
    idPost INT(11),
    idUtentePost VARCHAR(32),
    data DATE,
    ora TIME,
    descrizone VARCHAR(255) NOT NULL,
    PRIMARY KEY (idUtente, idPost, idUtentePost, data, ora),
    FOREIGN KEY (idUtente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idPost, idUtentePost) REFERENCES post(idPost, idUtente) ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE segnalazione(
	idSegnalazione INT(11),
    idUtente VARCHAR(32),
    idPost INT(11) NOT NULL,
    idUtentePost VARCHAR(32) NOT NULL,
    causa VARCHAR(32) NOT NULL,
    stato ENUM('completata', 'in_attesa'),
    data DATE NOT NULL,
    descrizione VARCHAR(255),
    PRIMARY KEY (idSegnalazione, idUtente),
    FOREIGN KEY (idUtente) REFERENCES utente(idUtente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idPost, idUtentePost) REFERENCES post(idPost, idUtente) ON DELETE CASCADE ON UPDATE CASCADE );
    
   SHOW TABLES;