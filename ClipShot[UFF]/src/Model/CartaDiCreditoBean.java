package Model;

public class CartaDiCreditoBean {
	private int ncarta;
	private String idUtente;
	private String intestatario;
	private String dataScadenza;
	private String cvv;
	
	public CartaDiCreditoBean(int ncarta, String idUtente, String intestatario, String dataScadenza, String cvv) {
		this.ncarta = ncarta;
		this.idUtente = idUtente;
		this.intestatario = intestatario;
		this.dataScadenza = dataScadenza;
		this.cvv = cvv;
	}

	public int getNcarta() {
		return ncarta;
	}

	public void setNcarta(int ncarta) {
		this.ncarta = ncarta;
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

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	
}
