package Model;

public class FotoBean 
{
	private int idFoto;
	private String path;
	private Double prezzo;
	
	public FotoBean() {
		
	}
	public FotoBean(int idFoto) {
		this.idFoto=idFoto;
	}
	
	public FotoBean(int idFoto, String path) {
		this.idFoto = idFoto;
		this.path = path;
		prezzo=null;
	}
	
	public FotoBean(int idFoto, String path, Double prezzo){
		this.idFoto = idFoto;
		this.path = path;
		this.prezzo = prezzo;
	}

	public int getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(int idFoto) {
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
