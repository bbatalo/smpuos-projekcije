package rs.uns.acs.ftn.forms;

public class ActivationForm {

	private String username;
	private String sessionId;
	
	public ActivationForm() {
		super();
	}
	
	public ActivationForm(String username, String sessionId) {
		super();
		this.username = username;
		this.sessionId = sessionId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
