package rs.uns.acs.ftn.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Movie implements Serializable {
	
	@Id
	private String id;
	private String name;
	private String description;
	private Long length;
	private Director director;
	private List<Actor> actors;
	private Category category;
	private List<Rating> ratings;
	private Double ratingAvg;
	private Date premiere;
	
	
	
	public Movie() {
	}
	
	
	
	public Movie(String name, String description, Long length, Director director, List<Actor> actors,
			Category category, List<Rating> ratings, Double ratingAvg, Date premiere) {

		this.name = name;
		this.description = description;
		this.length = length;
		this.director = director;
		this.actors = actors;
		this.category = category;
		this.ratings = ratings;
		this.ratingAvg = ratingAvg;
		this.premiere = premiere;
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getLength() {
		return length;
	}
	
	public void setLength(Long length) {
		this.length = length;
	}
	
	public Director getDirector() {
		return director;
	}
	
	public void setDirector(Director director) {
		this.director = director;
	}
	
	public List<Actor> getActors() {
		return actors;
	}
	
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Date getPremiere() {
		return premiere;
	}
	
	public void setPremiere(Date premiere) {
		this.premiere = premiere;
	}
	
	


	public List<Rating> getRatings() {
		return ratings;
	}



	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}



	public Double getRatingAvg() {
		return ratingAvg;
	}



	public void setRatingAvg(Double ratingAvg) {
		this.ratingAvg = ratingAvg;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((premiere == null) ? 0 : premiere.hashCode());
		result = prime * result + ((ratingAvg == null) ? 0 : ratingAvg.hashCode());
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode());
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
		Movie other = (Movie) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (premiere == null) {
			if (other.premiere != null)
				return false;
		} else if (!premiere.equals(other.premiere))
			return false;
		if (ratingAvg == null) {
			if (other.ratingAvg != null)
				return false;
		} else if (!ratingAvg.equals(other.ratingAvg))
			return false;
		if (ratings == null) {
			if (other.ratings != null)
				return false;
		} else if (!ratings.equals(other.ratings))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", description=" + description + ", length=" + length
				+ ", director=" + director + ", actors=" + actors + ", category=" + category + ", ratings=" + ratings
				+ ", ratingAvg=" + ratingAvg + ", premiere=" + premiere + "]";
	}

	public double calculateRating(){
		int sum=0;
		int counter = 0;
		for (Rating rat : ratings){
			counter+=1;
			sum+=rat.getValue();
		}
		double ratingAvg = ((double)sum)/counter;
		return ratingAvg;
	}


	

	
	
}
