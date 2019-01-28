package Model;

public class FotoBean 
{
	private String idFoto;
	private String path;
	private Double prezzo;
	
	public FotoBean(String idFoto, String path)
	{
		this.idFoto = idFoto;
		this.path = path;
		prezzo = null;
	}
	
	public FotoBean(String idFoto, String path, Double prezzo){
		this.idFoto = idFoto;
		this.path = path;
		this.prezzo = prezzo;
	}

	public String getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(String idFoto) {
		this.idFoto = idFoto;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
}
