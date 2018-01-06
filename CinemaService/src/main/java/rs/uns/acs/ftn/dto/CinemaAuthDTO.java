package rs.uns.acs.ftn.dto;

import rs.uns.acs.ftn.models.Cinema;

public class CinemaAuthDTO {

	private Cinema cinema;
	private String userId;
	
	public CinemaAuthDTO() {}
	
	public CinemaAuthDTO(Cinema cinema, String userId) {
		super();
		this.cinema = cinema;
		this.userId = userId;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
