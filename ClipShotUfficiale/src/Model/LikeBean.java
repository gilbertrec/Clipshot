package Model;

public class LikeBean {
	private String idUtente;
	private int idPost;
	private String idUtentePost;
	
	public LikeBean() {	}

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
}

