package Model;

import java.sql.Date;

public class UtenteBean 
{
	private String idUtente;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String sesso;
	private String indirizzo;
	private String professione;
	private String pathFotoProfilo;
	private String stato;
	private String tipo;
	private String fotoProfilo;
	
	public UtenteBean() {
		
	}
	
	public UtenteBean(String idUtente, String email, String password, String nome, String cognome, Date dataNascita, String sesso,String stato, String tipo){
		this.idUtente = idUtente;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
	}
	
	public UtenteBean(String idUtente, String email, String password, String nome, String cognome, Date dataNascita, String sesso){
		this.idUtente = idUtente;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
	}
	public String getFotoProfilo() {
		return fotoProfilo;
	}
	public void setFotoProfilo(String fotoProfilo) {
		this.fotoProfilo = fotoProfilo;
	}
	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getProfessione() {
		return professione;
	}

	public void setProfessione(String professione) {
		this.professione = professione;
	}

	public String getPathFotoProfilo() {
		return pathFotoProfilo;
	}

	public void setPathFotoProfilo(String pathFotoProfilo) {
		this.pathFotoProfilo = pathFotoProfilo;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}

