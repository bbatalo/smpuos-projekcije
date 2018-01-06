package rs.uns.acs.ftn.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import rs.uns.acs.ftn.models.Cinema;

public interface CinemaRepository extends MongoRepository<Cinema, String> {
	
	Page<Cinema> findAll(Pageable page);
	
	Cinema findById(String id);
	
	Cinema findByName(String name);
	
	Cinema save(Cinema cinema);

}
