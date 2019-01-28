package Model;

import java.sql.Date;

public class SegnalazioneBean {
	private String idSegnalazione;
	private String causa;
	private String stato;
	private Date data;
	private String descrizione;
	
	public SegnalazioneBean(String idSegnalazione, String causa, String stato, Date data) {
		this.idSegnalazione = idSegnalazione;
		this.causa = causa;
		this.stato = stato;
		this.data = data;
	}

	public String getIdSegnalazione() {
		return idSegnalazione;
	}

	public void setIdSegnalazione(String idSegnalazione) {
		this.idSegnalazione = idSegnalazione;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
