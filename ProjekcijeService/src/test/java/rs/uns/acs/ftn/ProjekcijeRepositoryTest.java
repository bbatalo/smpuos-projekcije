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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import rs.uns.acs.ftn.models.Projekcije;
import rs.uns.acs.ftn.repositories.ProjekcijeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjekcijeServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles("testing")
public class ProjekcijeRepositoryTest {

	@Value("${general.dateFormat}")
	private String dateFormat;
	
	@Autowired
	private ProjekcijeRepository projekcijeRepository;
	
	@Before
	public void insertInitialCollection() throws ParseException {	
		Projekcije projekcija1 = new Projekcije(
				"prId_1",
			    new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"),				
				Projekcije.Type.PREMIERE,
				Projekcije.Status.ACTIVE,
				"cinemaId_1",
				"Cinema1",
				"hallId_11",
				"Hall1",
				"movieId_50",
				"Zaposlenih 10%");
		
		Projekcije projekcija2 = new Projekcije(
				"prId_2",
			    new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"),				
				Projekcije.Type.REGULAR,
				Projekcije.Status.ACTIVE,
				"cinemaId_2",
				"Cinema2",
				"hallId_22",
				"Hall2",
				"movieId_60",
				"Microservice na vodi");		
		
		Projekcije projekcija3 = new Projekcije(
				"prId_3",
			    new SimpleDateFormat(dateFormat).parse("1900-01-01T00:00:00.000Z"),				
				Projekcije.Type.PREMIERE,
				Projekcije.Status.INACTIVE,
				"cinemaId_1",
				"Cinema1",
				"hallId_11",
				"Hall1",
				"movieId_70",
				"Bice bolje! - AV");
		
		projekcijeRepository.save(projekcija1);
		projekcijeRepository.save(projekcija2);
		projekcijeRepository.save(projekcija3);
	}
	
	@Test
	public void testProjekcijeRepository(){
		
		List<Projekcije> projekcije = projekcijeRepository.findAll();
		Assert.assertEquals(3, projekcije.size());
		
		Projekcije proj = projekcijeRepository.findOne("prId_2");
		Assert.assertEquals("prId_2", proj.getId());
		
		projekcije = projekcijeRepository.findByType(Projekcije.Type.PREMIERE);
		Assert.assertEquals(2, projekcije.size());
		projekcije = projekcijeRepository.findByType(Projekcije.Type.REGULAR);
		Assert.assertEquals(1, projekcije.size());		
		
		projekcije = projekcijeRepository.findByStatus(Projekcije.Status.INACTIVE);
		Assert.assertEquals(1, projekcije.size());
		projekcije = projekcijeRepository.findByStatus(Projekcije.Status.ACTIVE);
		Assert.assertEquals(2, projekcije.size());
				
		projekcije = projekcijeRepository.findByCinemaId("cinemaId_1");
		Assert.assertEquals(2, projekcije.size());
		projekcije = projekcijeRepository.findByCinemaName("Cinema1");
		Assert.assertEquals(2, projekcije.size());

		
		projekcije = projekcijeRepository.findByMovieId("movieId_70");
		Assert.assertEquals(1, projekcije.size());
		projekcije = projekcijeRepository.findByMovieName("Bice bolje! - AV");
		Assert.assertEquals(1, projekcije.size());		
	}
	
	@Before
	@After
	public void cleanUp(){
		projekcijeRepository.deleteAll();
	}	
}
