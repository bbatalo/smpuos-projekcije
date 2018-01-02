package rs.uns.acs.ftn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
