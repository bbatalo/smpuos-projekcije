package rs.uns.acs.ftn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.ws.rs.core.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Context;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import rs.uns.acs.ftn.models.Actor;
import rs.uns.acs.ftn.models.Movie;
import rs.uns.acs.ftn.models.Rating;
import rs.uns.acs.ftn.models.dto.MovieDTO;
import rs.uns.acs.ftn.models.dto.NameDTO;
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
	public Movie addMovie(MovieDTO dto){
		if(dto!=null){
			Movie movie = movieService.addMovie(dto.getName(), dto.getDescription(), dto.getLength(), dto.getDirector(), dto.getActors(), dto.getCategory(), dto.getRatings(), dto.getPremiere());
			return movie;
		}
		return null;
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
		if (movieId!=null){
			Movie movie = movieService.findById(movieId);
			return movie;
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/updateMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public Movie updateMovie(MovieDTO dto){
		if(dto!=null){
			Movie movie = movieService.updateMovie(dto.getId(), dto.getName(), dto.getDescription(), dto.getLength(), dto.getDirector(), dto.getActors(), dto.getCategory(), dto.getRatings(), dto.getPremiere());
			return movie;
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/deleteMovie",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public Boolean deleteMovie(String movieId){
		if (movieId!=null){
			Boolean removed = movieService.delete(movieId);
			return removed;
		}
		return null;
	}
	
	
	@Transactional
	@RequestMapping(value = "/rateMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public Movie rateMovie(RatingDTO dto){
		if(dto!=null){
			Rating rating = new Rating(dto.getValue(), dto.getPosterId());
			Movie movie = movieService.rateMovie(dto.getMovieId(), rating);
			return movie;
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getMoviesByYear",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getMoviesByYear(@RequestParam(name = "year_str") String  yearStr){
		if(yearStr!=null){
			try {
				Integer year = new Integer(yearStr);
				List<Movie> movies = movieService.findByYear(year);
				return movies;
			} catch (NumberFormatException e){
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	@Transactional
	@RequestMapping(value = "/getTrending",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getTrending(){
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		if (year!=null) {
			List<Movie> movies = movieService.findByYear(year);
			return movies;
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByRating",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByRating(@RequestParam(name = "rating") String rating){
		if(rating!=null){
			double ratingAvg = new Double(rating);
			List<Movie> movies = movieService.findByRatingAvg(ratingAvg);
			return movies;
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByDirector",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByDirector(@RequestParam(name = "director_name") String directorName,
			  					     @RequestParam(name = "director_surname") String directorSurname){
		if(directorName!=null && directorSurname!=null){
			List<Movie> movies = movieService.findByDirector(directorName, directorSurname);
			return movies;
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByActor",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByActor(@RequestParam(name = "actor_name") String actorName,
			 					  @RequestParam(name = "actor_surname") String actorSurname){
		if(actorName!=null && actorSurname!=null){
			List<Movie> movies = movieService.findByActor(actorName, actorSurname);
			return movies;
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByCategory",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByCategory(@RequestParam(name = "category") String category){
		if(category!=null){
			Movie.Category cat = Movie.Category.valueOf(category);
			List<Movie> movies = movieService.findByCategory(cat);
			return movies;
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByActors",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public List<Movie> getByActors(List<Actor> actors){
		if(actors!=null){
			List<Movie> movies = movieService.findByActors(actors);
			return movies;
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getMovieName",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.TEXT_PLAIN)
	public String getMovieName(String movieId){
		if (movieId!=null){
			Movie movie = movieService.findById(movieId);
			String movieName = movie.getName();
			return movieName;
		}
		return null;
	}

}
