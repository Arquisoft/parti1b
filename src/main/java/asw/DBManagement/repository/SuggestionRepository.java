package asw.DBManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Suggestion;

@Repository("suggestionRepository")
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

	List<Suggestion> findByCitizenDB(CitizenDB citizenDB);
	List<Suggestion> findAll();
	
	Suggestion findByTitle(String title);

}
