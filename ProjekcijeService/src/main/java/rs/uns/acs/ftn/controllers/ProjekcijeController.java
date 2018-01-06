package rs.uns.acs.ftn.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
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

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Projekcije> create(
			@RequestBody Projekcije newProj,
			@RequestParam("sessionId") String sessionId) {

		Projekcije response = projekcijeService.createProjection(newProj, sessionId);
		
		if (response != null) {
			return new ResponseEntity<Projekcije>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<Projekcije>(response, HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Projekcije> update(
			@RequestBody Projekcije newProj, 
			@RequestParam("sessionId") String sessionId,
			@PathVariable("id") String id) {

		Projekcije response = projekcijeService.update(id, newProj, sessionId);
		
		if (response != null) {
			return new ResponseEntity<Projekcije>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<Projekcije>(response, HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Boolean delete(@RequestParam("username") String username, @PathVariable("id") String id) {
		return projekcijeService.delete(id, username);
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
/*
	@FeignClient("user-service") // the application.name for the user service
	public interface UserServiceClient {
		@RequestMapping(value = "users/get_type", method = RequestMethod.GET) // the endpoint which will be balanced over
		String getType(@RequestParam(name = "username") String username);// the method specification must be the same as
																			// for users/checkUser
	}
*/	
	@FeignClient("user-service") // the application.name for the user service
	public interface UserServiceClient {
		@RequestMapping(value = "users/get_type_by_session_id", method = RequestMethod.GET) // the endpoint which will be balanced over
		String getTypeBySessionId(@RequestParam(name = "sessionId") String sessionId);// the method specification must be the same as
																			// for users/checkUser
	}	

	// TO DO...
	@FeignClient("cinema-service")
	public interface CinemaServiceClient {
		@RequestMapping(value = "products/checkProductsFromCart", method = RequestMethod.GET)
		Boolean checkProductsFromCart(@RequestBody List<String> ids);
	}

	// TO DO...
	@FeignClient("movie-service")
	public interface MovieServiceClient {
		@RequestMapping(value = "products/checkProductsFromCart", method = RequestMethod.GET)
		Boolean checkProductsFromCart(@RequestBody List<String> ids);
	}
	
	
}
