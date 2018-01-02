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
	
	List<User> findByFirstNameNotNull();
	
	List<User> findByFirstNameNull();
	
	List<User> findByUserLocationNear(Point point, Distance distance);
	
	List<User> findByUserLocationWithin(GeoJsonPolygon polygon);
	
	List<User> findByUserStatus(User.UserStatus userStatus);
	
	List<User> findByDateOfBirthBetween(Date start, Date end);

	User findByUserName(String userName);
	
	User findById(String userId);
	
	User findByIdAndUserStatus(String userId, User.UserStatus userStatus);
	
	User findByUserNameAndUserStatus(String userName, User.UserStatus userStatus);
	
	User findByUserNameAndUserType(String userName, User.UserType userType);
	
}
