package Model;

import java.sql.Date;

public class PostBean {
	private String idPost;
	private String descrizione;
	private Date data;
	private String stato;
	private String idUtente;
	private String idFoto;
	
	
	public PostBean() {
		
	}
	
	public PostBean(String idPost, String descrizione, Date data, String stato, String idUtente, String idFoto) {
		this.idPost = idPost;
		this.descrizione = descrizione;
		this.data = data;
		this.stato = stato;
		this.idUtente = idUtente;
		this.idFoto = idFoto;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
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

}
