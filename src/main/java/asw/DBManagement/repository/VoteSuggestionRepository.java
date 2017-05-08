package asw.DBManagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.model.VoteSuggestion;

@Repository
public interface VoteSuggestionRepository  extends CrudRepository<VoteSuggestion, Long>{

	List<VoteSuggestion> findBySuggestionEquals(Suggestion suggestion);

	List<VoteSuggestion> findByCitizenDB(CitizenDB citizen);

	VoteSuggestion findByCitizenDBAndSuggestion(CitizenDB citizen, Suggestion suggestion);

}
