package rs.uns.acs.ftn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import rs.uns.acs.ftn.models.Cinema;
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
}
