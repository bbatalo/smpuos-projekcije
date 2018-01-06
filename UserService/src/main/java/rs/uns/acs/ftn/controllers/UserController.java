package rs.uns.acs.ftn.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.models.User;
import rs.uns.acs.ftn.services.UserService;

@RestController
@RequestMapping("users")
public class UserController extends AbstractRESTController<User, String>{
	
	@Autowired
	Environment environment;
	
	private UserService userService;
    
	@Autowired
	public UserController(UserService userService) {
		super(userService);
		this.userService = userService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(
			@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password){
		return userService.login(username, password);
	}
	
	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	public User signUp(
			@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "firstName") Optional<String> firstName,
			@RequestParam(name = "lastName") Optional<String> lastName,
			@RequestParam(name = "dateOfBirth") Optional<Date> dateOfBirth,
			@RequestParam(name = "gender") Optional<User.Gender> gender,
			@RequestParam(name = "location") Optional<GeoJsonPoint> location){
		return userService.signUp(username, password, firstName.orElse(null), lastName.orElse(null),
				dateOfBirth.orElse(null), gender.orElse(null), location.orElse(null));
	}
	
	@RequestMapping(value = "/activate_user", method = RequestMethod.POST)
	public void activateUser(
			@RequestParam(name = "username") String username,
			@RequestParam(name = "requester_id") String requesterId){
		userService.activateUser(username, requesterId);
	}
	
	@RequestMapping(value = "/deactivate_user", method = RequestMethod.POST)
	public void deactivateUser(
			@RequestParam(name = "username") String username,
			@RequestParam(name = "requester_id") String requesterId){
		userService.deactivateUser(username, requesterId);
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public List<User> findAllActive(
			@RequestParam(name = "requester_id") String requesterId){
		User.Status status = User.Status.ACTIVE;
		
		return userService.findByStatus(status, requesterId);
	}
	
	@RequestMapping(value = "/inactive", method = RequestMethod.GET)
	public List<User> findAllInactive(
			@RequestParam(name = "requester_id") String requester_id){
		User.Status status = User.Status.INACTIVE;
		
		return userService.findByStatus(status, requester_id);
	}
	
	@RequestMapping(value = "/get_user", method = RequestMethod.GET)
	public User getUser(
			@RequestParam(name = "id") String id
	){
		return userService.findById(id);
	}
	
	@RequestMapping(value = "/get_users_by_name", method = RequestMethod.GET)
	public List<User> getUsers(
			@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName
	){
		return userService.findByFirstNameAndLastName(firstName, lastName);
	}
	
	@RequestMapping(value = "/get_users_near_point", method = RequestMethod.GET)
	public List<User> getUsersNearPoint(
			@RequestParam(name = "point_x") double point_x,
			@RequestParam(name = "point_y") double point_y,
			@RequestParam(name = "distance") double raw_distance
	){
		Point point = new Point(point_x, point_y);
		Distance distance = new Distance(raw_distance, Metrics.KILOMETERS);
		
		return userService.findByLocationNear(point, distance);
	}
	
	@RequestMapping(value = "/get_type", method = RequestMethod.GET)
	public String getType(
			@RequestParam(name = "username") String username
	){
		return userService.getType(username);
	}

}
