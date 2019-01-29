package Model;

import java.sql.Date;
import java.sql.Time;

public class PostBean {
	private String idPost;
	private String idUtente;
	private String idFoto;
	private String descrizione;
	private Date data;
	private Time ora;
	private String stato;
	
	
	public PostBean() {
		
	}
	
	public PostBean(String idPost, String idUtente, String idFoto, String descrizione, Date data, Time ora, String stato) {
		this.idPost = idPost;
		this.idUtente=idUtente;
		this.idFoto=idFoto;
		this.descrizione = descrizione;
		this.data = data;
		this.ora=ora;
		this.stato = stato;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
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

	public Time getOra() {
		return ora;
	}

	public void setOra(Time ora) {
		this.ora = ora;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	

}
