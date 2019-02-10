/**
 * @author Carmine Cristian Cruoglio
 * @author Adalgiso Della Calce
 * @author Stefano Linguiti
 * @author Gilberto Recupito
 */
package Model;

import java.util.GregorianCalendar;

public class CartaDiCreditoBean {
	private String numeroCarta;
	private String idUtente;
	private String intestatario;
	private GregorianCalendar dataScadenza;
	private String cvv;
	
	public CartaDiCreditoBean() {}
	
	public String getNumeroCarta() {
		return numeroCarta;
	}
	
	public void setNumeroCarta(String ncarta) {
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
	
	public String getCvv() {
		return cvv;
	}
	
	public void setCvv(String cvv) {
		this.cvv = cvv;
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
}
