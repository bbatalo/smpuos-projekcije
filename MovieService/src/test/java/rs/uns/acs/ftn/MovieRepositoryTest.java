package rs.uns.acs.ftn;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import rs.uns.acs.ftn.models.Director;
import rs.uns.acs.ftn.models.Movie;
import rs.uns.acs.ftn.models.Rating;
import rs.uns.acs.ftn.models.Actor;
import rs.uns.acs.ftn.repositories.MovieRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles("testing")
public class MovieRepositoryTest {
	
	@Value("${general.releaseDateFormat}")
	private String dateFormat;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Before
	public void insertInitialCollection() throws ParseException {
		
		/*
		 TEST MOVIE 1: The Shawshank Redemption 
		 */
		
		ArrayList<Actor> actors1 = new ArrayList<Actor>();
		Actor timRobbins = new Actor("Tim","Robbins");
		Actor morganFreeman = new Actor("Morgan", "Freeman");
		Actor bobGunton = new Actor("Bob", "Gunton");
		Actor williamSadler = new Actor("William", "Sadler");
		Actor clancyBrown = new Actor("Clancy", "Brown");
		Actor gilBellows = new Actor ("Gil", "Bellows");
		Actor markRolston = new Actor("Mark","Rolston");
		Actor jamesWhitmore = new Actor("James", "Whitmore");
		Actor jeffreyDeMunn = new Actor("Jeffrey", "DeMunn");
		Actor larryBrandenburg = new Actor("Larry","Brandenburg");
		Actor neilGiuntoli = new Actor("Neil","Giuntoli");
		Actor brianLibby = new Actor("Brian", "Libby");
		Actor davidProval = new Actor("David", "Proval");
		Actor josephRagno = new Actor("Joseph", "Ragno");
		Actor judeCiccolella = new Actor("Jude", "Ciccolella");
		actors1.add(timRobbins);
		actors1.add(morganFreeman);
		actors1.add(bobGunton);
		actors1.add(williamSadler);
		actors1.add(clancyBrown);
		actors1.add(gilBellows);
		actors1.add(markRolston);
		actors1.add(jamesWhitmore);
		actors1.add(jeffreyDeMunn);
		actors1.add(larryBrandenburg);
		actors1.add(neilGiuntoli);
		actors1.add(brianLibby);
		actors1.add(davidProval);
		actors1.add(josephRagno);
		actors1.add(judeCiccolella);
		
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		Date premiere = new Date();
		try {
	         premiere =  new SimpleDateFormat(dateFormat).parse("1994-10-14");
	     } catch (ParseException e) {
	         premiere =  null;
	         e.printStackTrace();
	     }
		
		Movie movie1 = new Movie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", new Long(144), new Director("Frank", "Darabont"),actors1, Movie.Category.DRAMA, ratings, new Double(0), premiere );
	
		/*
		 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 */
		
		
		/*
		 TEST MOVIE 2: The Godfather 
		 */
		
		ArrayList<Actor> actors2 = new ArrayList<Actor>();
		Actor marlonBrando = new Actor("Marlon", "Brando");
		Actor alPacino = new Actor("Al", "Pacino");
		Actor jamesCaan = new Actor("James", "Caan");
		Actor richardCastellano = new Actor("Richard", "Castellano");
		Actor robertDuvall = new Actor("Robert", "Duvall");
		Actor sterlingHayden = new Actor("Sterling", "Hayden");
		Actor johnMarley = new Actor("John", "Marley");
		Actor richardConte = new Actor("Richard", "Conte");
		Actor alLettieri = new Actor("Al", "Lettieri");
		Actor dianeKeaton = new Actor("Diane", "Keaton");
		Actor abeVigoda = new Actor("Abe", "Vigoda");
		Actor taliaShire = new Actor("Talia", "Shire");
		Actor gianniRusso = new Actor("Gianni", "Russo");
		Actor johnCazale = new Actor("John", "Cazale");
		Actor rudyBond = new Actor("Rudy", "Bond");
		actors2.add(marlonBrando);
		actors2.add(alPacino);
		actors2.add(jamesCaan);
		actors2.add(richardCastellano);
		actors2.add(robertDuvall);
		actors2.add(sterlingHayden);
		actors2.add(johnMarley);
		actors2.add(richardConte);
		actors2.add(alLettieri);
		actors2.add(dianeKeaton);
		actors2.add(abeVigoda);
		actors2.add(taliaShire);
		actors2.add(gianniRusso);
		actors2.add(johnCazale);
		actors2.add(rudyBond);
		
		
		Date premiere2 = new Date();
		try {
	         premiere2 =  new SimpleDateFormat(dateFormat).parse("1972-03-24");
	     } catch (ParseException e) {
	         premiere2 =  null;
	     }
		
		Movie movie2 = new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", new Long(175), new Director("Francis", "Ford Coppola"),actors2, Movie.Category.DRAMA, ratings, new Double(0), premiere2 );
	
	
		
		/*
		 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 */
		
		
		/*
		 TEST MOVIE 3: The Green Mile 
		 */
		
		ArrayList<Actor> actors3 = new ArrayList<Actor>();
		Actor tomHanks = new Actor("Tom", "Hanks");
		Actor davidMorse = new Actor("David", "Morse");
		Actor michaelClarkeDuncan = new Actor("Michael", "Clarke Duncan");
		Actor bonnieHunt = new Actor("Bonnie", "Hunt");
		Actor jamesCromwell = new Actor("James", "Cromwell");
		Actor michaelJeter = new Actor("Michael", "Jeter");
		Actor grahamGreene = new Actor("Graham", "Greene");
		Actor dougHutchison = new Actor("Doug", "Hutchison");
		Actor samRockwell = new Actor("Sam", "Rockwell");
		Actor barryPepper = new Actor("Barry", "Pepper");
		Actor patriciaClarkson = new Actor("Patricia", "Clarkson");
		Actor harryDeanStanton = new Actor("Harry", "Dean Stanton");
		Actor dabbsGreer = new Actor("Dabbs", "Greer");
		Actor eveBrent = new Actor("Eve", "Brent");
		actors3.add(tomHanks);
		actors3.add(davidMorse);
		actors3.add(michaelClarkeDuncan);
		actors3.add(bonnieHunt);
		actors3.add(jamesCromwell);
		actors3.add(michaelJeter);
		actors3.add(grahamGreene);
		actors3.add(dougHutchison);
		actors3.add(samRockwell);
		actors3.add(barryPepper);
		actors3.add(jeffreyDeMunn);
		actors3.add(patriciaClarkson);
		actors3.add(harryDeanStanton);
		actors3.add(dabbsGreer);
		actors3.add(eveBrent);
		
		
		Date premiere3 = new Date();
		try {
	         premiere3 =  new SimpleDateFormat(dateFormat).parse("1999-12-10");
	     } catch (ParseException e) {
	         premiere3 =  null;
	     }
		
		Movie movie3 = new Movie("The Green Mile", "The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.", new Long(189), new Director("Frank", "Darabont"),actors3, Movie.Category.DRAMA, ratings, new Double(0), premiere3 );
	
		
		/*
		 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 */
		
		
		/*
		 TEST MOVIE 4: Django Unscoped 
		 */
		
		ArrayList<Actor> actors4 = new ArrayList<Actor>();
		Actor jamieFoxx = new Actor("Jamie", "Foxx");
		Actor christophWaltz = new Actor("Cristoph", "Waltz");
		Actor leonardoDiCaprio = new Actor("Leonardo", "DiCaprio");
		Actor kerryWashington = new Actor("Kerry", "Washington");
		Actor samuelLJackson = new Actor("Samuel L.", "Jackson");
		Actor waltonGoggins = new Actor("Walton", "Goggins");
		Actor dennisChristopher = new Actor("Dennis", "Christopher");
		Actor jamesRemar = new Actor("James", "Remar");
		Actor davidSteen = new Actor("David", "Steen");
		Actor danaGourrier = new Actor("Dana", "Gourrier");
		Actor nicholeGalicia = new Actor("Nichole", "Galicia");
		Actor lauraCayouette = new Actor("Laura", "Cayouette");
		Actor atoEssandoh = new Actor("Ato", "Essandoh");
		Actor sammiRotibi = new Actor("Sammi", "Rotibi");
		actors4.add(jamieFoxx);
		actors4.add(christophWaltz);
		actors4.add(leonardoDiCaprio);
		actors4.add(kerryWashington);
		actors4.add(samuelLJackson);
		actors4.add(waltonGoggins);
		actors4.add(dennisChristopher);
		actors4.add(jamesRemar);
		actors4.add(davidSteen);
		actors4.add(danaGourrier);
		actors4.add(nicholeGalicia);
		actors4.add(lauraCayouette);
		actors4.add(atoEssandoh);
		actors4.add(sammiRotibi);
		
		
		Date premiere4 = new Date();
		try {
			premiere4 =  new SimpleDateFormat(dateFormat).parse("2012-12-11");
	     } catch (ParseException e) {
	    	 premiere4 =  null;
	     }
		
		Movie movie4 = new Movie("Django Unchained", "With the help of a German bounty hunter, a freed slave sets out to rescue his wife from a brutal Mississippi plantation owner.", new Long(165), new Director("Quentin", "Tarantino"),actors4, Movie.Category.WESTERN, ratings, new Double(0), premiere4 );
	
		
		
		/*
		 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 */
		
		
		/*
		 TEST MOVIE 5: La La Land 
		 */
		
		
		ArrayList<Actor> actors5 = new ArrayList<Actor>();
		Actor ryanGosling = new Actor("Ryan", "Gosling");
		Actor emmaStone = new Actor("Emma", "Stone");
		Actor amieeConn = new Actor("Amiee", "Conn");
		Actor terryWalters = new Actor("Terry", "Walters");
		Actor thomShelton = new Actor("Thom", "Shelton");
		Actor cindaAdams = new Actor("Cinda", "Adams");
		Actor callieHernandez = new Actor("Callie", "Hernandez");
		Actor jessicaRothe = new Actor("Jessica", "Rothe");
		Actor sonoyaMizuno = new Actor("Sonoya", "Mizuno");
		Actor rosemarieDeWitt = new Actor("Rosemarie", "DeWitt");
		Actor jkSimmons = new Actor("J.K.", "Simmons");
		Actor claudineClaudio = new Actor("Claudine", "Claudio");
		Actor jasonFuchs = new Actor("Jason", "Fuchs");
		Actor daWallach = new Actor("D.A.", "Wallach");
		Actor trevorLIssauer = new Actor("Trevor", "Lissauer");
		actors5.add(ryanGosling);
		actors5.add(emmaStone);
		actors5.add(amieeConn);
		actors5.add(terryWalters);
		actors5.add(thomShelton);
		actors5.add(cindaAdams);
		actors5.add(callieHernandez);
		actors5.add(jessicaRothe);
		actors5.add(sonoyaMizuno);
		actors5.add(rosemarieDeWitt);
		actors5.add(jkSimmons);
		actors5.add(claudineClaudio);
		actors5.add(jasonFuchs);
		actors5.add(daWallach);
		actors5.add(trevorLIssauer);
		
		Date premiere5 = new Date();
		try {
			premiere5 =  new SimpleDateFormat(dateFormat).parse("2016-12-25");
	     } catch (ParseException e) {
	    	 premiere5 =  null;
	     }
		
		Movie movie5 = new Movie("La La Land", "While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.", new Long(128), new Director("Damien", "Chazelle"),actors5, Movie.Category.MUSICAL, ratings, new Double(0), premiere5 );
	
		
		/*
		 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 */
		
		/*
		 TEST MOVIE 6: Star Wars: Episode VIII - The Last Jedi
		 */
		
		
		ArrayList<Actor> actors6 = new ArrayList<Actor>();
		Actor markHamill = new Actor("Mark", "Hamill");
		Actor carrieFisher = new Actor("Carrie", "Fisher");
		Actor adamDriver = new Actor("Adam", "Driver");
		Actor daisyRidley = new Actor("Daisy", "Ridley");
		Actor johnBoyega = new Actor("John", "Boyega");
		Actor oscarIsaac = new Actor("Oscar", "Isaac");
		Actor andySerkis = new Actor("Andy", "Serkis");
		Actor lupitaNyongo = new Actor("Lupita", "Nyong'o");
		Actor domhnallGleeson = new Actor("Domhnall", "Gleeson");
		Actor anthonyDaniels = new Actor("Anthony", "Daniels");
		Actor gwendolineChristie = new Actor("Gwendoline", "Christie");
		Actor kellyMarieTran = new Actor("Kelly Marie","Tran");
		Actor lauraDern = new Actor("Laura", "Dern");
		Actor benicioDelToro = new Actor("Benicio", "Del Toro");
		Actor frankOz = new Actor("Frank", "Oz");
		actors6.add(markHamill);
		actors6.add(carrieFisher);
		actors6.add(adamDriver);
		actors6.add(daisyRidley);
		actors6.add(johnBoyega);
		actors6.add(oscarIsaac);
		actors6.add(andySerkis);
		actors6.add(lupitaNyongo);
		actors6.add(domhnallGleeson);
		actors6.add(anthonyDaniels);
		actors6.add(gwendolineChristie);
		actors6.add(kellyMarieTran);
		actors6.add(lauraDern);
		actors6.add(benicioDelToro);
		actors6.add(frankOz);
		
		Date premiere6 = new Date();
		try {
			premiere6 =  new SimpleDateFormat(dateFormat).parse("2018-01-03");
	     } catch (ParseException e) {
	    	 premiere6 =  null;
	     }
		
		Movie movie6 = new Movie("Star Wars: Episode VIII - The Last Jedi", "Rey develops her newly discovered abilities with the guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile, the Resistance prepares for battle with the First Order.", new Long(152), new Director("Rian", "Johnson"),actors6, Movie.Category.ACTION, ratings, new Double(0), premiere6 );
	
		
		/*
		 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 */
		
		movieRepository.save(movie1);
		movieRepository.save(movie2);
		movieRepository.save(movie3);
		movieRepository.save(movie4);
		movieRepository.save(movie5);
		movieRepository.save(movie6);
		
		
	}
	
