package rs.uns.acs.ftn.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import rs.uns.acs.ftn.models.Cinema;

public interface CinemaRepository extends MongoRepository<Cinema, String> {
	
	Page<Cinema> findAll(Pageable page);
	
	Cinema findById(String id);
	
	Cinema findByName(String name);
	
	Cinema save(Cinema cinema);
	
	@Query("{'name': {$regex: ?0}}")
	List<Cinema> findAllByName(String name);

}
