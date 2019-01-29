package Model;

public class LikeBean {
	private String idUtente;
	private String idPost;
	private String idUtentePost;
	
	
	public LikeBean() {
	}
	
	public LikeBean(String idUtente, String idPost, String idUtentePost) {
		this.idUtente=idUtente;
		this.idPost=idPost;
		this.idUtentePost=idUtentePost;
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

	public String getIdUtentePost() {
		return idUtentePost;
	}

	public void setIdUtentePost(String idUtentePost) {
		this.idUtentePost = idUtentePost;
	}
	
	
	
}
