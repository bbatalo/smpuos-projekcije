package rs.uns.acs.ftn.services;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.uns.acs.ftn.models.Actor;
import rs.uns.acs.ftn.models.Director;
import rs.uns.acs.ftn.models.Movie;
import rs.uns.acs.ftn.models.Rating;
import rs.uns.acs.ftn.repositories.MovieRepository;

@Service
public class MovieService extends AbstractCRUDService<Movie, String>{
	
	private MovieRepository movieRepository;
	
	@Autowired
	public MovieService(MovieRepository movieRepository){
		super(movieRepository);
		this.movieRepository = movieRepository;
	}
	
	public Movie addMovie(String name, String description, Long length, Director director, List<Actor> actors,
			Movie.Category category, List<Rating> ratings, Date premiere) {
		int counter = 0;
		int sum = 0;
		for (Rating rat : ratings){
			counter+=1;
			sum+=rat.getValue();
		}
		double ratingAvg = sum/counter;
		Movie movie = new Movie(
					name,
					description,
					length,
					director,
					actors,
					category,
					ratings,
					ratingAvg,
					premiere);
		return movieRepository.save(movie);
	}
	
	public Movie rateMovie(String id, Rating rating){
		Movie movie = movieRepository.findById(id);
		boolean contains = false;
		for (Rating movieRating : movie.getRatings()){
			if (movieRating.getPosterId()==rating.getPosterId()){
				contains = true;
				movieRating.setValue(rating.getValue());
				double ratingAvg = movie.calculateRating();
				movie.setRatingAvg(ratingAvg);
				movieRepository.save(movie);
			}
		}
		if(!contains){
			movie.getRatings().add(rating);
			double ratingAvg = movie.calculateRating();
			movie.setRatingAvg(ratingAvg);
			movieRepository.save(movie);
		}
		return movie;
	}
	
	public Movie updateMovie(String id, String name, String description, Long length, Director director, List<Actor> actors,
			Movie.Category category, List<Rating> ratings, Date premiere){
		Movie movie = movieRepository.findById(id);
		
		if(name!=null && name!=""){
			movie.setName(name);
		}
		
		if(description!=null && description!=""){
			movie.setDescription(description);
		}
		
		if(length!=null){
			movie.setLength(length);
		}
		
		if(director!=null && (director.getFirstName()!=null || director.getLastName()!=null)){
			movie.setDirector(director);
		}
		
		if(actors.size()!=0 && actors!=null){
			movie.setActors(actors);
		}
		
		if(category!=null){
			movie.setCategory(category);
		}
		
		if(ratings.size()!=0 && ratings!=null){
			movie.setRatings(ratings);
			double ratingAvg = movie.calculateRating();
			movie.setRatingAvg(ratingAvg);
		}
		
		if(premiere!=null){
			movie.setPremiere(premiere);
		}
		
		movieRepository.save(movie);
		
		return movie;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Movie> findAll(){
		List<Movie> movies = movieRepository.findAll();
		
		Collections.sort(movies, new Comparator() {
            @Override
            public int compare(Object movieOne, Object gameTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((Movie)movieOne).getName().toUpperCase()
                        .compareTo(((Movie)gameTwo).getName().toUpperCase());
            }
        }); 
		
		return movies;
	}
	
	public Long deleteMovieById(Long id){
		return movieRepository.deleteMovieById(id);
	}
	
	public Movie findById(String movieId){
		return movieRepository.findById(movieId);
	}
	
	public List<Movie> findByName (String name){
		return movieRepository.findByName(name);
	}
	
	public List<Movie> findByDescription(String description){
		return movieRepository.findByDescription(description);
	}
	
	public List<Movie> findByLength(Long length){
		return movieRepository.findByLength(length);
	}
	
	public List<Movie> findByDirector(String firstName, String lastName){
		return movieRepository.findAllByDirectorFirstNameAndLastName(firstName, lastName);
	}
	
	public List<Movie> findByActor(String firstName, String lastName){
		return movieRepository.findAllByActor(firstName, lastName);
	}
	
	public List<Movie> findByCategory(Movie.Category category){
		return movieRepository.findByCategory(category);
	}
	
	public List<Movie> findByRatingAvg(Double rating){
		return movieRepository.findByRatingAvg(rating);
	}
	
	public List<Movie> findByPremiereBetween(Date start, Date end){
		return movieRepository.findByPremiereBetween(start, end);
	}
	
	public List<Movie> findByActors(List<Actor> actors){
		return movieRepository.findByActors(actors);
	}
	
	public Page<Movie> findAllPageable(Pageable pageable){
		return movieRepository.findAll(pageable);
	}
	
	public List<Movie> findByYear(Integer year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DAY_OF_YEAR, 1);    
		Date start = cal.getTime();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 11); // 11 = december
		cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
		
		Date end = cal.getTime();
		
		return movieRepository.findByPremiereBetween(start, end);
	}
	
	public String findMovieName (String movieId){
		Movie movie = movieRepository.findById(movieId);
		if (movie!=null){
			return movie.getName();
		} else {
			return "";
		}
	}

}
