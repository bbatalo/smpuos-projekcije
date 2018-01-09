package rs.uns.acs.ftn.models;

public class Rating {

	private int value;
	private String userId;
	
	public Rating() {}

	public Rating(int value, String userId) {
		super();
		this.value = value;
		this.userId = userId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
