package rs.uns.acs.ftn.models;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Cinema implements Serializable {

	@Id
	private String id;
	private String name;
	private Address address;
	private Collection<ProjectionHall> halls;
	private int grade;
	
	public Cinema() {
		
	}

	public Cinema(String name, Address address, int grade, Collection<ProjectionHall> halls) {
		super();
		this.name = name;
		this.address = address;
		this.grade = grade;
		this.halls = halls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Collection<ProjectionHall> getHalls() {
		return halls;
	}

	public void setHalls(Collection<ProjectionHall> halls) {
		this.halls = halls;
	}
	
	
	
}
