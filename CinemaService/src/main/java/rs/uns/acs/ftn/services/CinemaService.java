package rs.uns.acs.ftn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
