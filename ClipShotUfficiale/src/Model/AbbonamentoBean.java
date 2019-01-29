package Model;

import java.sql.Date;

public class AbbonamentoBean {
	private String idUtente;
	private Date dataScadenza;
	private int numeroCarta;
	private String stato;
	
	public AbbonamentoBean() {
	}
	
	public AbbonamentoBean(String idUtente, Date dataScadenza, int ncarta, String stato) {
		
		this.idUtente = idUtente;
		this.dataScadenza = dataScadenza;
		this.numeroCarta = ncarta;
		this.stato = stato;
	}
	public String getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public int getNumeroCarta() {
		return numeroCarta;
	}
	public void setNumeroCarta(int ncarta) {
		this.numeroCarta = ncarta;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
}
