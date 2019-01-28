package Model;

import java.sql.Time;
import java.sql.Date;

public class CommentoBean {
	private String idUtente;
	private String idPost;
	private Date data;
	private Time ora;
	private String descrizione;
	
	public CommentoBean(String idUtente, String idPost, Date data, Time ora, String descrizione) {
		
		this.idUtente = idUtente;
		this.idPost = idPost;
		this.data = data;
		this.ora = ora;
		this.descrizione = descrizione;
	}
	
	public String getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	public String getIdPost() {
		return idPost;
	}
	public void setIdPost(String idPost) {
		this.idPost = idPost;
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}
