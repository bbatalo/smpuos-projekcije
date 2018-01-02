package rs.uns.acs.ftn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
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
	
	public User login(String userName, String password){
		User.UserStatus userStatus = User.UserStatus.ACTIVE;
		User user = userRepository.findByUserNameAndUserStatus(userName, userStatus);
		
		if(user != null){
			if(user.getPassword().equals(password)){
				return user;
			}
		}
		
		return null;
	}

	public User signUp(String userName, String password){
		User user = new User(
				null,
				null,
				null,
				userName,
				password,
				null,
				null,
				null,
				null,
				User.UserStatus.ACTIVE,
				User.UserType.REGISTERED);
				
		userRepository.save(user);
		
		return null;
	}
	
	public void activateUser(String userName, String token) {
		User.UserType userType = User.UserType.ADMINISTRATOR;
		User admin = userRepository.findByUserNameAndUserType(token, userType);
		
		if (admin != null) {
			User.UserStatus oldUserStatus = User.UserStatus.INACTIVE;
			User targetUser = userRepository.findByUserNameAndUserStatus(userName, oldUserStatus);
			
			if (targetUser != null) {
				User.UserStatus newUserStatus = User.UserStatus.ACTIVE;
				
				targetUser.setUserStatus(newUserStatus);
			
				userRepository.save(targetUser);
			}
		}
	}
	
	public void deactivateUser(String userName, String token) {
		User.UserType userType = User.UserType.ADMINISTRATOR;
		User admin = userRepository.findByUserNameAndUserType(token, userType);
		
		if (admin != null) {
			User.UserStatus oldUserStatus = User.UserStatus.ACTIVE;
			User targetUser = userRepository.findByUserNameAndUserStatus(userName, oldUserStatus);
			
			if (targetUser != null) {
				User.UserStatus newUserStatus = User.UserStatus.INACTIVE;
				
				targetUser.setUserStatus(newUserStatus);
			
				userRepository.save(targetUser);
			}
		}
	}
	
	public List<User> findByUserStatus(User.UserStatus userStatus, String token){
		User.UserType userType = User.UserType.ADMINISTRATOR;
		User admin = userRepository.findByUserNameAndUserType(token, userType);
		
		if (admin != null) {
			return userRepository.findByUserStatus(userStatus);
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

	public User findByIdAndUserStatus(String userId, User.UserStatus userStatus) {
		return userRepository.findByIdAndUserStatus(userId, userStatus);
	}

	public User findById(String userId) {
		return userRepository.findById(userId);
	}

	public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}
	
	public List<User> findByUserLocationNear(Point point, Distance distance) {
		return userRepository.findByUserLocationNear(point, distance);
	};

}
