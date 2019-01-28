package Model;

public class OperatoreBean {
	private String idUtente;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String tipo;
	
	public OperatoreBean(String idUtente, String password, String nome, String cognome, String email, String tipo) {
		this.idUtente = idUtente;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.tipo = tipo;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
