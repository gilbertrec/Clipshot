/**
 * @author Carmine Cristian Cruoglio
 * @author Adalgiso Della Calce
 * @author Stefano Linguiti
 * @author Gilberto Recupito
 */
package Model;

import java.sql.Date;
import java.sql.Time;

public class MessaggioBean {
	private String idMittente;
	private String idDestinatario;
	private Date dataMessaggio;
	private Time ora;
	private String corpo;
	
	
	public MessaggioBean() { }
	
	public String getIdMittente() {
		return idMittente;
	}
	
	public void setIdMittente(String idMittente) {
		this.idMittente = idMittente;
	}
	
	public String getIdDestinatario() {
		return idDestinatario;
	}
	
	public void setIdDestinatario(String idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
	
	public Date getDataMessaggio() {
		return dataMessaggio;
	}
	
	public void setDataMessaggio(Date dataMessaggio) {
		this.dataMessaggio = dataMessaggio;
	}
	
	public Time getOra() {
		return ora;
	}
	
	public void setOra(Time ora) {
		this.ora = ora;
	}
	
	public String getCorpo() {
		return corpo;
	}
	
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
}
