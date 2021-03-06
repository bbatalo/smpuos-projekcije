package rs.uns.acs.ftn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import rs.uns.acs.ftn.models.User;
import rs.uns.acs.ftn.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles("testing")
public class UserRepositoryTest {
	
	@Value("${general.dateFormat}")
	private String dateFormat;
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void insertInitialCollection() throws ParseException {
		User user1 = new User(
				null,
			    "Bill", 
			    "Miller",
			    "bm",
			    "pass123",
			    new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"),
				new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"),		
			    User.Gender.MALE,
			    new GeoJsonPoint(19.8334687,45.2527831),
			    User.Status.ACTIVE,
			    User.Type.REGISTERED);
		
		User user2 = new User(
				null,
			    "Sam", 
			    "Huckerr",
			    "sh",
			    "pass1",
			    new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"),
				new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"),		
			    User.Gender.FEMALE,
			    new GeoJsonPoint(19.8334687,45.2527831),
			    User.Status.ACTIVE,
			    User.Type.REGISTERED);
		
		User user3 = new User(
				null,
			    null, 
			    "Hass",
			    "jh",
			    "pass12",
			    new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"),
				new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"),		
			    User.Gender.OTHER,
			    new GeoJsonPoint(19.8334687,45.2527831),
			    User.Status.ACTIVE,
			    User.Type.REGISTERED);
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
	}
	
	@Test
	public void testUserRepository(){
		
		List<User> users = userRepository.findAll();
		Assert.assertEquals(3, users.size());

		Page<User> page = userRepository.findAll(new PageRequest(0,20));
		Assert.assertEquals(3, page.getNumberOfElements());
		Assert.assertEquals(3, page.getTotalElements());
		
		users = userRepository.findByFirstNameAndLastName("Sam", "Huckerr");
		Assert.assertEquals(1, users.size());	
		
		users = userRepository.findByLocationNear( 
				new Point(13.8334688,48.2527831),
				new Distance(1., Metrics.KILOMETERS));
		Assert.assertEquals(1, users.size());
		
		users = userRepository.findByLocationWithin( 
				new GeoJsonPolygon(
						new Point(0., 0.),
						new Point (0., 50.),
						new Point(30.0, 50.),
						new Point (30., 0.),
						new Point(0., 0.))
				);
		Assert.assertEquals(2, users.size());
		
		users = userRepository.findByStatus(User.Status.ACTIVE);
		Assert.assertEquals(3, users.size());
	}
	
	
	@After
	public void cleanUp(){
		userRepository.deleteAll();
	}

}
