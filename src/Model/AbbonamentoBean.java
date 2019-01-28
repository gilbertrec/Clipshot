package Model;

import java.sql.Date;

public class AbbonamentoBean {
	private String idUtente;
	private Date datascadenza;
	private int ncarta;
	private String stato;
	
	
	public AbbonamentoBean(String idUtente, Date datascadenza, int ncarta, String stato) {
		super();
		this.idUtente = idUtente;
		this.datascadenza = datascadenza;
		this.ncarta = ncarta;
		this.stato = stato;
	}
	public String getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	public Date getDatascadenza() {
		return datascadenza;
	}
	public void setDatascadenza(Date datascadenza) {
		this.datascadenza = datascadenza;
	}
	public int getNcarta() {
		return ncarta;
	}
	public void setNcarta(int ncarta) {
		this.ncarta = ncarta;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
}
