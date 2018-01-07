package rs.uns.acs.ftn.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rs.uns.acs.ftn.controllers.ProjekcijeController.CinemaServiceClient;
import rs.uns.acs.ftn.controllers.ProjekcijeController.MovieServiceClient;
import rs.uns.acs.ftn.controllers.ProjekcijeController.UserServiceClient;
import rs.uns.acs.ftn.models.Projekcije;
import rs.uns.acs.ftn.repositories.ProjekcijeRepository;

@Service
public class ProjekcijeService extends AbstractCRUDService<Projekcije, String> {

	private ProjekcijeRepository projekcijeRepository;
	@SuppressWarnings("unused")
	private RestTemplate restTemplate;

	@Autowired
	private UserServiceClient userServiceClient; // feign client

	@Autowired
	private CinemaServiceClient cinemaServiceClient; // feign client

	@Autowired
	private MovieServiceClient movieServiceClient; // feign client

	@Autowired
	public ProjekcijeService(ProjekcijeRepository repo, RestTemplate restTemplate) {
		super(repo);
		this.projekcijeRepository = repo;
		this.restTemplate = restTemplate;
	}

	/* ------------------------------ ADMIN ---------------------------- */

	public ResponseEntity<Projekcije> createProjection(Projekcije newEntity, String sessionId) {

		// ako korisnik nije tipa admin, prekini aktivnost
		if (!isAdmin(userServiceClient.getTypeBySessionId(sessionId))) {
			return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);
		}

		// proveri da li su bisokop i sala validni
		Map<String, Object> responseCinemaHall = cinemaServiceClient.getCinemaHallName(newEntity.getCinemaId(),
				newEntity.getHallId());
		String cinemaName = (String) responseCinemaHall.get("cinemaName");
		String hallLabel = (String) responseCinemaHall.get("hallLabel");
		if (cinemaName == null) {
			return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);
		}
		newEntity.setCinemaName(cinemaName);
		newEntity.setHallLabel(hallLabel); // upisujem i kada mi posalje "error: not found" !!!

		// proveri da li je film validan
		ResponseEntity<String> responseMovieName = movieServiceClient. getMovieName(newEntity.getMovieId());
		String movieName = responseMovieName.getBody();
		if (movieName == null) {
			return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);
		}
		newEntity.setMovieName(movieName);

		// ako je sve u redu, kreiraj
		return new ResponseEntity<Projekcije>(projekcijeRepository.save(newEntity), HttpStatus.OK);
	}

	public ResponseEntity<Projekcije> updateProjection(String id, Projekcije newEntity, String sessionId) {

		// ako korisnik nije tipa admin, prekini aktivnost
		if (!isAdmin(userServiceClient.getTypeBySessionId(sessionId))) {
			return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);
		}

		Projekcije oldEntity = projekcijeRepository.findOne(id);
		// ako nije pronasao tu projekciju, prekini aktivnost
		if (oldEntity == null) {
			return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);
		}

		if(newEntity.getDate() !=null){
			oldEntity.setDate(newEntity.getDate());
		}	
		
		if(newEntity.getStatus() !=null){
			oldEntity.setStatus(newEntity.getStatus());
		}
		
		if(newEntity.getType() !=null){
			oldEntity.setType(newEntity.getType());
		}
		
		// ako se menja bioskop
		if (newEntity.getCinemaId() != null) {
			
			/* isto kao u createProjection */
			// proveri da li su bisokop i sala validni
			Map<String, Object> responseCinemaHall = cinemaServiceClient.getCinemaHallName(newEntity.getCinemaId(),
					newEntity.getHallId());
			String cinemaName = (String) responseCinemaHall.get("cinemaName");
			String hallLabel = (String) responseCinemaHall.get("hallLabel");
			if (cinemaName == null) {
				return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);
			}
			oldEntity.setCinemaName(cinemaName);
			oldEntity.setHallLabel(hallLabel); // upisujem i kada mi posalje "error: not found" !!!
		}

		// ako se menja film
		if (newEntity.getMovieId() != null) {
			
			/* isto kao u createProjection */
			// proveri da li je film validan
			ResponseEntity<String> responseMovieName = movieServiceClient.getMovieName(newEntity.getMovieId());
			String movieName = responseMovieName.getBody();
			if (movieName == null) {
				return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);
			}
			oldEntity.setMovieName(movieName);
		}

		return new ResponseEntity<Projekcije>(projekcijeRepository.save(oldEntity), HttpStatus.OK);
	}

	public ResponseEntity<Boolean> deleteProjection(String id, String sessionId) {
		
		// ako korisnik nije tipa admin, prekini aktivnost
		if (!isAdmin(userServiceClient.getTypeBySessionId(sessionId))) {
			return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
		}

		projekcijeRepository.delete(id);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
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

	/**
	 * Method compare if given string is equal to "ADMINISTRATOR"
	 * 
	 * @param type
	 * @return if user type is administrator
	 */
	private boolean isAdmin(String type) {
		if (type == null) {
			return false;
		}

		if (type.equals("ADMINISTRATOR")) {
			return true;
		}

		return false;
	}

	/* ------------------------------ HYSTRIX -------------------------- */

	/* USING LOAD-BALANCING - USER */
	/**
	 * Method checks if the given user is registered and active We use Ribbon and
	 * Feign to get data from user-service, load-balancing
	 * 
	 * @param username
	 * @return if user exists
	 */
	@HystrixCommand(fallbackMethod = "fallbackGetType")
	public String getTypeLoad(String sessionId) {
		/* USING LOAD-BALANCING */
		return userServiceClient.getTypeBySessionId(sessionId);
	}

	public String fallbackGetType(String username) {
		System.out.println("!!!!!!!!!!!!!!!user!!!!!!!!!!!!!!");
		return "";
	}

	/* USING LOAD-BALANCING - MOVIE */
	@HystrixCommand(fallbackMethod = "fallbackGetMovieName")
	public ResponseEntity<String> getMovieNameLoad(String movieId) {
		/* USING LOAD-BALANCING */
		return movieServiceClient.getMovieName(movieId);
	}

	public String fallbackGetMovieName(String movieId) {
		System.out.println("!!!!!!!!!!!!!movie!!!!!!!!!!!!!");
		return "";
	}

	/* USING LOAD-BALANCING - CINEMA */
	@HystrixCommand(fallbackMethod = "fallbackGetCinemaHallName")
	public Map<String, Object> getCinemaHallNameLoad(String cinemaId, String hallId) {
		/* USING LOAD-BALANCING */
		return cinemaServiceClient.getCinemaHallName(cinemaId, hallId);
	}

	public String fallbackGetCinemaHallName(String movieId) {
		System.out.println("!!!!!!!!!!!!!cinema!!!!!!!!!!!!!");
		return "";
	}
}
