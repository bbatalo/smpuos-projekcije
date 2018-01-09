package rs.uns.acs.ftn.dto;

public class RatingDTO {

	private int value;
	private String userId;
	private String cinemaId;
	
	public RatingDTO() {}
	
	public RatingDTO(int value, String userId, String cinemaId) {
		super();
		this.value = value;
		this.userId = userId;
		this.cinemaId = cinemaId;
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

	public String getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}
	
}
