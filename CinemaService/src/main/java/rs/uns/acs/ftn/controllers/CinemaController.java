package rs.uns.acs.ftn.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Map<String, Object> save(@RequestBody Cinema newCinema) {
		
		//autentifikovati i iskoristiti CinemaAuthDTO
		
		Cinema created = cinemaService.save(newCinema);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("created", created);
		return m;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable String id) {
		
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
	
	
}
