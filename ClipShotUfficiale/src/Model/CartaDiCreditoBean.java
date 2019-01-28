package Model;

import java.sql.Date;

public class CartaDiCreditoBean {
	private int numeroCarta;
	private String idUtente;
	private String intestatario;
	private Date dataScadenza;
	private String cvv;
	
	public CartaDiCreditoBean() {
		
	}
	public CartaDiCreditoBean(int ncarta, String idUtente, String intestatario, Date dataScadenza, String cvv) {
		this.numeroCarta = ncarta;
		this.idUtente = idUtente;
		this.intestatario = intestatario;
		this.dataScadenza = dataScadenza;
		this.cvv = cvv;
	}

	public int getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(int ncarta) {
		this.numeroCarta = ncarta;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	
}
