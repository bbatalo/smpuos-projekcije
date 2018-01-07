package rs.uns.acs.ftn.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import rs.uns.acs.ftn.dto.CinemaHallDTO;
import rs.uns.acs.ftn.models.Cinema;
import rs.uns.acs.ftn.models.ProjectionHall;
import rs.uns.acs.ftn.repositories.CinemaRepository;

@Service
public class CinemaService extends AbstractCRUDService<Cinema, String> {

	private CinemaRepository cinemaRepository;
	
	@Autowired
	public CinemaService(CinemaRepository cinemaRepository) {
		super(cinemaRepository);
		this.cinemaRepository = cinemaRepository;
	}
	
	public List<Cinema> findAllByName(String name) {
		return cinemaRepository.findAllByName(name);
	}
	
	public List<Cinema> findAllByLocation(Double x, Double y, Double distance) {
		System.out.println(x + " " + y + " " + distance);
		Distance dist = new Distance(distance, Metrics.KILOMETERS);
		System.out.println(dist);
		return cinemaRepository.findByLocationNear(new Point(x, y), dist);
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
}
