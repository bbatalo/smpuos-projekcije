package rs.uns.acs.ftn.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

	@SuppressWarnings("unused")
	@Autowired
	private CinemaServiceClient cinemaServiceClient; // feign client

	@SuppressWarnings("unused")
	@Autowired
	private MovieServiceClient movieServiceClient; // feign client

	@Autowired
	public ProjekcijeService(ProjekcijeRepository repo, RestTemplate restTemplate) {
		super(repo);
		this.projekcijeRepository = repo;
		this.restTemplate = restTemplate;
	}

	/* ------------------------------ ADMIN ---------------------------- */
	// TO DO...
	public Projekcije createProjection(Projekcije newEntity, String sessionId) {
		System.out.println("Create sa entitetom");

		// ako korisnik nije tipa admin, prekini aktivnost
		if (!isAdmin(userServiceClient.getTypeBySessionId(sessionId))) {
			return null;
		}

		// proveri da li su bisokop i sala validni

		// proveri da li je film validan

		// ako je sve u redu, kreiraj
		return projekcijeRepository.save(newEntity);
	}

	/*
	 * public UpravljanjeProjekcijama createPara(Date datumProjekcije,
	 * UpravljanjeProjekcijama.Type tip, UpravljanjeProjekcijama.Status status,
	 * String bioskopId, String bioskopNaziv, String salaId, String salaOznaka,
	 * String filmId, String filmNaziv) {
	 * 
	 * System.out.println("Create sa parametrima");
	 * 
	 * UpravljanjeProjekcijama entity = new UpravljanjeProjekcijama(null,
	 * datumProjekcije, tip, status, bioskopId, bioskopNaziv, salaId, salaOznaka,
	 * filmId, filmNaziv);
	 * 
	 * entity = upravljanjeRepository.save(entity);
	 * 
	 * return entity; }
	 */

	public Projekcije update(String id, Projekcije newEntity, String sessionId) {

		// ako korisnik nije tipa admin, prekini aktivnost
		if (!isAdmin(userServiceClient.getTypeBySessionId(sessionId))) {
			return null;
		}

		Projekcije entity = projekcijeRepository.findOne(id);
		// ako nije pronasao entitet, prekini aktivnost
		if (entity == null) {
			return null;
		}

		try {
			BeanUtils.copyProperties(newEntity, entity, "id");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}

		return projekcijeRepository.save(entity);
	}

	public boolean delete(String id, String sessionId) {
		// ako korisnik nije tipa admin, prekini aktivnost
		if (!isAdmin(userServiceClient.getTypeBySessionId(sessionId))) {
			return false;
		}

		projekcijeRepository.delete(id);

		return true;
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

	/* USING LOAD-BALANCING - USER*/
	/**
	 * Method checks if the given user is registered and active We use Ribbon and
	 * Feign to get data from user-service, load-balancing
	 * 
	 * @param username
	 * @return if user exists
	 */
	@HystrixCommand(fallbackMethod = "fallbackGetType")
	public String getType(String sessionId) {
		/* USING LOAD-BALANCING */
		return userServiceClient.getTypeBySessionId(sessionId);
	}

	public String fallbackGetType(String username) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return "";
	}
}
