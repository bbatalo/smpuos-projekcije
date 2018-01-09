package rs.uns.acs.ftn.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rs.uns.acs.ftn.controllers.CinemaController.UserServiceClient;
import rs.uns.acs.ftn.models.Cinema;
import rs.uns.acs.ftn.models.HallType;
import rs.uns.acs.ftn.models.ProjectionHall;
import rs.uns.acs.ftn.models.Rating;
import rs.uns.acs.ftn.repositories.CinemaRepository;

@Service
public class CinemaService extends AbstractCRUDService<Cinema, String> {

	private CinemaRepository cinemaRepository;
	
	@Autowired
	private UserServiceClient userServiceClient;
	
	@Autowired
	public CinemaService(CinemaRepository cinemaRepository) {
		super(cinemaRepository);
		this.cinemaRepository = cinemaRepository;
	}
	
	public List<Cinema> findAllByName(String name) {
		return cinemaRepository.findAllByName(name);
	}
	
	public List<Cinema> findAllByLocation(Double x, Double y, Double distance) {
		Distance dist = new Distance(distance, Metrics.KILOMETERS);
		return cinemaRepository.findByLocationNear(new Point(x, y), dist);
	}
	
	public List<Cinema> rankings() {
		return cinemaRepository.findAllByOrderByGradeDesc();
	}
	
	public Cinema rate(String id, Rating rating) {
		Cinema cinema = cinemaRepository.findById(id);
		
		boolean contains = false;
		
		for (Rating rt : cinema.getRatings()) {
			if (rt.getUserId().equals(rating.getUserId())) {
				contains = true;
				rt.setValue(rating.getValue());
				cinema.setGrade(cinema.calculateRating());
				cinemaRepository.save(cinema);
			}
		}
		
		if (!contains) {
			cinema.getRatings().add(rating);
			cinema.setGrade(cinema.calculateRating());
			cinemaRepository.save(cinema);
		}
		
		return cinema;
	}
	
	public Map<String, Object> findCinemaHall(String cinemaId, String hallId) {
		Cinema cin = cinemaRepository.findById(cinemaId);
		String cinName = "";
		String hallLabel = null;
		
		if (cin != null) {
			cinName = cin.getName();
			for (ProjectionHall h : cin.getHalls()) {
				if (h.getId().equals(hallId)) {
					hallLabel = h.getLabel();
					break;
				}
			}
		}
		if (hallLabel == null) {
			hallLabel = "";
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("cinemaName", cinName);
		m.put("hallLabel", hallLabel);
		
		return m;
	}

	
	@HystrixCommand(fallbackMethod = "fallbackGetType")
	public String getUserType(String sessionId) {
		return userServiceClient.getTypeBySessionId(sessionId);
	}

	public String fallbackGetType(String username) {
		System.out.println("user-service unavailable");
		return "";
	}
	
	public boolean isCinemaValid(Cinema cinema) {
		
		if ((cinema.getLocation() == null) 
				|| (cinema.getLocation().getCoordinates() == null)) {
			return false;
		}
		
		if ((cinema.getAddress() == null) || cinema.getAddress().equals("")) {
			return false;
		}
		
		if ((cinema.getName() == null) || (cinema.getName().equals(""))) {
			return false;
		}
		
		for (ProjectionHall hall : cinema.getHalls()) {
			if ((hall.getLabel() == null) || (hall.getLabel().equals(""))) {
				return false;
			}
			
			if ((hall.getRowCount()) == 0 || (hall.getColCount() == 0)) {
				return false;
			}
			
			if ((hall.getCapacity() == 0) || ((hall.getColCount() * hall.getRowCount()) != hall.getCapacity())) {
				return false;
			}

		}
		
		return true;
	}

	public boolean isRatingValid(Rating rating) {
		
		if (rating.getValue() > 5 || rating.getValue() < 1) {
			return false;
		}
		
		if ((rating.getUserId() == null) || (rating.getUserId().equals(""))) {
			return false;
		}
				
		// eventualno proveriti da li se username poklapa sa sessionId...
		
		return true;
	}
}
