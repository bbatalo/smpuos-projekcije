package rs.uns.acs.ftn.models.dto;

import rs.uns.acs.ftn.models.Rating;

public class RatingDTO {
	

	private Long value;
	private String posterId;
	private String movieId;
	
	public RatingDTO() {

	}
	
	public RatingDTO(Long value, String posterId, String movieId) {
		this.value = value;
		this.posterId = posterId;
		this.movieId = movieId;
	}
	
	public Long getValue() {
		return value;
	}
	
	public void setValue(Long value) {
		this.value = value;
	}
	
	public String getPosterId() {
		return posterId;
	}
	
	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}
	
	public String getMovieId() {
		return movieId;
	}
	
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		result = prime * result + ((posterId == null) ? 0 : posterId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatingDTO other = (RatingDTO) obj;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		if (posterId == null) {
			if (other.posterId != null)
				return false;
		} else if (!posterId.equals(other.posterId))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "RatingDTO [value=" + value + ", posterId=" + posterId + ", movieId=" + movieId + "]";
	}
	
	

}
