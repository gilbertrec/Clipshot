package Model;

import java.sql.Date;

public class MessaggioAssistenzaBean {
	private String idDestinatario;
	private String idMittente;
	private Date data;
	private String ora;
	private String corpo;
	
	
	public MessaggioAssistenzaBean() {
	}
	
	public MessaggioAssistenzaBean(String idDestinatario, String idMittente, Date data, String ora, String corpo) {
		
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

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	
}
