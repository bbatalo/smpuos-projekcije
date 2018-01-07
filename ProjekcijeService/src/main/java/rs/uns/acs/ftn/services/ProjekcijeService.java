package rs.uns.acs.ftn.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rs.uns.acs.ftn.controllers.ProjekcijeController.CinemaServiceClient;
import rs.uns.acs.ftn.controllers.ProjekcijeController.MovieServiceClient;
import rs.uns.acs.ftn.controllers.ProjekcijeController.UserServiceClient;
import rs.uns.acs.ftn.models.Projekcije;
import rs.uns.acs.ftn.repositories.ProjekcijeRepository;

@Service
public class ProjekcijeService extends AbstractCRUDService<Projekcije, String> {

	private ProjekcijeRepository projekcijeRepository;

	@Autowired
	private UserServiceClient userServiceClient; // feign client

	@Autowired
	private CinemaServiceClient cinemaServiceClient; // feign client

	@Autowired
	private MovieServiceClient movieServiceClient; // feign client

	@Autowired
	public ProjekcijeService(ProjekcijeRepository repo) {
		super(repo);
		this.projekcijeRepository = repo;
	}

	/* ------------------------------ KORISNIK ------------------------- */

	// date
	public List<Projekcije> findByDate(Date date) {
		return projekcijeRepository.findByDate(date);
	}

	// type - premiere
	public List<Projekcije> isPremiere() {
		return projekcijeRepository.findByType(Projekcije.Type.PREMIERE);
	}

	// status - active
	public List<Projekcije> isActive() {
		return projekcijeRepository.findByStatus(Projekcije.Status.ACTIVE);
	}

	// cinema
	public List<Projekcije> findByCinemaId(String id) {
		return projekcijeRepository.findByCinemaId(id);
	}

	public List<Projekcije> findByCinemaName(String cinemaName) {
		return projekcijeRepository.findByCinemaName(cinemaName);
	}

	// movie
	public List<Projekcije> findByMovieId(String id) {
		return projekcijeRepository.findByMovieId(id);
	}

	public List<Projekcije> findByMovieName(String movieName) {
		return projekcijeRepository.findByMovieName(movieName);
	}

	/* ------------------------------ HYSTRIX -------------------------- */

	/* USING LOAD-BALANCING - USER */
	/**
	 * Method checks what type if for given sessionId
 	 * We use Ribbon and Feign to get data from user-service, load-balancing
	 * 
	 * @param sessionId
	 * @return if user exists
	 */
	@HystrixCommand(fallbackMethod = "fallbackGetType")
	public String getTypeBySessionIdLoad(String sessionId) {
		/* USING LOAD-BALANCING */
		return userServiceClient.getTypeBySessionId(sessionId);
	}

	public String fallbackGetType(String sessionId) {
		System.out.println("!!!!!!!!!!!!!!!user!!!!!!!!!!!!!!");
		return "";
	}

	/* USING LOAD-BALANCING - MOVIE */
	@HystrixCommand(fallbackMethod = "fallbackGetMovieName")
	public String getMovieNameLoad(String movieId) {
		/* USING LOAD-BALANCING */
		return movieServiceClient.getMovieName(movieId);
	}

	public String fallbackGetMovieName(String movieId) {
		System.out.println("!!!!!!!!!!!!!movie!!!!!!!!!!!!!");
		return "Default Movie";
	}

	/* USING LOAD-BALANCING - CINEMA */
	@HystrixCommand(fallbackMethod = "fallbackGetCinemaHallName")
	public Map<String, Object> getCinemaHallName(String cinemaId, String hallId) {
		/* USING LOAD-BALANCING */
		return cinemaServiceClient.getCinemaHallName(cinemaId, hallId);
	}

	public Map<String, Object> fallbackGetCinemaHallName(String cinemaId, String hallId) {
		System.out.println("!!!!!!!!!!!!!cinema!!!!!!!!!!!!!");

		Map<String, Object> m = new HashMap<String, Object>();
		String defCinema = "Default Cinema";
		String defHall = "Default Hall";

		m.put("cinemaName", (Object) defCinema);
		m.put("hallLabel", (Object) defHall);

		return m;
	}
}
