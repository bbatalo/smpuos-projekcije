package rs.uns.acs.ftn.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import rs.uns.acs.ftn.models.User;
import rs.uns.acs.ftn.repositories.UserRepository;

@Service
public class UserService extends AbstractCRUDService<User, String>{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository){
		super(userRepository);
		this.userRepository = userRepository;
	}
	
	public User login(String username, String password){
		User.Status status = User.Status.ACTIVE;
		User user = userRepository.findByUsernameAndStatus(username, status);
		
		if(user != null){
			if(user.getPassword().equals(password)){
				return user;
			}
		}
		
		return null;
	}

	public User signUp(String username, String password, String firstName, String lastName,
			Date dateOfBirth, User.Gender gender, GeoJsonPoint location){
		User user = new User(
				null,
				firstName,
				lastName,
				username,
				password,
				new Date(),
				dateOfBirth,
				gender,
				location,
				User.Status.ACTIVE,
				User.Type.REGISTERED);
				
		user = userRepository.save(user);
		
		return user;
	}
	
	public void activateUser(String username, String requesterId) {
		User requester = userRepository.findById(requesterId);
		
		if (requester.getType() == User.Type.ADMINISTRATOR) {
			User.Status oldStatus = User.Status.INACTIVE;
			User user = userRepository.findByUsernameAndStatus(username, oldStatus);
			
			if (user != null) {
				User.Status newStatus = User.Status.ACTIVE;
				
				user.setStatus(newStatus);
			
				userRepository.save(user);
			}
		}
	}
	
	public void deactivateUser(String username, String requesterId) {
		User requester = userRepository.findById(requesterId);
		
		if (requester.getType() == User.Type.ADMINISTRATOR) {
			User.Status oldStatus = User.Status.ACTIVE;
			User user = userRepository.findByUsernameAndStatus(username, oldStatus);
			
			if (user != null) {
				User.Status newStatus = User.Status.INACTIVE;
				
				user.setStatus(newStatus);
			
				userRepository.save(user);
			}
		}
	}
	
	public List<User> findByStatus(User.Status status, String requesterId){
		User requester = userRepository.findById(requesterId);
		
		if (requester.getType() == User.Type.ADMINISTRATOR) {
			return userRepository.findByStatus(status);
		} else {
			return new ArrayList<User>();
		}
	}
	
	public List<User> findByFirstName(String firstName){
		return userRepository.findByFirstName(firstName);
	}
	
	public Page<User> findByFirstName(String firstName, Pageable pageable){
		return userRepository.findByFirstName(firstName, pageable);
	}

	public User findByIdAndUserStatus(String id, User.Status status) {
		return userRepository.findByIdAndStatus(id, status);
	}

	public User findById(String id) {
		return userRepository.findById(id);
	}

	public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}
	
	public List<User> findByLocationNear(Point point, Distance distance) {
		return userRepository.findByLocationNear(point, distance);
	};

}
