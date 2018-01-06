package rs.uns.acs.ftn.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
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
//	
//	@Query("{'address.location': {'$nearSphere': [?0, ?1], '$maxDistance': ?2}}")
//	List<Cinema> findByLocationNear(Double x, Double y, Distance distance);

	List<Cinema> findByLocationNear(Point point, Distance distance);
	
}
