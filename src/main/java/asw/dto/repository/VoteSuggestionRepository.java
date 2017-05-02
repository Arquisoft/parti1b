package asw.dto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import asw.dto.model.CitizenDB;
import asw.dto.model.Suggestion;
import asw.dto.model.VoteSuggestion;
import asw.dto.model.key.VoteSuggestionKey;

public interface VoteSuggestionRepository  extends CrudRepository<VoteSuggestion, Long>{

	List<VoteSuggestion> findBySuggestion(Suggestion suggestion);

	List<VoteSuggestion> findByCitizenDB(CitizenDB citizen);

	VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey);

	//VoteSuggestion findByVoteCommentKey(VoteSuggestionKey voteSuggestionKey); //No hay campo voteSuggKey ¿?

}