	@Test
	public void testMovieRepository(){
		/* FIRST RUN AFTER RUNNING DB_SCRIPT INIT */
		ArrayList<Movie> movies = (ArrayList<Movie>)movieRepository.findAll();
		Assert.assertEquals(10, movies.size());

		Page<Movie> page = movieRepository.findAll(new PageRequest(0,20));
		Assert.assertEquals(10, page.getNumberOfElements());
		Assert.assertEquals(10, page.getTotalElements());
		
		/* FIRST RUN */
		
		/* EVERY OTHER RUN 
		ArrayList<Movie> movies = (ArrayList<Movie>)movieRepository.findAll();
		Assert.assertEquals(6, movies.size());

		Page<Movie> page = movieRepository.findAll(new PageRequest(0,20));
		Assert.assertEquals(6, page.getNumberOfElements());
		Assert.assertEquals(6, page.getTotalElements());
		 OTHER RUNS */
		
		movies = (ArrayList<Movie>) movieRepository.findByRatingAvg(new Double(10));
		Assert.assertEquals(0, movies.size());
		
		movies = (ArrayList<Movie>)movieRepository.findAll();
		movies.get(2).setRatingAvg(new Double(10));
		movieRepository.save(movies.get(2));
		movies = (ArrayList<Movie>) movieRepository.findByRatingAvg(new Double(10));
		Assert.assertEquals(1, movies.size());
		
		/* FIRST RUN */
		movies = (ArrayList<Movie>) movieRepository.findAllByActor("Ryan", "Gosling");
		Assert.assertEquals(2, movies.size());
		
		/* FIRST */
		
		/* OTHER RUNS 
		movies = (ArrayList<Movie>) movieRepository.findAllByActor("Jeffrey", "DeMunn");
		Assert.assertEquals(2, movies.size());
		
		 OTHER */
		
		/*                                   NOT SURE HOW TO TEST FOR LISTS
		ArrayList<Actor> actorsTest = new ArrayList<Actor>();
		
		Actor jeffreyDeMunn = new Actor("Jeffrey", "DeMunn");
		actorsTest.add(jeffreyDeMunn);
		
		movies = (ArrayList<Movie>) movieRepository.findByActors(actorsTest);
		Assert.assertEquals(2, movies.size());
		
		Actor tomHanks = new Actor("Tom", "Hanks");
		actorsTest.add(tomHanks);
		
		movies = (ArrayList<Movie>) movieRepository.findByActors(actorsTest);
		Assert.assertEquals(1, movies.size());
		*/
		
		movies = (ArrayList<Movie>) movieRepository.findByName("The Shawshank Redemption");
		Assert.assertEquals(1, movies.size());
		Calendar cal = Calendar.getInstance();
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		cal.set(Calendar.DAY_OF_YEAR, 1);    
		Date start = cal.getTime();
		
		cal.set(Calendar.MONTH, 11); // 11 = december
		cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
		
		Date end = cal.getTime();
		
		movies = (ArrayList<Movie>) movieRepository.findByPremiereBetween(start, end);
		Assert.assertEquals(1, movies.size());
		

		cal.set(Calendar.YEAR, 1994);
		cal.set(Calendar.DAY_OF_YEAR, 1);    
		start = cal.getTime();
		System.out.println(start.toString());
		cal.set(Calendar.YEAR, 1994);
		cal.set(Calendar.MONTH, 11); // 11 = december
		cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
		
		end = cal.getTime();
		System.out.println(end.toString());
		
		movies = (ArrayList<Movie>) movieRepository.findByPremiereBetween(start, end);
		Assert.assertEquals(1, movies.size());
		

		
		
		movies = (ArrayList<Movie>) movieRepository.findByCategory(Movie.Category.DRAMA);
		Assert.assertEquals(3, movies.size());
		
		movies = (ArrayList<Movie>) movieRepository.findAllByDirectorFirstNameAndLastName("Frank", "Darabont");
		Assert.assertEquals(2, movies.size());
		
		movies = (ArrayList<Movie>) movieRepository.findAllByDirectorFirstNameAndLastName("Quentin", "Tarantino");
		Assert.assertEquals(1, movies.size());
		
		
	
	}
	
	
	@After
	public void cleanUp(){
		movieRepository.deleteAll();
	}

}
