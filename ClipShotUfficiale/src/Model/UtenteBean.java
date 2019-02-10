/**
 * @author Carmine Cristian Cruoglio
 * @author Adalgiso Della Calce
 * @author Stefano Linguiti
 * @author Gilberto Recupito
 */
package Model;

import java.util.GregorianCalendar;

public class UtenteBean 
{
	private String idUtente;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private GregorianCalendar dataNascita;
	private String sesso;
	private String indirizzo;
	private String fotoProfilo;
	private String stato;
	private String tipo;
	
	
	public UtenteBean() { }

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
	public GregorianCalendar getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(GregorianCalendar dataNascita) {
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
	public String getFotoProfilo() {
		return fotoProfilo;
	}
	public void setFotoProfilo(String fotoProfilo) {
		this.fotoProfilo = fotoProfilo;
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
	
	public int getYear() {
		return this.dataNascita.get(GregorianCalendar.YEAR);
	}
	
	public int getMonth() {
		return this.dataNascita.get(GregorianCalendar.MONTH)+1;
	}
	
	public int getDay() {
		return this.dataNascita.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public String getStringData() {
		return this.getYear()+"-"+this.getMonth()+"-"+this.getDay();
	}
}
