package Model;

import java.sql.Date;
import java.sql.Time;

public class MessaggioAssistenzaBean {
	private String idDestinatario;
	private String idMittente;
	private Date data;
	private Time ora;
	private String corpo;
	
	public MessaggioAssistenzaBean() {
		
	}
	
	public MessaggioAssistenzaBean(String idDestinatario, String idMittente, Date data, Time ora, String corpo) {
		
		this.idDestinatario = idDestinatario;
		this.idMittente = idMittente;
		this.data = data;
		this.ora = ora;
		this.corpo = corpo;
	}

	public String getIdDestinatario() {
		return idDestinatario;
	}

	public void setIdDestinatario(String idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	public String getIdMittente() {
		return idMittente;
	}

	public void setIdMittente(String idMittente) {
		this.idMittente = idMittente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getOra() {
		return ora;
	}

	public void setOra(Time ora) {
		this.ora = ora;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	
}
