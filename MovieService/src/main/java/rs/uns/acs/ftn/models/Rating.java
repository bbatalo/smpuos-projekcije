package rs.uns.acs.ftn.models;

public class Rating {
	
	private Long value;
	private String posterId;
	
	public Rating() {

	}
	
	public Rating(Long value, String posterId) {
		this.value = value;
		this.posterId = posterId;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Rating other = (Rating) obj;
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
	
	

}
