package rs.uns.acs.ftn.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.dto.CinemaHallDTO;
import rs.uns.acs.ftn.models.Cinema;
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
	
	@RequestMapping("/hello")
	public String Hello() {
		return "hello";
	}
	

	@RequestMapping(method = RequestMethod.POST, 
					consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> save(@RequestParam(name = "sessionId") String sessionId,
									@RequestBody Cinema newCinema) {
		
		if (!isAdmin(cinemaService.getUserType(sessionId))) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("not authorized", true);
			return m;
		}
		Cinema created = cinemaService.save(newCinema);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("created", created);
		return m;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@RequestParam(name = "sessionId") String sessionId,
									  @PathVariable String id) {
		

		if (!isAdmin(cinemaService.getUserType(sessionId))) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("not authorized", true);
			return m;
		}
		
		cinemaService.delete(id);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		return m;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> update(@PathVariable String id, @RequestBody Cinema newEntity) {
		
		Cinema updated = cinemaService.update(id, newEntity);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("updated", updated);
		return m;
	}
	
	@RequestMapping(value = "/cinemas_by_name", method = RequestMethod.GET)
	public Map<String, Object> findAllByName(@RequestParam(name = "name") String name) {
		
		List<Cinema> cinemas = cinemaService.findAllByName(name);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("cinemas", cinemas);
		return m;
	}
	
	@RequestMapping(value = "/cinemas_by_location", method = RequestMethod.GET)
	public Map<String, Object> findAllByLocation(@RequestParam(name = "x") Double x,
												 @RequestParam(name = "y") Double y,
												 @RequestParam(name = "dist", defaultValue = "1") Double distance) {
		
		
		List<Cinema> cinemas = cinemaService.findAllByLocation(x, y, distance);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("cinemas", cinemas);
		return m;
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
