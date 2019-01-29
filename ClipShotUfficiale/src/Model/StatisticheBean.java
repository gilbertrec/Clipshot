package Model;

public class StatisticheBean {
	private String idUtente;
	private int numeroVisualizzazioni;
	
	
	public StatisticheBean() {
		
	}
	
	public StatisticheBean(String idUtente, int numeroVisualizzazioni) {
		this.idUtente = idUtente;
		this.numeroVisualizzazioni = numeroVisualizzazioni;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public int getNumeroVisualizzazioni() {
		return numeroVisualizzazioni;
	}

	public void setNumeroVisualizzazioni(int numeroVisualizzazioni) {
		this.numeroVisualizzazioni = numeroVisualizzazioni;
	}
	
}
