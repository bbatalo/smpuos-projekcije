package rs.uns.acs.ftn.dto;

public class CinemaHallDTO {

	private String cinemaName;
	private String hallLabel;
	
	public CinemaHallDTO() {}
	
	public CinemaHallDTO(String cinemaName, String hallLabel) {
		super();
		this.cinemaName = cinemaName;
		this.hallLabel = hallLabel;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getHallLabel() {
		return hallLabel;
	}

	public void setHallLabel(String hallLabel) {
		this.hallLabel = hallLabel;
	}
	
	
}
