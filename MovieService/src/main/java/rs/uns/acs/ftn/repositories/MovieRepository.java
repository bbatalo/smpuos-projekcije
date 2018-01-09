package rs.uns.acs.ftn.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import rs.uns.acs.ftn.models.Actor;
import rs.uns.acs.ftn.models.Movie;
import rs.uns.acs.ftn.models.Category;

public interface MovieRepository extends MongoRepository<Movie, String> {
	
	List<Movie> findAll();
	
	Movie findById(String movieId);
	
	List<Movie> findByName (String name);
	
	List<Movie> findByDescription(String description);
	
	List<Movie> findByLength(Long length);
	
	List<Movie> findByCategory(Category category);
	
	List<Movie> findByRatingAvg(Double rating);
	
	List<Movie> findByPremiereBetween(Date start, Date end);
	
	Page<Movie> findAll(Pageable pageable);
	
	
	List<Movie> findByActors(List<Actor> actors);
	
	@Query("{'director.firstName': ?0, 'director.lastName': ?1}")
	List<Movie> findAllByDirectorFirstNameAndLastName(String firstName, String lastName);
	
	@Query("{'actors.firstName': ?0, 'actors.lastName': ?1}")
	List<Movie> findAllByActor(String firstName, String lastName);

}
