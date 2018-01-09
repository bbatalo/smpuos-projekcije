package rs.uns.acs.ftn.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.dto.RatingDTO;
import rs.uns.acs.ftn.models.Cinema;
import rs.uns.acs.ftn.models.Rating;
import rs.uns.acs.ftn.services.CinemaService;

@RestController
@RequestMapping("cinemas")
public class CinemaController extends AbstractRESTController {

	@Autowired
	Environment environment;
	
	private CinemaService cinemaService;
	
	@Autowired
	public CinemaController(CinemaService cinemaService) {
		super(cinemaService);
		this.cinemaService = cinemaService;
	}
	
	@RequestMapping(method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> save(@RequestParam(name = "sessionId") String sessionId,
							@RequestBody Cinema newCinema) {

		if (!isAdmin(cinemaService.getUserType(sessionId))) {
			return new ResponseEntity<Object>(newCinema, HttpStatus.FORBIDDEN);
		}
		
		if (!cinemaService.isCinemaValid(newCinema)) {
			return new ResponseEntity<Object>("not valid", HttpStatus.OK);
		}
		
		Cinema created = cinemaService.save(newCinema);


		return new ResponseEntity<Object>(created, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestParam(name = "sessionId") String sessionId,
									  @PathVariable String id) {
		

		if (!isAdmin(cinemaService.getUserType(sessionId))) {
			return new ResponseEntity<String>("Not authorized", HttpStatus.FORBIDDEN);
		}
		
		cinemaService.delete(id);
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> update(@PathVariable String id, 
									     @RequestBody Cinema newCinema,
									     @RequestParam(name = "sessionId") String sessionId) {
		
		if (!isAdmin(cinemaService.getUserType(sessionId))) {
			return new ResponseEntity<Object>(newCinema, HttpStatus.FORBIDDEN);
		}
		
		if (!cinemaService.isCinemaValid(newCinema)) {
			return new ResponseEntity<Object>("not valid", HttpStatus.OK);
		}
		
		Cinema updated = cinemaService.update(id, newCinema);

		return new ResponseEntity<Object>(updated, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cinemas_by_name", method = RequestMethod.GET)
	public ResponseEntity<List<Cinema>> findAllByName(@RequestParam(name = "name") String name) {
		
		List<Cinema> cinemas = cinemaService.findAllByName(name);

		return new ResponseEntity<List<Cinema>>(cinemas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cinemas_by_location", method = RequestMethod.GET)
	public ResponseEntity<List<Cinema>> findAllByLocation(@RequestParam(name = "x") Double x,
												 @RequestParam(name = "y") Double y,
												 @RequestParam(name = "dist", defaultValue = "1") Double distance) {
		
		List<Cinema> cinemas = cinemaService.findAllByLocation(x, y, distance);
		
		return new ResponseEntity<List<Cinema>>(cinemas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rate", method = RequestMethod.POST)
	public ResponseEntity<Object> rateCinema(@RequestParam(name = "sessionId") String sessionId,
											 @RequestBody RatingDTO dto) {
		
		if (!isRegistered(cinemaService.getUserType(sessionId))) {
			return new ResponseEntity<Object>(cinemaService.findOne(dto.getCinemaId()), HttpStatus.FORBIDDEN);
		}
		
		Rating rating = new Rating(dto.getValue(), dto.getUserId());
		if (!cinemaService.isRatingValid(rating)) {
			return new ResponseEntity<Object>("not valid", HttpStatus.OK);
		}
		
		Cinema cinema = cinemaService.rate(dto.getCinemaId(), rating);
		
		if (cinema == null)
			return new ResponseEntity<Object>("cinema not found", HttpStatus.OK);
		
		return new ResponseEntity<Object>(cinema, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ranking", method = RequestMethod.GET)
	public ResponseEntity<List<Cinema>> rankings() {
		
		List<Cinema> cinemas = cinemaService.rankings();
		
		return new ResponseEntity<List<Cinema>>(cinemas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cinema_hall_name", method = RequestMethod.GET)
	public Map<String, Object> getCinemaHallName(@RequestParam(name = "cinemaId") String cinemaId,
										   @RequestParam(name = "hallId") String hallId) {
		
		Map<String, Object> m = cinemaService.findCinemaHall(cinemaId, hallId);
		return m;
	}
	
	@FeignClient("user-service")
	public interface UserServiceClient {
		@RequestMapping(value = "users/get_type_by_session_id", method = RequestMethod.GET)
		
		String getTypeBySessionId(@RequestParam(name = "sessionId") String sessionId);
	}
	
	
	//private methods
	
	private boolean isAdmin(String type) {
		if (type == null) {
			return false;
		}
		
		if (type.equals("ADMINISTRATOR")) {
			return true;
		}
		
		return false;
	}
	
	private boolean isRegistered(String type) {
		if (type == null) {
			return false;
		}
		
		if (type.equals("REGISTERED")) {
			return true;
		}
		
		return false;
	}
}
