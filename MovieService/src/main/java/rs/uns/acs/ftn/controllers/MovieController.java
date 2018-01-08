package rs.uns.acs.ftn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Context;

import rs.uns.acs.ftn.models.Actor;
import rs.uns.acs.ftn.models.Movie;
import rs.uns.acs.ftn.models.Rating;
import rs.uns.acs.ftn.models.dto.MovieDTO;
import rs.uns.acs.ftn.models.dto.RatingDTO;
import rs.uns.acs.ftn.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController extends AbstractRESTController<Movie, String> {
	
	@Autowired
	Environment environment;
	
	private MovieService movieService;
    
	@Autowired
	public MovieController(MovieService movieService) {
		super(movieService);
		this.movieService = movieService;
	}
	
	/* TODO: TEST ADMIN */
	@Transactional
	@RequestMapping(value = "/addMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO dto, @RequestParam("sessionId") String sessionId){
			Movie movie = new Movie();
			if (!isAdmin(movieService.getTypeBySessionIdLoad(sessionId))){
				return new ResponseEntity<Movie>(movie, HttpStatus.FORBIDDEN);			
			}
		
			movie = movieService.addMovie(dto.getName(), dto.getDescription(), dto.getLength(), dto.getDirector(), dto.getActors(), dto.getCategory(), dto.getRatings(), dto.getPremiere());
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getMovies",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public  Map<String, Object> getMovies(@Context HttpServletRequest request, @RequestParam(name = "page", defaultValue = "0") Integer page) {
		
			Page<Movie> movies = movieService.findAll(new PageRequest(page, pageSize));
			
			
			return prepareListPage(movies);
		
	}
	
	@Transactional
	@RequestMapping(value = "/getMovie",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Movie> getMovie(@RequestParam(name = "movie_id") String movieId){
			Movie movie = movieService.findById(movieId);
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
	/* TODO: TEST ADMIN */
	@Transactional
	@RequestMapping(value = "/updateMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Movie> updateMovie(@RequestBody MovieDTO dto, @RequestParam("sessionId") String sessionId){
			Movie movie = new Movie();
			if (!isAdmin(movieService.getTypeBySessionIdLoad(sessionId))){
				return new ResponseEntity<Movie>(movie, HttpStatus.FORBIDDEN);			
			}
			movie = movieService.updateMovie(dto.getId(), dto.getName(), dto.getDescription(), dto.getLength(), dto.getDirector(), dto.getActors(), dto.getCategory(), dto.getRatings(), dto.getPremiere());
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
	/* TODO: TEST ADMIN */
	@Transactional
	@RequestMapping(value = "/deleteMovie",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Boolean> deleteMovie(@RequestBody String movieId, @RequestParam("sessionId") String sessionId){

			if (!isAdmin(movieService.getTypeBySessionIdLoad(sessionId))){
				return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);			
			}
			Boolean removed = movieService.delete(movieId);
			return new ResponseEntity<Boolean>(removed, HttpStatus.OK);
	}
	
	/* TODO: TEST USER */
	@Transactional
	@RequestMapping(value = "/rateMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Movie> rateMovie(@RequestBody RatingDTO dto, @RequestParam("sessionId") String sessionId){
			Movie movie = new Movie();
			if (!isAdmin(movieService.getTypeBySessionIdLoad(sessionId))){
				return new ResponseEntity<Movie>(movie, HttpStatus.FORBIDDEN);			
			}
			Rating rating = new Rating(dto.getValue(), dto.getPosterId());
			movie = movieService.rateMovie(dto.getMovieId(), rating);
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/getMoviesByYear",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getMoviesByYear(@RequestParam(name = "year_str") String  yearStr){
			try {
				Integer year = new Integer(yearStr);
				List<Movie> movies = movieService.findByYear(year);
				return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
			} catch (NumberFormatException e){
				e.printStackTrace();
				return null;
			}
	}
	
	@Transactional
	@RequestMapping(value = "/getTrending",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getTrending(){
		Integer year = Calendar.getInstance().get(Calendar.YEAR);

			List<Movie> movies = movieService.findByYear(year);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/getByRating",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByRating(@RequestParam(name = "rating") String rating){
			double ratingAvg = new Double(rating);
			List<Movie> movies = movieService.findByRatingAvg(ratingAvg);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/getByDirector",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByDirector(@RequestParam(name = "director_name") String directorName,
			  					     @RequestParam(name = "director_surname") String directorSurname){

			List<Movie> movies = movieService.findByDirector(directorName, directorSurname);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}
	
	@Transactional
	@RequestMapping(value = "/getByActor",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByActor(@RequestParam(name = "actor_name") String actorName,
			 					  @RequestParam(name = "actor_surname") String actorSurname){
			List<Movie> movies = movieService.findByActor(actorName, actorSurname);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}
	
	@Transactional
	@RequestMapping(value = "/getByCategory",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByCategory(@RequestParam(name = "category") String category){

			Movie.Category cat = Movie.Category.valueOf(category);
			List<Movie> movies = movieService.findByCategory(cat);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/getByActors",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByActors(@RequestBody List<Actor> actors){

			List<Movie> movies = movieService.findByActors(actors);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

	}
	
	@Transactional
	@RequestMapping(value = "/getMovieName",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> getMovieName(@RequestBody String movieId){
			String name = movieService.findMovieName(movieId);
			return new ResponseEntity<String>(name, HttpStatus.OK) ;

	}
	
	@FeignClient("user-service") // the application.name for the user service
	public interface UserServiceClient {
		@RequestMapping(value = "users/get_type_by_session_id", method = RequestMethod.GET) // the endpoint which will be balanced over
		
		String getTypeBySessionId(@RequestParam(name = "sessionId") String sessionId);// the method specification must
																						// be the same as for
																						// users/get_type_by_session_id
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

}
