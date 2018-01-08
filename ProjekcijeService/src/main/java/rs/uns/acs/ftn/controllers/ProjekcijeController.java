package rs.uns.acs.ftn.controllers;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.models.Projekcije;
import rs.uns.acs.ftn.services.ProjekcijeService;

@RestController
@RequestMapping("projekcije")
public class ProjekcijeController extends AbstractRESTController<Projekcije, String> {

	@Autowired
	Environment environment;

	private ProjekcijeService projekcijeService;

	@Autowired
	public ProjekcijeController(ProjekcijeService projekcijeService) {
		super(projekcijeService);
		this.projekcijeService = projekcijeService;
	}

	/* ------------------------------ ADMIN ---------------------------- */

	@RequestMapping(method = RequestMethod.POST, consumes =	MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Projekcije> create(@RequestBody Projekcije newEntity,
			@RequestParam("sessionId") String sessionId) {

		// ako korisnik nije tipa admin, prekini aktivnost
		if (!isAdmin(projekcijeService.getTypeBySessionIdLoad(sessionId))){
			return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);			
		}
	
		// preuzmi nazive bisokopa i sale
		Map<String, Object> responseCinemaHall = projekcijeService.getCinemaHallName(newEntity.getCinemaId(), newEntity.getHallId());
		newEntity.setCinemaName((String) responseCinemaHall.get("cinemaName"));
		newEntity.setHallLabel((String) responseCinemaHall.get("hallLabel"));
		
		// preuzmi naziv filma
		ResponseEntity<String> responseMovieName = projekcijeService.getMovieNameLoad(newEntity.getMovieId());
		newEntity.setMovieName(responseMovieName.getBody());
				
		// posalji servisu da sacuva
		projekcijeService.save(newEntity);
		
		return new ResponseEntity<Projekcije>(newEntity, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes =	MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Projekcije> update(@RequestBody Projekcije newEntity,
			@RequestParam("sessionId") String sessionId, @PathVariable("id") String id) {

		// ako korisnik nije tipa admin, prekini aktivnost
		if (!isAdmin(projekcijeService.getTypeBySessionIdLoad(sessionId))){
			return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);			
		}		
		
		Projekcije oldEntity = projekcijeService.findOne(id);
		// ako nije pronasao tu projekciju, prekini aktivnost
		if (oldEntity == null) {
			return new ResponseEntity<Projekcije>(newEntity, HttpStatus.FORBIDDEN);
		}		

		if(newEntity.getDate() == null){
			newEntity.setDate(oldEntity.getDate());
		}	
		
		if(newEntity.getStatus() == null){
			newEntity.setStatus(oldEntity.getStatus());
		}
		
		if(newEntity.getType() == null){
			newEntity.setType(oldEntity.getType());
		}
		
		// ako se menja bioskop (da bi se promenila sala potreban je kljuc bioskopa)
		if (newEntity.getCinemaId() == null) {
			newEntity.setCinemaId(oldEntity.getCinemaId());
			newEntity.setCinemaName(oldEntity.getCinemaName());
			newEntity.setHallId(oldEntity.getHallId());
			newEntity.setHallLabel(oldEntity.getHallLabel());
		}
		else
		{
			Map<String, Object> responseCinemaHall = projekcijeService.getCinemaHallName(newEntity.getCinemaId(), newEntity.getHallId());
			newEntity.setCinemaName((String) responseCinemaHall.get("cinemaName"));
			newEntity.setHallLabel((String) responseCinemaHall.get("hallLabel"));				
		}		

		// ako se menja film
		if (newEntity.getMovieId() == null) {
			newEntity.setMovieId(oldEntity.getMovieId());
			newEntity.setMovieName(oldEntity.getMovieName());			
		}
		else {
			ResponseEntity<String> responseMovieName = projekcijeService.getMovieNameLoad(newEntity.getMovieId());
			newEntity.setMovieName(responseMovieName.getBody());
		}		
		
		Projekcije izmenjena = projekcijeService.update(id, newEntity);
		
		return new ResponseEntity<Projekcije>(izmenjena, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@RequestParam("sessionId") String sessionId, @PathVariable("id") String id) {

		// ako korisnik nije tipa admin, prekini aktivnost
		if (!isAdmin(projekcijeService.getTypeBySessionIdLoad(sessionId))) {
			return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
		}

		projekcijeService.delete(id);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
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

	/* ------------------------------ KORISNIK ------------------------- */

	@RequestMapping(value = "search/premiere", method = RequestMethod.GET)
	public List<Projekcije> isPremiere() {
		return projekcijeService.isPremiere();
	}

	@RequestMapping(value = "search/active", method = RequestMethod.GET)
	public List<Projekcije> isActive() {
		return projekcijeService.isActive();
	}

	@RequestMapping(value = "search/findByCinemaName", method = RequestMethod.GET)
	public List<Projekcije> findByCinemaName(@RequestParam(name = "name") String cinemaName) {
		return projekcijeService.findByCinemaName(cinemaName);
	}

	@RequestMapping(value = "search/findByCinemaId", method = RequestMethod.GET)
	public List<Projekcije> findByCinemaId(@RequestParam(name = "id") String cinemaId) {
		return projekcijeService.findByCinemaId(cinemaId);
	}

	@RequestMapping(value = "search/findByMovieName", method = RequestMethod.GET)
	public List<Projekcije> findByMovieName(@RequestParam(name = "name") String movieName) {
		return projekcijeService.findByMovieName(movieName);
	}

	@RequestMapping(value = "search/findByMovieId", method = RequestMethod.GET)
	public List<Projekcije> findByMovieId(@RequestParam(name = "id") String movieId) {
		return projekcijeService.findByMovieId(movieId);
	}

	/* ------------------------------ FEIGN ---------------------------- */

	@FeignClient("user-service") // the application.name for the user service
	public interface UserServiceClient {
		@RequestMapping(value = "users/get_type_by_session_id", method = RequestMethod.GET) // the endpoint which will be balanced over
		
		String getTypeBySessionId(@RequestParam(name = "sessionId") String sessionId);// the method specification must
																						// be the same as for
																						// users/get_type_by_session_id
	}

	@FeignClient("movie-service")
	public interface MovieServiceClient {
		@RequestMapping(value = "movies/getMovieName", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN, produces = MediaType.TEXT_PLAIN)

		ResponseEntity<String> getMovieName(@RequestBody String movieId);
	}

	@FeignClient("cinema-service")
	public interface CinemaServiceClient {
		@RequestMapping(value = "cinemas/cinema_hall_name", method = RequestMethod.GET)

		Map<String, Object> getCinemaHallName(@RequestParam(name = "cinemaId") String cinemaId,
				@RequestParam(name = "hallId") String hallId);
	}

}
