package Model;

public class LikeBean {
	private String idFollower;
	private String idFollowing;
	
	public LikeBean(String idFollower, String idFollowing) {

		this.idFollower = idFollower;
		this.idFollowing = idFollowing;
	}

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
