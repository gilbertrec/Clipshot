package Model;

import java.sql.Date;

public class UtenteBean 
{
	private String idUtente;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private Date data;
	private String sesso;
	private String indirizzo;
	private String professione;
	private String pathFotoProfilo;
	private String stato;
	private String tipo;
	
	
	public UtenteBean() {
		
	}
	public UtenteBean(String idUtente, String email, String password, String nome, String cognome, Date data, String sesso){
		this.idUtente = idUtente;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.data = data;
		this.sesso = sesso;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

