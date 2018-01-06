package rs.uns.acs.ftn.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Projekcije implements Serializable{

	public enum Status {ACTIVE, INACTIVE}; 
	public enum Type {PREMIERE, REGULAR}; 
	
	@Id
	private String id;
	private Date date;
	private Type type;
	private Status status;
	private String cinemaId;
	private String cinemaName;
	private String hallId;
	private String hallLabel;
	private String movieId;
	private String movieName;
		
	public Projekcije(){
		super();
	}

	public Projekcije(String id, Date date, Type type, Status status, String cinemaId, String cinemaName, String hallId,
			String hallLabel, String movieId, String movieName) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.status = status;
		this.cinemaId = cinemaId;
		this.cinemaName = cinemaName;
		this.hallId = hallId;
		this.hallLabel = hallLabel;
		this.movieId = movieId;
		this.movieName = movieName;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getHallId() {
		return hallId;
	}

	public void setHallId(String hallId) {
		this.hallId = hallId;
	}

	public String getHallLabel() {
		return hallLabel;
	}

	public void setHallLabel(String hallLabel) {
		this.hallLabel = hallLabel;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cinemaId == null) ? 0 : cinemaId.hashCode());
		result = prime * result + ((cinemaName == null) ? 0 : cinemaName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((hallId == null) ? 0 : hallId.hashCode());
		result = prime * result + ((hallLabel == null) ? 0 : hallLabel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		result = prime * result + ((movieName == null) ? 0 : movieName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Projekcije other = (Projekcije) obj;
		if (cinemaId == null) {
			if (other.cinemaId != null)
				return false;
		} else if (!cinemaId.equals(other.cinemaId))
			return false;
		if (cinemaName == null) {
			if (other.cinemaName != null)
				return false;
		} else if (!cinemaName.equals(other.cinemaName))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (hallId == null) {
			if (other.hallId != null)
				return false;
		} else if (!hallId.equals(other.hallId))
			return false;
		if (hallLabel == null) {
			if (other.hallLabel != null)
				return false;
		} else if (!hallLabel.equals(other.hallLabel))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		if (movieName == null) {
			if (other.movieName != null)
				return false;
		} else if (!movieName.equals(other.movieName))
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Projekcije [id=" + id + ", date=" + date + ", type=" + type + ", status=" + status + ", cinemaId="
				+ cinemaId + ", cinemaName=" + cinemaName + ", hallId=" + hallId + ", hallLabel=" + hallLabel
				+ ", movieId=" + movieId + ", movieName=" + movieName + "]";
	}
	



		
}
