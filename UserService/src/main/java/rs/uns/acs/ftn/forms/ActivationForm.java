package rs.uns.acs.ftn.forms;

public class ActivationForm {

	private String username;
	private String requesterId;
	
	public ActivationForm() {
		super();
	}
	
	public ActivationForm(String username, String requesterId) {
		super();
		this.username = username;
		this.requesterId = requesterId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRequesterId() {
		return requesterId;
	}
	
	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}
	
}
