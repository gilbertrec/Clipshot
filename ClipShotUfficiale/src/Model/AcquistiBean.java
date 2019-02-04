package Model;

import java.sql.Date;
import java.util.GregorianCalendar;

public class AcquistiBean {
	private String idUtente;
	private int idFoto;
	private GregorianCalendar data;
	
	
	public AcquistiBean() {
	}
	
	public AcquistiBean(String idUtente, int idFoto, GregorianCalendar data){
		this.idUtente = idUtente;
		this.idFoto = idFoto;
		this.data = data;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public int getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
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
	
	public String getStringData() {
		String date =""+this.getYear()+"-"+this.getMonth()+"-"+this.getDay()+"";
		return date;
	}
}

