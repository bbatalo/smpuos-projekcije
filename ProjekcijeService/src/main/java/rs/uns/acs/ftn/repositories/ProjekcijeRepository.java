package rs.uns.acs.ftn.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import rs.uns.acs.ftn.models.Projekcije;

public interface ProjekcijeRepository extends MongoRepository<Projekcije, String>{

	List<Projekcije> findByDate(Date date);

	// pregled projekcija po tipu (premijerne, regularne)
	List<Projekcije> findByType(Projekcije.Type type);	
	
	// pregled projekcija po statusu (aktivne, neaktivne)
	List<Projekcije> findByStatus(Projekcije.Status status);	

	// pregled projekcija po bioskopima
	List<Projekcije> findByCinemaId(String id);
	List<Projekcije> findByCinemaName(String cinemaName);

	// pregled projekcija po filmovima
	List<Projekcije> findByMovieId(String id);
	List<Projekcije> findByMovieName(String movieName);
		
}
