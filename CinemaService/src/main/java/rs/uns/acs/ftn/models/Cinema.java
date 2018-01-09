package rs.uns.acs.ftn.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Cinema implements Serializable {

	@Id
	private String id;
	private String name;
	private String address;
	@GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2DSPHERE)
	private GeoJsonPoint location;
	private Collection<ProjectionHall> halls;
	private Collection<Rating> ratings;
	private double grade;
	
	public Cinema() {
		this.halls = new ArrayList<ProjectionHall>();
		this.ratings = new ArrayList<Rating>();
	}

	public Cinema(String name, double grade, Collection<ProjectionHall> halls) {
		super();
		this.name = name;
		this.grade = grade;
		this.halls = halls;
	}
	

	public Cinema(String name, String address, GeoJsonPoint location, Collection<ProjectionHall> halls,
			Collection<Rating> ratings, double grade) {
		super();
		this.name = name;
		this.address = address;
		this.location = location;
		this.halls = halls;
		this.ratings = ratings;
		this.grade = grade;
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
	
	public double getGrade() {
		return grade;
	}
	
	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public GeoJsonPoint getLocation() {
		return location;
	}

	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}


	public Collection<ProjectionHall> getHalls() {
		return halls;
	}

	public void setHalls(Collection<ProjectionHall> halls) {
		this.halls = halls;
	}
	
	public Collection<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<Rating> ratings) {
		this.ratings = ratings;
	}

	public double calculateRating() {
		double sum = 0;
		int counter = 0;
		for (Rating rat : ratings){
			counter += 1;
			sum += rat.getValue();
		}
		
		double ratingAvg = sum/counter;
		return ratingAvg;
	}
	
}
