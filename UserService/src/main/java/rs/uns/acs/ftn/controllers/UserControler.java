package rs.uns.acs.ftn.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.models.User;
import rs.uns.acs.ftn.services.UserService;

@RestController
@RequestMapping("users")
public class UserControler extends AbstractRESTController<User, String>{
	
	@Autowired
	Environment environment;
	
	private UserService userService;
	
    private final Random random = new Random();

    private static final String[] NAMES = new String[] {
    		"Arnette Whitesides",
    		"Sherley Holifield ",
    		"Iva Mathias",
    		"Joellen Hatch",
    		"Harley Braziel",
    		"Oralee Thweatt",
    		"Mao Lammert",
    		"Dannette Peru",
    		"Sherell Service",
    		"Tamara Bratcher",
    		"Quintin Vankirk",
    		"Orval Tarter",
    		"Alysa Kesterson",
    		"Krissy Bothwell",
    		"Freeda Leicht",
    		"Gemma Crippen",
    		"Darci Caroll",
    		"Tarra Argento",
    		"Corinne Farah",
    		"Myrta Neuberger"

    };
    
	@Autowired
	public UserControler(UserService userService) {
		super(userService);
		this.userService = userService;
	}
	
	@RequestMapping(value = "/hello")
	public String hello(){

		return NAMES[random.nextInt(NAMES.length)] +"[PORT: "+ environment.getProperty("local.server.port") + "]";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public User login(
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "password") String password){
		return userService.login(userName, password);
	}
	
	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public User signUp(
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "password") String password){
		return userService.signUp(userName, password);
	}
	
	@RequestMapping(value = "/activate_user", method = RequestMethod.GET)
	public void activateUser(
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "token") String token){
		userService.activateUser(userName, token);
	}
	
	@RequestMapping(value = "/deactivate_user", method = RequestMethod.GET)
	public void deactivateUser(
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "token") String token){
		userService.deactivateUser(userName, token);
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public List<User> findAllActive(
			@RequestParam(name = "token") String token){
		User.UserStatus userStatus = User.UserStatus.ACTIVE;
		
		return userService.findByUserStatus(userStatus, token);
	}
	
	@RequestMapping(value = "/inactive", method = RequestMethod.GET)
	public List<User> findAllInactive(
			@RequestParam(name = "token") String token){
		User.UserStatus userStatus = User.UserStatus.INACTIVE;
		
		return userService.findByUserStatus(userStatus, token);
	}
	
	@RequestMapping(value = "/get_user", method = RequestMethod.GET)
	public User getUser(
			@RequestParam(name = "userId") String userId
	){
		return userService.findById(userId);
	}
	
	@RequestMapping(value = "/get_users", method = RequestMethod.GET)
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
		Distance distance = new Distance(raw_distance);
		
		return userService.findByUserLocationNear(point, distance);
	}

}
