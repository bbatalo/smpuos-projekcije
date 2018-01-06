package rs.uns.acs.ftn.forms;

import java.util.Date;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import rs.uns.acs.ftn.models.User;

public class SignUpForm {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private User.Gender gender;
	private double locationX;
	private double locationY;
	
	public SignUpForm() {
		super();
	}
	
	public SignUpForm(String username, String password, String firstName, String lastName,
			Date dateOfBirth, User.Gender gender, double locationX, double locationY) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public User.Gender getGender() {
		return gender;
	}
	
	public void setGender(User.Gender gender) {
		this.gender = gender;
	}
	
	public double getLocationX() {
		return locationX;
	}
	
	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}
	
	public double getLocationY() {
		return locationY;
	}
	
	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}
	
	public GeoJsonPoint getLocation() {
		return new GeoJsonPoint(locationX, locationY);
	}
	
}
