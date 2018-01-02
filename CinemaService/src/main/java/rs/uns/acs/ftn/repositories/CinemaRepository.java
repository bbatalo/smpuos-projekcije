package rs.uns.acs.ftn.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.uns.acs.ftn.models.Cinema;

public interface CinemaRepository extends MongoRepository<Cinema, String> {
	
	Cinema findById(String id);
	
	Cinema findByName(String name);

}
