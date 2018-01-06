package rs.uns.acs.ftn.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.forms.ActivationForm;
import rs.uns.acs.ftn.forms.LoginForm;
import rs.uns.acs.ftn.forms.SignUpForm;
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
	public String login(@RequestBody LoginForm loginForm){
		return userService.login(loginForm.getUsername(), loginForm.getPassword());
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(@RequestBody String sessionId){
		userService.logout(sessionId);
	}
	
	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	public User signUp(@RequestBody SignUpForm signUpForm){
		return userService.signUp(signUpForm.getUsername(), signUpForm.getPassword(),
				signUpForm.getFirstName(), signUpForm.getLastName(),
				signUpForm.getDateOfBirth(), signUpForm.getGender(), 
				signUpForm.getLocation());
	}
	
	@RequestMapping(value = "/activate_user", method = RequestMethod.POST)
	public void activateUser(@RequestBody ActivationForm activationForm){
		userService.activateUser(activationForm.getUsername(), activationForm.getSessionId());
	}
	
	@RequestMapping(value = "/deactivate_user", method = RequestMethod.POST)
	public void deactivateUser(@RequestBody ActivationForm activationForm){
		userService.deactivateUser(activationForm.getUsername(), activationForm.getSessionId());
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public List<User> findAllActive(@RequestParam(name = "session_id") String sessionId){
		User.Status status = User.Status.ACTIVE;
		
		return userService.findByStatus(status, sessionId);
	}
	
	@RequestMapping(value = "/inactive", method = RequestMethod.GET)
	public List<User> findAllInactive(@RequestParam(name = "session_id") String sessionId){
		User.Status status = User.Status.INACTIVE;
		
		return userService.findByStatus(status, sessionId);
	}
	
	@RequestMapping(value = "/get_user", method = RequestMethod.GET)
	public User getUser(@RequestParam(name = "id") String id){
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
			@RequestParam(name = "point_x") double pointX,
			@RequestParam(name = "point_y") double pointY,
			@RequestParam(name = "distance") double raw_distance
	){
		Point point = new Point(pointX, pointY);
		Distance distance = new Distance(raw_distance, Metrics.KILOMETERS);
		
		return userService.findByLocationNear(point, distance);
	}
	
	@RequestMapping(value = "/get_username_by_session_id", method = RequestMethod.GET)
	public String getUsernameBySessionId(@RequestParam(name = "sessionId") String sessionId){
		return userService.getUsernameBySessionId(sessionId);
	}
	
	@RequestMapping(value = "/get_type_by_session_id", method = RequestMethod.GET)
	public String getTypeBySessionId(@RequestParam(name = "sessionId") String sessionId){
		return userService.getTypeBySessionId(sessionId);
	}
	
	@RequestMapping(value = "/get_type", method = RequestMethod.GET)
	public String getType(@RequestParam(name = "username") String username){
		return userService.getType(username);
	}

}
