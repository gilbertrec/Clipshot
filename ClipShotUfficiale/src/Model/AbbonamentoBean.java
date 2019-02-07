package Model;

import java.util.GregorianCalendar;

public class AbbonamentoBean {
	private String idUtente;
	private GregorianCalendar dataScadenza;
	private String numeroCarta;
	private String stato;
	
	public AbbonamentoBean() {}	
	public AbbonamentoBean(String idUtente, GregorianCalendar dataScadenza, String ncarta, String stato) {
		
		this.idUtente = idUtente;
		this.dataScadenza = dataScadenza;
		this.numeroCarta = ncarta;
		this.stato = stato;
	}
	public boolean isScaduto(GregorianCalendar dataScadenza) {
		return new GregorianCalendar().before(dataScadenza);
	}
	
	public String getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	public GregorianCalendar getDataScadenza() {
		return dataScadenza;
	}
	public String getStringDataScadenza() {
		return this.getYear()+"-"+this.getMonth()+"-"+this.getDay();
	}
	public void setDataScadenza(GregorianCalendar dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public int getYear() {
		return this.dataScadenza.get(GregorianCalendar.YEAR);
	}
	public int getMonth() {
		return this.dataScadenza.get(GregorianCalendar.MONTH)+1;
	}
	public int getDay() {
		return this.dataScadenza.get(GregorianCalendar.DAY_OF_MONTH);
	}
	public String getNumeroCarta() {
		return numeroCarta;
	}
	public void setNumeroCarta(String ncarta) {
		this.numeroCarta = ncarta;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
}
