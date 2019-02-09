package Model;

import java.sql.Date;

public class SegnalazioneBean {
	private int idSegnalazione;
	private String idUtente;
	private int idPost;
	private String idUtentePost;
	private String causa;
	private String stato;
	private Date data;
	private String descrizione;
	
	
	public SegnalazioneBean() { }

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

	public int getIdSegnalazione() {
		return idSegnalazione;
	}

	public void setIdSegnalazione(int idSegnalazione) {
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
