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
	public ResponseEntity<Movie> addMovie(MovieDTO dto){
		if(dto!=null){
			Movie movie = movieService.addMovie(dto.getName(), dto.getDescription(), dto.getLength(), dto.getDirector(), dto.getActors(), dto.getCategory(), dto.getRatings(), dto.getPremiere());
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
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
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Movie> getMovie(String movieId){
		if (movieId!=null){
			Movie movie = movieService.findById(movieId);
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/updateMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Movie> updateMovie(MovieDTO dto){
		if(dto!=null){
			Movie movie = movieService.updateMovie(dto.getId(), dto.getName(), dto.getDescription(), dto.getLength(), dto.getDirector(), dto.getActors(), dto.getCategory(), dto.getRatings(), dto.getPremiere());
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/deleteMovie",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Boolean> deleteMovie(String movieId){
		if (movieId!=null){
			Boolean removed = movieService.delete(movieId);
			return new ResponseEntity<Boolean>(removed, HttpStatus.OK);
		}
		return null;
	}
	
	
	@Transactional
	@RequestMapping(value = "/rateMovie",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Movie> rateMovie(RatingDTO dto){
		if(dto!=null){
			Rating rating = new Rating(dto.getValue(), dto.getPosterId());
			Movie movie = movieService.rateMovie(dto.getMovieId(), rating);
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getMoviesByYear",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getMoviesByYear(String yearStr){
		if(yearStr!=null){
			try {
				Integer year = new Integer(yearStr);
				List<Movie> movies = movieService.findByYear(year);
				return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
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
	public ResponseEntity<List<Movie>> getTrending(){
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		if (year!=null) {
			List<Movie> movies = movieService.findByYear(year);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByRating",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByRating(String rating){
		if(rating!=null){
			double ratingAvg = new Double(rating);
			List<Movie> movies = movieService.findByRatingAvg(ratingAvg);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByDirector",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByDirector(NameDTO dto){
		if(dto!=null){
			List<Movie> movies = movieService.findByDirector(dto.getFirstName(), dto.getLastName());
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByActor",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByActor(NameDTO dto){
		if(dto!=null){
			List<Movie> movies = movieService.findByActor(dto.getFirstName(), dto.getLastName());
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByCategory",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByCategory(String category){
		if(category!=null){
			Movie.Category cat = Movie.Category.valueOf(category);
			List<Movie> movies = movieService.findByCategory(cat);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getByActors",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Movie>> getByActors(List<Actor> actors){
		if(actors!=null){
			List<Movie> movies = movieService.findByActors(actors);
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/getMovieName",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> getMovieName(String movieId){
		if (movieId!=null){
			Movie movie = movieService.findById(movieId);
			String movieName = movie.getName();
			return new ResponseEntity<String>(movieName, HttpStatus.OK);
		}
		return null;
	}

}
