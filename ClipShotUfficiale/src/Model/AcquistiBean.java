package Model;

import java.sql.Date;

public class AcquistiBean {
	private String idUtente;
	private String idFoto;
	private Date data;
	
	
	public AcquistiBean() {
	}
	
	public AcquistiBean(String idUtente, String idFoto, Date data){
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

	public String getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(String idFoto) {
		this.idFoto = idFoto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}

