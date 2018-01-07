package rs.uns.acs.ftn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
	
	@Transactional
	@RequestMapping(value = "/addMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public Movie addMovie(@RequestBody MovieDTO dto){
			Movie movie = movieService.addMovie(dto.getName(), dto.getDescription(), dto.getLength(), dto.getDirector(), dto.getActors(), dto.getCategory(), dto.getRatings(), dto.getPremiere());
			return movie;
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
	public Movie getMovie(@RequestParam(name = "movie_id") String movieId){
			Movie movie = movieService.findById(movieId);
			return movie;
	}
	
	@Transactional
	@RequestMapping(value = "/updateMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public Movie updateMovie(@RequestBody MovieDTO dto){
			Movie movie = movieService.updateMovie(dto.getId(), dto.getName(), dto.getDescription(), dto.getLength(), dto.getDirector(), dto.getActors(), dto.getCategory(), dto.getRatings(), dto.getPremiere());
			return movie;
	}
	
	@Transactional
	@RequestMapping(value = "/deleteMovie",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public Boolean deleteMovie(@RequestBody String movieId){
			Boolean removed = movieService.delete(movieId);
			return removed;
	}
	
	
	@Transactional
	@RequestMapping(value = "/rateMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public Movie rateMovie(@RequestBody RatingDTO dto){
			Rating rating = new Rating(dto.getValue(), dto.getPosterId());
			Movie movie = movieService.rateMovie(dto.getMovieId(), rating);
			return movie;
	}
	
	@Transactional
	@RequestMapping(value = "/getMoviesByYear",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getMoviesByYear(@RequestParam(name = "year_str") String  yearStr){
			try {
				Integer year = new Integer(yearStr);
				List<Movie> movies = movieService.findByYear(year);
				return movies;
			} catch (NumberFormatException e){
				e.printStackTrace();
				return null;
			}
	}
	
	@Transactional
	@RequestMapping(value = "/getTrending",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getTrending(){
		Integer year = Calendar.getInstance().get(Calendar.YEAR);

			List<Movie> movies = movieService.findByYear(year);
			return movies;
	}
	
	@Transactional
	@RequestMapping(value = "/getByRating",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByRating(@RequestParam(name = "rating") String rating){
			double ratingAvg = new Double(rating);
			List<Movie> movies = movieService.findByRatingAvg(ratingAvg);
			return movies;
	}
	
	@Transactional
	@RequestMapping(value = "/getByDirector",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByDirector(@RequestParam(name = "director_name") String directorName,
			  					     @RequestParam(name = "director_surname") String directorSurname){

			List<Movie> movies = movieService.findByDirector(directorName, directorSurname);
			return movies;

	}
	
	@Transactional
	@RequestMapping(value = "/getByActor",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByActor(@RequestParam(name = "actor_name") String actorName,
			 					  @RequestParam(name = "actor_surname") String actorSurname){
			List<Movie> movies = movieService.findByActor(actorName, actorSurname);
			return movies;

	}
	
	@Transactional
	@RequestMapping(value = "/getByCategory",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByCategory(@RequestParam(name = "category") String category){

			Movie.Category cat = Movie.Category.valueOf(category);
			List<Movie> movies = movieService.findByCategory(cat);
			return movies;
	}
	
	@Transactional
	@RequestMapping(value = "/getByActors",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByActors(@RequestBody List<Actor> actors){

			List<Movie> movies = movieService.findByActors(actors);
			return movies;

	}
	
	@Transactional
	@RequestMapping(value = "/getMovieName",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.TEXT_PLAIN)
	public String getMovieName(@RequestBody String movieId){

			Movie movie = movieService.findById(movieId);
			String movieName = movie.getName();
			return movieName;

	}

}
