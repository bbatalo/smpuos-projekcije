package rs.uns.acs.ftn.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import rs.uns.acs.ftn.models.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	List<User> findByFirstName(String firstName);
	
	List<User> findByFirstName(String firstName, Sort sort);
	
	Page<User> findByFirstName(String firstName, Pageable pageable);
	
	List<User> findByFirstNameAndLastName(String firstName, String lastName);	
	
	List<User> findByLocationNear(Point point, Distance distance);
	
	List<User> findByLocationWithin(GeoJsonPolygon polygon);
	
	List<User> findByStatus(User.Status status);
	
	List<User> findByDateOfBirthBetween(Date start, Date end);

	User findByUsername(String username);
	
	User findById(String id);
	
	User findBySessionId(String sessionId);
	
	User findByIdAndStatus(String userId, User.Status status);
	
	User findByUsernameAndStatus(String username, User.Status status);
	
	User findByUsernameAndType(String username, User.Type type);
	
}
