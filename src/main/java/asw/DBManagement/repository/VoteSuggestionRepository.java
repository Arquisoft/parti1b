package asw.DBManagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.model.VoteSuggestion;


public interface VoteSuggestionRepository  extends CrudRepository<VoteSuggestion, Long>{

	List<VoteSuggestion> findBySuggestionEquals(Suggestion suggestion);

	List<VoteSuggestion> findByCitizenDB(CitizenDB citizen);

	//VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey);

	//VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey); //No hay campo voteSuggKey ¿?

}
