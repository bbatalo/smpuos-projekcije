package rs.uns.acs.ftn.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.dto.CinemaAuthDTO;
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
	

//	@RequestMapping(method = RequestMethod.POST, 
//					consumes = { MediaType.APPLICATION_JSON_VALUE })
//	public Map<String, Object> save(@RequestBody Cinema newCinema) {
//		
//		Cinema created = cinemaService.save(newCinema);
//		Map<String, Object> m = new HashMap<String, Object>();
//		m.put("success", true);
//		m.put("created", created);
//		return m;
//	}
	
	@RequestMapping(method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> save(@RequestBody CinemaAuthDTO newCinema) {

		
		
		Cinema created = cinemaService.save(newCinema.getCinema());
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("created", created);
		return m;
	}
}
