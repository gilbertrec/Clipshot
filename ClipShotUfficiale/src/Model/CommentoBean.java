package Model;

import java.util.GregorianCalendar;

public class CommentoBean {
	private String idUtente;
	private int idPost;
	private String idUtentePost;
	private GregorianCalendar data;
	private GregorianCalendar ora;
	private String descrizione;
	
	
	public CommentoBean() {	}
	
	public String getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	
	public int getIdPost() {
		return idPost;
	}
	
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	
	public String getIdUtentePost() {
		return idUtentePost;
	}
	
	public void setIdUtentePost(String idUtentePost) {
		this.idUtentePost = idUtentePost;
	}
	
	public GregorianCalendar getData() {
		return data;
	}
	
	public void setData(GregorianCalendar data) {
		this.data = data;
	}
	
	public GregorianCalendar getOra() {
		return ora;
	}
	
	public void setOra(GregorianCalendar ora) {
		this.ora = ora;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public int getYear() {
		return this.data.get(GregorianCalendar.YEAR);
	}
	
	public int getMonth() {
		return this.data.get(GregorianCalendar.MONTH)+1;
	}
	
	public int getDay() {
		return this.data.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public int getHour() {
		return this.ora.get(GregorianCalendar.HOUR_OF_DAY);
	}
	
	public int getMinute() {
		return this.ora.get(GregorianCalendar.MINUTE);
	}
	
	public int getSecond() {
		return this.ora.get(GregorianCalendar.SECOND);
	}
	
	public String getStringData() {
		return this.getYear()+"-"+this.getMonth()+"-"+this.getDay();
	}
	
	public String getStringOra() {
		return this.getHour()+":"+this.getMinute()+":"+this.getSecond();
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}
