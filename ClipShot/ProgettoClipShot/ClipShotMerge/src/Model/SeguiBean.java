/**
 * @author Carmine Cristian Cruoglio
 * @author Adalgiso Della Calce
 * @author Stefano Linguiti
 * @author Gilberto Recupito
 */
package Model;

public class SeguiBean {
	private String idFollower;
	private String idFollowing;
	
	public SeguiBean() { }

	public String getIdFollower() {
		return idFollower;
	}
	
	public void setIdFollower(String idFollower) {
		this.idFollower = idFollower;
	}
	
	public String getIdFollowing() {
		return idFollowing;
	}

	public void setIdFollowing(String idFollowing) {
		this.idFollowing = idFollowing;
	}
	
}
